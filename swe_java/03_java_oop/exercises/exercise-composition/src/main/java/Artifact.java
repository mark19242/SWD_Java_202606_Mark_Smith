public class Artifact {

    private String name;
    private int yearOfDiscovery;
    private Person discoverer;
    private Person curator;

    public Artifact(String name, int yearOfDiscovery, Person discoverer, Person curator) {
        this.name = name;
        this.yearOfDiscovery = yearOfDiscovery;
        this.discoverer = discoverer;
        this.curator = curator;
    }

    public String getName() {
        return name;
    }

    public int getYearOfDiscovery() {
        return yearOfDiscovery;
    }

    public Person getDiscoverer() {
        return discoverer;
    }

    public Person getCurator() {
        return curator;
    }

    public String toString() {
        return "Artifact: " + name + " (" + yearOfDiscovery + ")\n"
                + "Discoverer: " + discoverer + "\n"
                + "Curator: " + curator;
    }
}