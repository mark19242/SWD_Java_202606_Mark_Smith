package MainApp;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        String name = "Mary";

        for (int i = 0; i <= 100; i++) {

            System.out.println(name + i);

        }

        // Primitive (Value) Types

        boolean isOn = false;      // Go-to choice for truth (True or False)
        byte byteVal = 120;        // Used sometimes for small numbers
        char singleCharacter = 'G'; // Used sometimes for single character needs
        short smallInt = 32550;    // When you need an int guaranteed to be less than 32k
        int counter = 21000100;    // Go-to choice for general purpose integers
        long bigInt = 12312313L;   // Maybe if you know you have excessively big whole numbers
        float floatTemp = 98.6f;   // Rarely used
        double doubleTemp = 98.6;  // Go-to choice for decimal precision

        // Reference (Object) Types

//        String name = "Bob";

        String upperName = name.toUpperCase();

        System.out.println(upperName);

        for (int i = 0; i < 20; i++) {

            System.out.println("Byte Value is: " + byteVal);

            byteVal++;
        }

        // byteVal = byteVal + counter;
        // This would cause a type mismatch error because
        // counter is an int and byteVal is a byte.
        boolean isOn = false;

        if (isOn == false) {

            System.out.println("It's dark in here");

        }
        else {

            System.out.println("Grab some shades");

        }

        if (isOn == false) {

            isOn = true;

            System.out.println("Turning on the lights");

        }

        if (isOn == false) {

            System.out.println("It's dark in here");

        }
        else {

            System.out.println("Grab some shades");

        }

    }
}