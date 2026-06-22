import java.util.Scanner;

public class UserRegistration {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            // =========================
            // Get Age
            // =========================
            System.out.print("Enter your age: ");
            int age = Integer.parseInt(scanner.nextLine());

            // =========================
            // Get Email
            // =========================
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            if (email == null || email.trim().isEmpty()) {
                throw new NullPointerException("Email cannot be empty.");
            }

            // =========================
            // Get PIN
            // =========================
            System.out.print("Enter your 4-digit PIN: ");
            String pinInput = scanner.nextLine();

            int pin = Integer.parseInt(pinInput);

            if (pinInput.length() != 4) {
                throw new IllegalArgumentException("PIN must be exactly 4 digits.");
            }

            // =========================
            // Success
            // =========================
            System.out.println("\nRegistration Successful!");
            System.out.println("Age: " + age);
            System.out.println("Email: " + email);
            System.out.println("PIN: " + pin);

        }
        catch (NumberFormatException ex) {
            System.out.println("Error: Age and PIN must be numeric.");
        }
        catch (NullPointerException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        finally {
            System.out.println("\nRegistration Attempt Complete!");
            scanner.close();
        }
    }
}
