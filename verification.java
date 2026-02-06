import java.util.List;

public class verification {
    public static boolean verifyPIN(int id, int enteredPIN) {
        List<String[]> data = csv_reader.main(null); // Read data from CSV file
        // Convert List<String[]> to 2D array if needed
        String[][] array = data.toArray(new String[0][]);

        for (String[] row : array) {
            if (row.length >= 2 && Integer.parseInt(row[0]) == id && Integer.parseInt(row[1]) == enteredPIN) {
                return true;
            }
        }
        return false; // return false if not verified
    }
}
