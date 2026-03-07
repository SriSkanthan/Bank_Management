import java.util.List;

public class account {
    // Removed data loading in constructor, will read from DB as needed

    void deposit(int accountID, double amount) {
        // Implementation for deposit
        if (amount > 0) {
            List<String[]> data = PostgreSQLReader.readAccounts();
            for (String[] row : data) {
                if (row.length >= 3 && Integer.parseInt(row[0]) == accountID) {
                    double currentBalance = Double.parseDouble(row[2]);
                    double newBalance = currentBalance + amount;
                    PostgreSQLWriter.updateBalance(accountID, newBalance);
                    break;
                }
            }
        }
    }

    void withdraw(int accountID, double amount) {
        // Implementation for withdraw
        List<String[]> data = PostgreSQLReader.readAccounts();
        for (String[] row : data) {
            if (row.length >= 3 && Integer.parseInt(row[0]) == accountID) {
                double currentBalance = Double.parseDouble(row[2]);
                if (amount > 0 && amount <= currentBalance) {
                    double newBalance = currentBalance - amount;
                    PostgreSQLWriter.updateBalance(accountID, newBalance);
                }
                break;
            }
        }
    }

    double checkBalance(int accountID) {
        // Implementation for checking balance
        List<String[]> data = PostgreSQLReader.readAccounts();
        for (String[] row : data) {
            if (row.length >= 3 && Integer.parseInt(row[0]) == accountID) {
                return Double.parseDouble(row[2]);
            }
        }
        return 0.0; // Placeholder return value
    }
}
