/**
 * Elf.java : Elf class subclass of GameCharacter, class invariant: all int values must be >= 0, name must be non-empty string, classType and alignment must be valid options
 */
public class Elf extends GameCharacter {
    /**********CONSTANTS***********/
    public static final String DEFAULT_NAME = "Varis", DEFAULT_CLASS_TYPE = "Fighter", DEFAULT_ALIGNMENT = "Neutral";
    public static final int DEFAULT_GOLD = 100, DEFAULT_EXP_POINTS = 1,
            DEFAULT_HIT_POINT = 1, DEFAULT_ARMOR_CLASS = 1;
    public static final Weapon DEFAULT_WEAPON1 = new Weapon("Longbow", 8, 150, 2), DEFAULT_WEAPON2 = null;

    /**********INSTANCE VARIABLES***********/
    /**********CONSTRUCTORS***********/
    public Elf(String name, String classType, String alignment, int gold, int expPoints,
            int hitPoint, int armorClass, Weapon weapon1, Weapon weapon2) {
            super(name, classType, alignment, gold, expPoints, hitPoint, armorClass, weapon1, weapon2);
    }

    public Elf() {
        this(Elf.DEFAULT_NAME, Elf.DEFAULT_CLASS_TYPE, Elf.DEFAULT_ALIGNMENT, Elf.DEFAULT_GOLD, Elf.DEFAULT_EXP_POINTS,
                Elf.DEFAULT_HIT_POINT, Elf.DEFAULT_ARMOR_CLASS, Elf.DEFAULT_WEAPON1, Elf.DEFAULT_WEAPON2);
    }

    /**********SETTERS / MUTATORS***********/
    /**********GETTERS / ACCESSORS***********/
    /********* OTHER REQUIRED METHODS *********/
    @Override
    /**
    * If param is Elf then heal, otherwise give some gold
    *
    * @param other GameCharacter to help, will modify original object (HP or gold)
    */
    public void assist(GameCharacter other) {
        if (other instanceof Elf) {
            int healed = other.getHitPoint() + 10;
            other.setHitPoint(healed);
            System.out.println("Assisted by healing 10");

        } else {
            int goldUpdated = other.getGold() + 5;
            other.setGold(goldUpdated);
            System.out.println("Assisted by giving 5 gold");
        }
    }

    /**
    * If param is Elf then more likely to hit but less damage, else less likely to hit but more damage
    *
    * @param other GameCharacter to attack, will modify original object HP only
    * 
    * @return boolean indicating if attack was successful // could change this to into for more info
    */
    @Override
    public boolean attack(GameCharacter other) {
        int diceRoll = (int) (Math.random() * 20) + 1;
        //calculate base damage
        int attackDamage = this.getExpPoints() / other.getArmorClass();
        int updatedHitPoint = other.getHitPoint();

        //modify damage based on character type
        if (other instanceof Elf) {
            attackDamage /= 2;
            diceRoll += 5;

        }

        //if over damage, make sure the HP doesn't go below 0
        if (attackDamage > updatedHitPoint) {
            attackDamage = updatedHitPoint;
        }
        //determine if hit successful, if so update HP appropriately
        if (diceRoll > 10) {
            updatedHitPoint -= attackDamage;
            other.setHitPoint(updatedHitPoint);
            return true;
        } else {
            return false;
        }
        /********* HELPER METHODS *********/
        /********* ABSTRACT METHODS *********/
    }
}