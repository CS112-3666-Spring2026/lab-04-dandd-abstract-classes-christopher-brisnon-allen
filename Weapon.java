/**
 * Weapon.java : class invariant: name bust be present (not null or lenght 0),
 * and weapon stats >= 
 */
public class Weapon {
    /**********CONSTANTS***********/
    public static final String DEFAULT_NAME = "Club";
    public static final int DEFAULT_DAMAGE = 1, DEFAULT_RANGE = 1, DEFAULT_ATTACK_BONUS = 0;
    /**********INSTANCE VARIABLES***********/
    private String name;
    private int damage, range, attackBonus;
    /**********CONSTRUCTORS***********/
    public Weapon(String name, int damage, int range, int attackBonus) {
        if (!this.setAll(name, damage, range, attackBonus)) {
            System.out.println("ERROR: invalid data given to full Weapon constructor. Shutting down.");
            System.exit(0);
        }
    }

    public Weapon() {
        this(DEFAULT_NAME, DEFAULT_DAMAGE, DEFAULT_RANGE, DEFAULT_ATTACK_BONUS);
    }

    public Weapon(Weapon original) {
        if (original == null) {
            System.out.println("Error: null data given to copy Weapon constructor. Shutting down.");
            System.exit(0);
        } else {
            this.setAll(original.name, original.damage, original.range, original.attackBonus);
        }
    }
    
    /**********SETTERS / MUTATORS***********/
    public boolean setName(String name) {
        if(name != null && name.length() != 0) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean setDamage(int damage) {
        if (damage >= 0) {
            this.damage = damage;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean setRange(int range) {
        if (range >= 0) {
            this.range = range;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean setAttackBonus(int attackBonus) {
        if (attackBonus >= 0) {
            this.attackBonus = attackBonus;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean setAll(String name, int damage, int range, int attackBonus) {
        return this.setName(name) && this.setDamage(damage) &&
                this.setRange(range) && this.setAttackBonus(attackBonus);
    }
    
    /**********GETTERS / ACCESSORS***********/
    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getRange() {
        return this.range;
    }

    public int getAttackBonus() {
        return this.attackBonus;
    }
    
    /*********OTHER REQUIRED METHODS*********/
    @Override
    public String toString() {
        return String.format("Weapon: %s, Damage: %d, Range: %d, Attack Bonus: %d",
                this.name, this.damage, this.range, this.attackBonus);
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Weapon otherWeapon = (Weapon)other;
        return this.name.equals(otherWeapon.name) && this.damage == otherWeapon.damage &&
                this.range == otherWeapon.range && this.attackBonus == otherWeapon.attackBonus;
    }
}
