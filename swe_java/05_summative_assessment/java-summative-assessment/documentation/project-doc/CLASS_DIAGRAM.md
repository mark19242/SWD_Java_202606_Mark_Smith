# Inventory Manager Class Diagram

This diagram shows the current class structure of the Inventory Manager
application. The application separates product models, user interaction,
business logic, and file persistence.

```mermaid
classDiagram

    class App {
        +main(String[] args) void
    }

    class MainMenu {
        -Scanner scanner
        -InventoryService inventoryService
        +MainMenu(InventoryService inventoryService)
        +run() void
        -displayMenu() void
        -addProduct() void
        -viewProducts() void
        -searchProduct() void
        -updateProduct() void
        -deleteProduct() void
        -saveInventory() void
        -loadInventory() void
    }

    class InventoryService {
        -List~Product~ products
        -InventoryRepository repository
        +InventoryService()
        +InventoryService(InventoryRepository repository)
        +saveInventory() void
        +loadInventory() void
        +addProduct(Product product) boolean
        +getAllProducts() List~Product~
        +findProductById(String productID) Product
        +findProductsByName(String productName) List~Product~
        +updateProduct(String productID, int quantity, double price) boolean
        +deleteProduct(String productID) boolean
    }

    class InventoryRepository {
        <<interface>>
        +save(List~Product~ products) void
        +load() List~Product~
    }

    class FileInventoryRepository {
        -Path filePath
        +FileInventoryRepository(Path filePath)
        +save(List~Product~ products) void
        +load() List~Product~
        -formatProduct(Product product) String
        -cleanField(String value) String
        -parseProduct(String line, int lineNumber) Product
    }

    class Product {
        <<abstract>>
        -String productID
        -String productName
        -int quantity
        -double price
        +Product(String productID, String productName, int quantity, double price)
        +getProductID() String
        +getProductName() String
        +getQuantity() int
        +getPrice() double
        +addStock(int amount) boolean
        +removeStock(int amount) boolean
        +updatePrice(double newPrice) boolean
        +displayProductInfo() String
        +getProductType() String
    }

    class StandardProduct {
        +StandardProduct(String productID, String productName, int quantity, double price)
        +getProductType() String
    }

    class PerishableProduct {
        -LocalDate expirationDate
        +PerishableProduct(String productID, String productName, int quantity, double price, LocalDate expirationDate)
        +getProductType() String
        +getExpirationDate() LocalDate
        +isExpired() boolean
        +displayProductInfo() String
    }

    class Expirable {
        <<interface>>
        +getExpirationDate() LocalDate
        +isExpired() boolean
    }

    Product <|-- StandardProduct
    Product <|-- PerishableProduct
    Expirable <|.. PerishableProduct

    InventoryRepository <|.. FileInventoryRepository
    InventoryService --> InventoryRepository : depends on
    InventoryService "1" o-- "*" Product : manages

    MainMenu --> InventoryService : uses
    App --> InventoryService : creates
    App --> MainMenu : starts

    FileInventoryRepository ..> Product : saves and recreates
    FileInventoryRepository ..> StandardProduct : creates
    FileInventoryRepository ..> PerishableProduct : creates
```

## Relationship Summary

- `App` creates the `InventoryService`, supplies it to `MainMenu`, and starts
  the application.
- `MainMenu` handles console input and output and delegates inventory
  operations to `InventoryService`.
- `InventoryService` manages the in-memory collection of `Product` objects.
- `InventoryService` depends on the `InventoryRepository` interface rather
  than a specific storage implementation.
- `InventoryRepository` defines the required save and load operations.
- `FileInventoryRepository` implements the repository interface using a
  tab-separated text file.
- `Product` is the abstract parent class for all inventory products.
- `StandardProduct` represents a regular inventory item.
- `PerishableProduct` extends `Product` and implements `Expirable`.
- `Expirable` defines the behavior required for objects with expiration dates.