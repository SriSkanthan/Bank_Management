import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class verification {
    public static boolean verifyPIN(int id, int enteredPIN) {
        // read the csv file from main_dataset.csv and verify the id and pin
        List<String[]> data = new ArrayList<>();
        String csvFile = "main_dataset.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
