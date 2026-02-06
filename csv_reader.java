import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csv_reader {
    public static List<String[]> main(String[] args) {
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

        return data; // return the data read from the csv file
    }
}
