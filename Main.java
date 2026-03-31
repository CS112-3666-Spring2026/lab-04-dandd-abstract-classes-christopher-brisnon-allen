/********************************************
*	AUTHORS: Christopher Brinson-Allen - CBA
*   COLLABORATORS: Nadia Arani
*	LAST MODIFIED:	03/31/2026
*********************************************
*	DnD Lab
*********************************************
*	PROGRAM DESCRIPTION:
* A text-based character selection based on DND. Player will choose through a small menu
* what character they want to go on an adventure with.
*********************************************
*	ALGORITHM:
* 1. Display welcome message
* 2. Display character options
* 3. Get user choice and create their character
* 4. Display character status
* 5. Confirm choice, and if not confirm, restart character selection
*********************************************/
import java.util.Scanner;

public class Main {
    /**
     * Displays the current status of player
     * @param gameCharacter The player character
     */
    public static void displayStatus(GameCharacter gameCharacter) {
        System.out.println("\nCurrent Status:");
        System.out.println("Health: " + gameCharacter.getHitPoint());
        System.out.println("Experience Points: " + gameCharacter.getExpPoints());
        System.out.println("Armor Class: " + gameCharacter.getArmorClass());
        System.out.println("Weapon 1: " + gameCharacter.getWeapon1());

        //displays weapon 2 if it exists
        Weapon weapon2 = gameCharacter.getWeapon2();
        if (weapon2 != null) {
            System.out.println("Weapon 2: " + weapon2);
        } else {
            System.out.println("Weapon 2: None");
        }

        System.out.println("Alignment: " + gameCharacter.getAlignment());
        System.out.println("Class Type: " + gameCharacter.getClassType());
        //displays character subclass
        if (gameCharacter instanceof Dwarf) {
                System.out.println("Character Type: Dwarf");
            } else if (gameCharacter instanceof Elf) {
                System.out.println("Character Type: Elf");
            } else {
                System.out.println("Character Type: Unknown");
            }

        System.out.println("Name: " + gameCharacter.getName());
        System.out.println("Gold: " + gameCharacter.getGold());
    }
    /**
     * Displays a menu of options and returns the user's choice
     * @param options Array of menu options to display
     * @return The index of the selected option
     */
    public static int displayMenu(String[] options) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nChoose your action:");
        // Display all options
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        
        // Get valid input
        int choice;
        do {
            System.out.print("Enter your choice (1-" + options.length + "): ");
            choice = input.nextInt(); // should handle exceptions
        } while (choice < 1 || choice > options.length);
        
        return choice - 1; // -1 to handle array
        
    }

    public static void main(String[] args) {

        //Initialize scanner for user input
        Scanner input = new Scanner(System.in);

        //Initiliaze user options

        GameCharacter[] characterOptions = new GameCharacter[3];
        characterOptions[0] = new Dwarf("Alice", "Barbarian", "Lawful Good", 100, 20, 30, 10,
                new Weapon("Axe", 12, 1, 3), null);
        characterOptions[1] = new Elf("Charlie", "Rogue", "Chaotic Good", 80, 15, 25, 8, new Weapon("Dagger", 8, 1, 1),
                null);
        characterOptions[2] = new Dwarf("Bob", "Fighter", "Unaligned", 50, 10, 20, 5, new Weapon("Sword", 10, 1, 2),
                null);

        System.out.println("Welcome to the RPG Game!");
        System.out.println("Please choose your character!");

        //Display character options
        for (int i = 0; i < characterOptions.length; i++) {
            System.out
                    .println((i + 1) + ". " + characterOptions[i].getName() + " the "
                            + characterOptions[i].getClassType());
        }

        //Get user choice and create their character
        int choice;
        do {
            System.out.print("Enter your choice (1-" + characterOptions.length + "): ");
            choice = input.nextInt(); // should handle exceptions
        } while (choice < 1 || choice > characterOptions.length);

        //store reference to the selected character
        GameCharacter player = characterOptions[choice - 1];
        System.out.println("\nYou have chosen " + player.getName() + " the " + player.getClassType() + "!");
        displayStatus(player);

        //confirm choice, and if not confirm, restart character selection
        System.out.print("Are you sure you want to play as " + player.getName()
                + " the " + player.getClassType() + "? (y/n): ");
        String confirm = input.next();
        if (!confirm.equalsIgnoreCase("y")) {
            main(args); // restart character selection
            return; // exit current method to avoid running the rest of the code
        }
    }
}


