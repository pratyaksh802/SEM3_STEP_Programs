abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public abstract void castMagicSpell();
}

class WizardTower extends MagicalStructure {
    private int spellCapacity;

    public WizardTower(String name, int magicPower, String location, int spellCapacity) {
        super(name, magicPower, location, true);
        this.spellCapacity = spellCapacity;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " casts a powerful spell!");
    }
}

class EnchantedCastle extends MagicalStructure {
    private int defenseRating;

    public EnchantedCastle(String name, int magicPower, String location, int defenseRating) {
        super(name, magicPower, location, true);
        this.defenseRating = defenseRating;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " activates defensive magic!");
    }
}

class MysticLibrary extends MagicalStructure {
    private int bookCount;

    public MysticLibrary(String name, int magicPower, String location, int bookCount) {
        super(name, magicPower, location, true);
        this.bookCount = bookCount;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " releases knowledge magic!");
    }
}

class DragonLair extends MagicalStructure {
    private String dragonType;

    public DragonLair(String name, int magicPower, String location, String dragonType) {
        super(name, magicPower, location, true);
        this.dragonType = dragonType;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " dragon breathes fire!");
    }
}

class KingdomManager {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
               (s2 instanceof WizardTower && s1 instanceof MysticLibrary) ||
               (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) ||
               (s2 instanceof EnchantedCastle && s1 instanceof DragonLair);
    }

    public static void main(String[] args) {
        // Create magical structures
        WizardTower tower = new WizardTower("MageTower", 100, "North", 5);
        EnchantedCastle castle = new EnchantedCastle("RoyalFort", 80, "East", 50);
        MysticLibrary library = new MysticLibrary("ArcaneLib", 60, "West", 300);
        DragonLair lair = new DragonLair("FireLair", 120, "South", "Red Dragon");

        // Demonstrate spell casting
        System.out.println("--- Casting Spells ---");
        tower.castMagicSpell();
        castle.castMagicSpell();
        library.castMagicSpell();
        lair.castMagicSpell();

        // Test structure interactions
        System.out.println("\n--- Structure Interactions ---");
        System.out.println("Tower and Library interact? " + canStructuresInteract(tower, library));
        System.out.println("Castle and Lair interact? " + canStructuresInteract(castle, lair));
        System.out.println("Tower and Castle interact? " + canStructuresInteract(tower, castle));
        
        // Additional interaction test
        System.out.println("\n--- Additional Tests ---");
        System.out.println("Library and Tower interact? " + canStructuresInteract(library, tower));
        System.out.println("Lair and Castle interact? " + canStructuresInteract(lair, castle));
        
        // Test with same type of structures
        System.out.println("\n--- Same Type Tests ---");
        WizardTower anotherTower = new WizardTower("ArcaneSpire", 90, "Northwest", 4);
        System.out.println("Tower and Another Tower interact? " + 
                          canStructuresInteract(tower, anotherTower));
    }
}
