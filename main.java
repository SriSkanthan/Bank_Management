import java.util.Scanner;

class Main {
    public static int from_ID; // Static variable to hold the user ID

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.println("Please verify your identity to proceed.");
        System.out.print("Please enter your ID: ");
        from_ID = scanner.nextInt();
        System.out.print("Please enter your PIN: ");
        String enteredPIN = scanner.next();

        if (verification.verifyPIN(from_ID, enteredPIN)) {
            System.out.println("Access granted. You can now use the ATM.");
            menu.main(args);
        } else {
            System.out.println("Access denied. Incorrect PIN.");
        }
        scanner.close();
    }
}