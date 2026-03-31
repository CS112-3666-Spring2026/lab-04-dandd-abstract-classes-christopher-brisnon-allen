/**
 * Dwarf.java : Dwarf class subclass of GameCharacter, class invariant: all int values must be >= 0, name must be non-empty string, classType and alignment must be valid options
 */
public class Dwarf extends GameCharacter {
      /**********CONSTANTS***********/
    public static final String DEFAULT_NAME = "Loris", DEFAULT_CLASS_TYPE = "Cleric", DEFAULT_ALIGNMENT = "Lawful Good";
    public static final int DEFAULT_GOLD = 1000, DEFAULT_EXP_POINTS = 2,
            DEFAULT_HIT_POINT = 5, DEFAULT_ARMOR_CLASS = 2;
    public static final Weapon DEFAULT_WEAPON1 = new Weapon("Mace", 6, 1, 2), DEFAULT_WEAPON2 = null;

    /**********INSTANCE VARIABLES***********/
    /**********CONSTRUCTORS***********/
    public Dwarf(String name, String classType, String alignment, int gold, int expPoints,
            int hitPoint, int armorClass, Weapon weapon1, Weapon weapon2) {
            super(name, classType, alignment, gold, expPoints, hitPoint, armorClass, weapon1, weapon2);
    }

    public Dwarf() {
        this(Dwarf.DEFAULT_NAME, Dwarf.DEFAULT_CLASS_TYPE, Dwarf.DEFAULT_ALIGNMENT, Dwarf.DEFAULT_GOLD,
                Dwarf.DEFAULT_EXP_POINTS,
                Dwarf.DEFAULT_HIT_POINT, Dwarf.DEFAULT_ARMOR_CLASS, Dwarf.DEFAULT_WEAPON1, Dwarf.DEFAULT_WEAPON2);
    }
    
    public Dwarf(Dwarf original) {
        super(original);
    }

    /**********SETTERS / MUTATORS***********/
    /**********GETTERS / ACCESSORS***********/
    /********* OTHER REQUIRED METHODS *********/
    @Override
    /**
    * If param is Dwarf then heal, otherwise give some gold
    *
    * @param other GameCharacter to help, will modify original object (HP or gold)
    */
    public void assist(GameCharacter other) {
        if (other instanceof Dwarf) {
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
    * If param is Dwarf then more likely to hit but less damage, else less likely to hit but more damage
    *
    * @param other GameCharacter to attack, will modify original object HP only
    * 
    * @return boolean indicating if attack was successful // could change this to into for more info
    */

    public boolean attack(GameCharacter other) {
        int diceRoll = (int) (Math.random() * 20) + 1;
        //calculate base damage
        int attackDamage = this.getExpPoints() / other.getArmorClass();
        int updatedHitPoint = other.getHitPoint();

        //modify damage based on character type
        if (other instanceof Dwarf) {
            attackDamage = this.getExpPoints() / other.getArmorClass();
        } else {
            attackDamage = this.getExpPoints() * this.getArmorClass() / other.getArmorClass();
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
