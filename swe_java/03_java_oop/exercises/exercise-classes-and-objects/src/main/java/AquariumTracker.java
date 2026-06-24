import model.AquariumFish;
import model.Aquarium;

import java.util.Scanner;

public class AquariumTracker {
    public static void main(String[] args) {

        boolean validInput = false;

        Scanner console = new Scanner(System.in);

        Aquarium tank = new Aquarium();
        AquariumFish af1 = new AquariumFish();


        System.out.println("Enter the information for your aquarium.");

        System.out.print("Tank Name: ");
        tank.setTankName(console.nextLine());

        System.out.print("Tank Size: ");
        tank.setTankSize(console.nextLine());

        System.out.print("Tank Temperature: ");
        tank.setTankTemp(Double.valueOf(console.nextLine()));

        System.out.print("Tank Status: ");
        tank.setTankStatus(console.nextLine());

        System.out.println("Enter the information for your fish.");

        System.out.print("Species Name: ");
        af1.setSpecies(console.nextLine());

        System.out.print("Common Name: ");
        af1.setCommonName(console.nextLine());

        do {
            System.out.print("Maximum temperature: ");
            String temp = console.nextLine();

            try {
                af1.setMaxTemp(Double.valueOf(temp));
                validInput = true;
            } catch (IllegalArgumentException iax) {
                System.out.println(iax.getMessage());
                validInput = false;
            } catch (Exception e) {
                System.out.println("Maximum Temperature is invalid");
                validInput = false;
            }
        } while (!validInput);

        do {
            System.out.print("Minimum temperature: ");
            String temp = console.nextLine();

            try {
                af1.setMinTemp(Double.valueOf(temp));
                validInput = true;
            } catch (IllegalArgumentException iax) {
                System.out.println(iax.getMessage());
                validInput = false;
            } catch (Exception e) {
                System.out.println("Minimum Temperature is invalid");
                validInput = false;
            }
        } while (!validInput);

        System.out.println("Diet: ");
        af1.setDiet(console.nextLine());


        System.out.println("\n==============================");
        System.out.println("     AQUARIUM SUMMARY");
        System.out.println("==============================");
        System.out.println("\nAquarium Summary");
        System.out.println("Tank Name: " + tank.getTankName());
        System.out.println("Tank Size: " + tank.getTankSize());
        System.out.println("Tank Temperature: " + tank.getTankTemp());
        System.out.println("Tank Status: " + tank.getTankStatus());
        System.out.println("Tank Ready: " + tank.isTankReady());

        System.out.println("\n==============================");
        System.out.println("       FISH SUMMARY");
        System.out.println("==============================");
        System.out.println("Thank you for the input, here is the summary.");
        System.out.println("Species name: " + af1.getSpecies());
        System.out.println("Common name: " + af1.getCommonName());
        System.out.println("Average Temperature: " + af1.averageTemp());

        tank.setFish(af1);

        System.out.println("\n==============================");
        System.out.println("ASSOCIATION COMPLETE");
        System.out.println("==============================");
        System.out.println("Fish successfully added to aquarium: " + tank.getTankName());
    }
}