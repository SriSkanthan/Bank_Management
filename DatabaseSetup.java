import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseSetup {
    public static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                "customer_id SERIAL PRIMARY KEY, " +
                "pin VARCHAR(10) NOT NULL, " +
                "balance DECIMAL(10,2) NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.execute();
            System.out.println("Table 'accounts' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void migrateDataFromCSV() {
        // Assuming csv_reader is still available for migration
        List<String[]> data = csv_reader.main(null);
        String insertSQL = "INSERT INTO accounts (customer_id, pin, balance) VALUES (?, ?, ?) ON CONFLICT (customer_id) DO NOTHING";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            for (String[] row : data) {
                if (row.length >= 3) {
                    stmt.setInt(1, Integer.parseInt(row[0]));
                    stmt.setString(2, row[1]);
                    stmt.setDouble(3, Double.parseDouble(row[2]));
                    stmt.executeUpdate();
                }
            }
            System.out.println("Data migrated from CSV to PostgreSQL successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}