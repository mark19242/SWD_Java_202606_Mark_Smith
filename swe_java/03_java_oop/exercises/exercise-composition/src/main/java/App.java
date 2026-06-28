import java.util.Scanner;

public class App {

    static void main() {

        Scanner console = new Scanner(System.in);

        System.out.print("Enter artifact name: ");
        String artifactName = console.nextLine();

        System.out.print("Enter year of discovery: ");
        int yearOfDiscovery = Integer.parseInt(console.nextLine());

        System.out.print("Enter discoverer's first name: ");
        String discovererFirstName = console.nextLine();

        System.out.print("Enter discoverer's last name: ");
        String discovererLastName = console.nextLine();

        System.out.print("Enter discoverer's primary specialty: ");
        String discovererSpecialty = console.nextLine();

        Person discoverer = new Person(
                discovererFirstName,
                discovererLastName,
                discovererSpecialty
        );

        System.out.print("Is the discoverer also the curator? (Y/N): ");
        String answer = console.nextLine();

        Person curator;

        if (answer.equalsIgnoreCase("Y")) {
            curator = discoverer;
        } else {
            System.out.print("Enter curator's first name: ");
            String curatorFirstName = console.nextLine();

            System.out.print("Enter curator's last name: ");
            String curatorLastName = console.nextLine();

            System.out.print("Enter curator's primary specialty: ");
            String curatorSpecialty = console.nextLine();

            curator = new Person(
                    curatorFirstName,
                    curatorLastName,
                    curatorSpecialty
            );
        }

        Artifact artifact = new Artifact(
                artifactName,
                yearOfDiscovery,
                discoverer,
                curator
        );

        System.out.println();
        System.out.println(artifact);
    }
}