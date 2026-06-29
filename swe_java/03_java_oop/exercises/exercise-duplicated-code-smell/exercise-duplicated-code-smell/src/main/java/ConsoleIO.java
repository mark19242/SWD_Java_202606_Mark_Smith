import java.util.Scanner;

public class ConsoleIO {

    Scanner myScanner = new Scanner(System.in);

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public int getInteger(String prompt) {
        boolean goodInput = false;
        int number = -1;

        while(!goodInput) {
            System.out.println(prompt);
            try {
                number = Integer.parseInt(myScanner.nextLine());
                goodInput = true;
            } catch(Exception e) {
                writeMessage("Input a valid number");
            }
        }
        return number;
    }

    public int getIntegerInBetween(String prompt, int min, int max) {

        boolean valid = false;
        int number = -1;

        do {

            number = getInteger(prompt);

            if (number >= min && number <= max) {
                valid = true;
            } else {
                writeMessage("Enter a number between " + min + " and " + max);
            }

        } while (!valid);

        return number;
    }

    public String getInput(String prompt) {
        writeMessage(prompt);
        return myScanner.nextLine();
    }

    public String getNonNullNonEmptyString(String prompt) {

        boolean invalid = true;
        String result = null;

        do {
            result = getInput(prompt);

            invalid = result == null || result.isBlank();

            if (invalid) {
                writeMessage("Enter a valid response.");
            }

        } while (invalid);

        return result;
    }

//    public double getMoney() {
//        boolean goodInput = false;
//        double number = 0.0;
//
//        while(!goodInput) {
//            System.out.println("Enter the amount of cash on hand: ");
//            try {
//                number = Double.parseDouble(myScanner.nextLine());
//                goodInput = true;
//            } catch(Exception e) {
//                System.out.println("Input a valid number");
//            }
//        }
//        return number;
//    }


    public double getPositiveMoney() {
        boolean goodInput = false;
        double number = 0.0;

        while(!goodInput) {
            System.out.println("Enter the amount of cash on hand: ");
            try {
                number = Double.parseDouble(myScanner.nextLine());
                if(number > 0.0) {
                    goodInput = true;
                } else {
                    System.out.println("Enter a positive amount of money.");
                }
            } catch(Exception e) {
                writeMessage("Input a valid number");
            }
        }
        return number;
    }
}
