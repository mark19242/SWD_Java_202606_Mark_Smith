package classwork;

public class DataTypes {

    public static void main(String[] args) {

        // int data type
        int myAge = 46;
        System.out.println("My age: " + myAge);

        // boolean data type
        boolean aliceIsThirsty = true;
        boolean bobIsThirsty = false;

        System.out.println("Alice is thirsty: " + aliceIsThirsty);
        System.out.println("Bob is thirsty: " + bobIsThirsty);

        // char data type
        char alpha = 'a';
        char lowerCaseB = 98;

        System.out.println("Alpha: " + alpha);
        System.out.println("Lowercase B: " + lowerCaseB);

        // float data type
        float taxRate = 0.07f;
        float subtotal = 20.05f;
        float tax = subtotal * taxRate;
        float total = subtotal + tax;

        System.out.println("$" + subtotal);
        System.out.println("$" + tax);
        System.out.println("-------------");
        System.out.println("$" + total);

        // byte and short data types
        byte dozen = 12;
        short century = 100;

        System.out.println("Dozen: " + dozen);
        System.out.println("Century: " + century);

        // long data type
        long bigNumber = 9100000000000000000L;

        System.out.println("Big Number: " + bigNumber);
    }
}
