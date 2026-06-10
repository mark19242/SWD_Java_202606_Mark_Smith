package codealong.operators;

public class Operators {

    public static void main(String[] args) {

        // Assignment
        int customerAge = 20;

        // Comparison Expression
        boolean isMinor = (customerAge >= 18);

        // Basic Math
        int birdsInTree = 30;
        int birdsFlyWay = 14;

        int birdsRemaining = birdsInTree - birdsFlyWay;

        System.out.println("There are " + birdsRemaining + " birds left in the tree");

        int bottlesPerCarton = 24;
        int numberOfCartons = 5;

        int totalBottles = bottlesPerCarton * numberOfCartons;

        System.out.println("Total bottles on hand: " + totalBottles);

        int locations = 2;

        System.out.println("Locations served: " + locations);

        // Type Considerations

        // Integer Math - Only Whole Numbers Returned
        int evenSplitOfCartons = (numberOfCartons / locations);

        System.out.println(
                "Each Location equal allotment of cartons: "
                        + evenSplitOfCartons
        );

        // GOTCHA!
        // Anything combined with an int within an expression
        // or using operators is promoted to a double and
        // WILL NOT fit in an int variable!

        // evenSplitOfCartons = (numberOfCartons / 3.5);

        System.out.println(
                "If I could break a carton (and bottles) evenly: "
                        + (numberOfCartons / (locations + .3))
        );

        // Mod Operator
        int extraCartons = numberOfCartons % locations;

        System.out.println(
                "Cartons to keep on hand because I cannot break cartons: "
                        + extraCartons
        );

        // Double Example
        double moneyOnHand = 1.25;

        int friends = 5;

        System.out.println(
                "Each friend gets: "
                        + (moneyOnHand / friends)
        );

        // Increment Operators

        double money = 3.5;

        System.out.println("I start with: " + money);

        money++;

        System.out.println(
                "Incrementing money with ++ as a discrete step: "
                        + money
        );

        System.out.println(
                "Incrementing money with ++ inline (postfix): "
                        + money++
        ); // Reads then increments

        System.out.println("Money is now: " + money);

        System.out.println(
                "Incrementing money with ++ inline (unary): "
                        + ++money
        ); // Increments then reads

        System.out.println("Money is now: " + money);

        // Logical Operators

        boolean isAdult; // Declaration only

        int guestAge = 34;

        isAdult = guestAge >= 18;

        boolean hasTicket = true;

        System.out.println("Are they adult: " + isAdult);

        // Logical AND

        if (isAdult && hasTicket) {
            System.out.println("Ticketed Adult");
        } else {
            System.out.println(
                    "NO GO: You need to be adult AND have a ticket"
            );
        }

        // Logical OR

        if (isAdult || hasTicket) {
            System.out.println(
                    "They are either of age or have a pass or both"
            );
        } else {
            System.out.println(
                    "They have neither condition"
            );
        }

        // Logical XOR (^)

        if (isAdult ^ hasTicket) {
            System.out.println(
                    "Customer has either Age OR Pass, and only one or the other"
            );
        }
    }
}