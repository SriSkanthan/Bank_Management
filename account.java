import java.util.List;

public class account {
    List<String[]> data = csv_reader.main(null); // Read data from CSV file
    // Convert List<String[]> to 2D array if needed
    String[][] array = data.toArray(new String[0][]);

    void deposit(int accountID, double amount) {
        // Implementation for deposit
        if (amount > 0) {
            // Update balance logic here
            for (String[] row : array) {
                if (row.length >= 2 && Integer.parseInt(row[0]) == accountID) {
                    double currentBalance = Double.parseDouble(row[2]);
                    double newBalance = currentBalance + amount;
                    row[2] = String.valueOf(newBalance);
                    // Write updated data back to CSV if needed
                    csv_writer.main(null); // Call the writer method to update CSV
                }
            }

        }
    }

    void withdraw(int accountID, double amount) {
        // Implementation for withdraw
        for (String[] row : array) {
            if (row.length >= 2 && Integer.parseInt(row[0]) == accountID) {
                double currentBalance = Double.parseDouble(row[2]);
                if (amount > 0 && amount <= currentBalance) {
                    double newBalance = currentBalance - amount;
                    row[2] = String.valueOf(newBalance);
                    // Write updated data back to CSV if needed
                    csv_writer.main(null); // Call the writer method to update CSV
                }
            }
        }
    }

    double checkBalance(int accountID) {
        // Implementation for checking balance
        for (String[] row : array) {
            if (row.length >= 2 && Integer.parseInt(row[0]) == accountID) {
                return Double.parseDouble(row[2]);
            }
        }
        return 0.0; // Placeholder return value
    }
}
