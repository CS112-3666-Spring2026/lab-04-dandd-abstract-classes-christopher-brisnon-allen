/**
 * GameCharacter.java : class invariant: all int values must be >= 0, name
 * exists (not null, length > 0), and class/aligment have to be a part of
 * allowed values (limited to classes fromp layers handbooks)
 * weapon 1 must exist, weapon 2 can be null
 */
public abstract class GameCharacter {
    /********** CONSTANTS ***********/
    public static final String DEFAULT_NAME = "Bob", DEFAULT_CLASS_TYPE = "Fighter", DEFAULT_ALIGNMENT = "Neutral";
    public static final int DEFAULT_GOLD = 10, DEFAULT_EXP_POINTS = 1,
            DEFAULT_HIT_POINT = 1, DEFAULT_ARMOR_CLASS = 1;
    public static final Weapon DEFAULT_WEAPON1 = new Weapon(), DEFAULT_WEAPON2 = null;

    public static final String[] VALID_CLASS_TYPES = { "Barbarian", "Bard", "Cleric", "Druid", "Fighter",
            "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard" };
    public static final String[] VALID_ALIGNMENTS = { "Lawful Good", "Neutral Good", "Chaotic Good",
            "Lawful Neutral", "Neutral", "Chaotic Neutral", "Lawful Evil", "Neutral Evil", "Chaotic Evil",
            "Unaligned" };

    /********** INSTANCE VARIABLES ***********/
    private String name, classType, alignment;
    private int gold, expPoints, hitPoint, armorClass;
    private Weapon weapon1, weapon2;

    /********** CONSTRUCTORS ***********/
    public GameCharacter(String name, String classType, String alignment, int gold, int expPoints,
            int hitPoint, int armorClass, Weapon weapon1, Weapon weapon2) {
        if (!setAll(name, classType, alignment, gold, expPoints, hitPoint, armorClass,
                weapon1, weapon2)) {
            System.out.println("ERROR: invalid data given to full GameCharacter constructor, shutting down.");
            System.exit(0);
        }
    }

    public GameCharacter() {
        this(DEFAULT_NAME, DEFAULT_CLASS_TYPE, DEFAULT_ALIGNMENT, DEFAULT_GOLD, DEFAULT_EXP_POINTS,
                DEFAULT_HIT_POINT, DEFAULT_ARMOR_CLASS, DEFAULT_WEAPON1, DEFAULT_WEAPON2);
    }

    public GameCharacter(GameCharacter original) {
        if (original == null) {
            System.out.println("ERROR: null data given to copy GameCharacter constructor, shutting down.");
            System.exit(0);
        } else {
            this.setAll(original.name, original.classType, original.alignment, original.gold, original.expPoints,
                    original.hitPoint, original.armorClass, original.weapon1, original.weapon2);
        }
    }

    /********** SETTERS / MUTATORS ***********/
    public boolean setName(String name) {
        if (name != null && name.length() > 0) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean setClassType(String classType) {
        if (GameCharacter.isInArray(VALID_CLASS_TYPES, classType)) {
            this.classType = classType;
            return true;
        } else {
            return false;
        }
    }

    public boolean setAlignment(String alignment) {
        if (GameCharacter.isInArray(VALID_ALIGNMENTS, alignment)) {
            this.alignment = alignment;
            return true;
        } else {
            return false;
        }
    }

    public boolean setGold(int gold) {
        if (gold >= 0) {
            this.gold = gold;
            return true;
        } else {
            return false;
        }
    }

    public boolean setExpPoint(int expPoints) {
        if (expPoints >= 0) {
            this.expPoints = expPoints;
            return true;
        } else {
            return false;
        }
    }

    public boolean setHitPoint(int hitPoint) {
        if (hitPoint >= 0) {
            this.hitPoint = hitPoint;
            return true;
        } else {
            return false;
        }
    }

    public boolean setArmorClass(int armorClass) {
        if (armorClass >= 0) {
            this.armorClass = armorClass;
            return true;
        } else {
            return false;
        }
    }

    public boolean setWeapon1(Weapon weapon) {
        if (weapon != null) {
            this.weapon1 = new Weapon(weapon);
            return true;
        } else {
            return false;
        }
    }

    public boolean setWeapon2(Weapon weapon) {
        if (weapon == null) {
            this.weapon2 = null;
        } else {
            this.weapon2 = new Weapon(weapon);
        }
        return true;
    }

    public boolean setAll(String name, String classType, String alignment, int gold, int expPoints,
            int hitPoint, int armorClass, Weapon weapon1, Weapon weapon2) {
        return setName(name) && setClassType(classType) && setAlignment(alignment) &&
                setGold(gold) && setExpPoint(expPoints) && setHitPoint(hitPoint) &&
                setArmorClass(armorClass) && setWeapon1(weapon1) && setWeapon2(weapon2);
    }

    /********** GETTERS / ACCESSORS ***********/
    public String getName() {
        return this.name;
    }

    public String getClassType() {
        return this.classType;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public int getGold() {
        return this.gold;
    }

    public int getExpPoints() {
        return this.expPoints;
    }

    public int getHitPoint() {
        return this.hitPoint;
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    public Weapon getWeapon1() {
        return new Weapon(this.weapon1);
    }

    public Weapon getWeapon2() {
        if (this.weapon2 == null) {
            return null;
        } else {
            return new Weapon(this.weapon2);
        }
    }

    /********* OTHER REQUIRED METHODS *********/
    @Override
    public String toString() {
        String weapons = "Equipped = {" + this.weapon1 + "}";
        if (this.weapon2 != null) {
            weapons += "+ {" + this.weapon2 + "}";
        }

        return String.format("Game Character: Name = %s, Class = %s, Alignment = %s, " +
                "Gold = %d, XP = %d, Hit Points = %d, Armor Class = %d, %n\t%s",
                this.name, this.classType, this.alignment, this.gold, this.expPoints, this.hitPoint,
                this.armorClass, weapons);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof GameCharacter)) {
            return false;
        }
        GameCharacter otherCharacter = (GameCharacter) other;
        boolean secondWeaponsEqual = (this.weapon2 == null && otherCharacter.weapon2 == null) ||
                this.weapon2.equals(otherCharacter.weapon2);

        return this.name.equals(otherCharacter.name) && this.classType.equals(otherCharacter.name) &&
                this.alignment.equals(otherCharacter.alignment) && this.gold == otherCharacter.gold &&
                this.expPoints == otherCharacter.expPoints && this.hitPoint == otherCharacter.hitPoint &&
                this.armorClass == otherCharacter.armorClass && this.weapon1.equals(otherCharacter.weapon1) &&
                secondWeaponsEqual;
    }

    /********* HELPER METHODS *********/
    // this while loop is good to remember, it will be used again in the future
    private static boolean isInArray(String[] values, String value) {
        if (values == null || value == null) {
            return false;
        }

        int location = 0;
        boolean isPresent = false;

        while (!isPresent && location < values.length) { // while not found and haven't reached the end
            isPresent = values[location].equals(value);
            location++;
        }

        return isPresent;
    }
    /********* ABSTRACT METHODS *********/
    public abstract void assist(GameCharacter other);
    public abstract boolean attack(GameCharacter other);
}
