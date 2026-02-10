import java.util.Scanner;

public class menu {
    public static void main(String[] args) {
        int id = Main.from_ID; // Accessing the static ID from Main class
        account account = new account();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM Menu!");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Exit");

        // Additional menu functionality can be implemented here for example: like the
        // if else statemnts to handle user choices.
        int choices = 0; // Placeholder for user choice input
        switch (choices) {
            case 1:
                System.out.println("You selected Withdraw Money.");
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                account.withdraw(id, amount);
                break;
            case 2:
                System.out.println("You selected Deposit Money.");
                System.out.print("Enter amount to deposit: ");
                amount = scanner.nextDouble();
                account.deposit(id, amount);
                break;
            case 3:
                System.out.println("You selected Check Balance.");
                account.checkBalance(id);
                break;
            case 4:
                System.out.println("You selected Transfer Funds.");
                System.out.print("Enter amount to transfer: ");
                amount = scanner.nextDouble();
                System.out.print("Enter recipient account ID: ");
                int to_ID = scanner.nextInt();
                account.deposit(to_ID, amount);
                account.withdraw(id, amount);
                break;
            case 5:
                System.out.println("Exiting the ATM. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        scanner.close();

    }
}
