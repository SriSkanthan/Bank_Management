import java.util.List;

public class verification {
    public static boolean verifyPIN(int id, String enteredPIN) {
        List<String[]> data = PostgreSQLReader.readAccounts(); // Read data from PostgreSQL database
        // Convert List<String[]> to 2D array if needed
        String[][] array = data.toArray(new String[0][]);

        for (String[] row : array) {
            if (row.length >= 2 && Integer.parseInt(row[0]) == id && row[1].equals(enteredPIN)) {
                return true;
            }
        }
        return false; // return false if not verified
    }
}
