import java.util.*;

class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int stageIndex;
    private static int totalPetsCreated = 0;
    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};

    public VirtualPet() {
        this("MysteryPet", "Unknown");
    }

    public VirtualPet(String petName) {
        this(petName, "RandomSpecies");
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 0, 50, 50, 1);
    }

    public VirtualPet(String petName, String species, int age, int happiness, int health, int stageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stageIndex = stageIndex;
        totalPetsCreated++;
    }

    public static String generatePetId() {
        return "PET-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public void evolvePet() {
        if (health > 0 && stageIndex < EVOLUTION_STAGES.length - 1) {
            stageIndex++;
            System.out.println(petName + " evolved into " + EVOLUTION_STAGES[stageIndex]);
        }
    }

    public void feedPet() {
        health += 10;
        System.out.println(petName + " was fed. Health increased!");
    }

    public void playWithPet() {
        happiness += 10;
        System.out.println(petName + " played happily. Happiness increased!");
    }

    public void healPet() {
        health += 20;
        System.out.println(petName + " was healed.");
    }

    public void simulateDay() {
        age++;
        health -= 5;
        happiness -= 5;
        if (health <= 0) {
            System.out.println(petName + " has died and turned into a Ghost.");
            species = "Ghost";
        }
    }

    public void getPetStatus() {
        System.out.println("ID: " + petId + 
                         " | Name: " + petName + 
                         " | Species: " + species + 
                         " | Age: " + age + 
                         " | Happiness: " + happiness + 
                         " | Health: " + health + 
                         " | Stage: " + EVOLUTION_STAGES[stageIndex]);
    }

    public static void main(String[] args) {
        VirtualPet pet1 = new VirtualPet();
        VirtualPet pet2 = new VirtualPet("Buddy");
        VirtualPet pet3 = new VirtualPet("Charlie", "Dragon");

        // Simulate 3 days
        for (int i = 0; i < 3; i++) {
            pet1.simulateDay();
            pet2.playWithPet();
            pet3.feedPet();
            
            // Try to evolve pets
            pet1.evolvePet();
            pet2.evolvePet();
            pet3.evolvePet();
        }

        // Show final status of all pets
        pet1.getPetStatus();
        pet2.getPetStatus();
        pet3.getPetStatus();
    }
}
