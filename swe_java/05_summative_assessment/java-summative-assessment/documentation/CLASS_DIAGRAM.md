# Inventory Manager Class Diagram

This diagram shows the current class structure of the Inventory Manager
application. It will be updated as additional product types, interfaces,
and persistence classes are added.

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
    }

    class InventoryService {
        -List~Product~ products
        +addProduct(Product product) boolean
        +getAllProducts() List~Product~
        +findProductById(String productID) Product
        +findProductsByName(String productName) List~Product~
        +updateProduct(String productID, int quantity, double price) boolean
        +deleteProduct(String productID) boolean
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

    Product <|-- StandardProduct
    InventoryService "1" o-- "*" Product : manages
    MainMenu --> InventoryService : uses
    App --> InventoryService : creates
    App --> MainMenu : starts
```

## Relationship Summary

- `App` creates the `InventoryService` and starts the `MainMenu`.
- `MainMenu` handles user interaction and sends inventory requests to
  `InventoryService`.
- `InventoryService` manages a collection of `Product` objects.
- `Product` is an abstract parent class containing information and behavior
  shared by all product types.
- `StandardProduct` inherits from `Product` and represents a regular
  inventory item.

## Planned Updates

This diagram will be updated after adding:

- `PerishableProduct`
- An interface for advanced product behavior
- `InventoryRepository`
- File-saving and loading relationships