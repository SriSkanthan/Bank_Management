import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseSetup {

    public static void main(String[] args) {
        System.out.println("Starting Bank Management Database Synchronization...");

        // 1. Create the table first
        createTable();

        // 2. Run the migration second
        migrateDataFromCSV();
    }

    public static void createTable() {
        // Changed customer_id to INT because you are explicitly importing IDs from your
        // CSV
        String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                "customer_id INT PRIMARY KEY, " +
                "pin VARCHAR(10) NOT NULL, " +
                "balance DECIMAL(10,2) NOT NULL);";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {

            stmt.execute();
            System.out.println("Table 'accounts' verified/created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void migrateDataFromCSV() {
        // Assuming csv_reader is your class handling file logic
        List<String[]> data = csv_reader.main(null);

        String insertSQL = "INSERT INTO accounts (customer_id, pin, balance) " +
                "VALUES (?, ?, ?) ON CONFLICT (customer_id) DO NOTHING;";

        // Using try-with-resources to ensure connection closes safely
        try (Connection conn = DatabaseConnection.getConnection()) {

            // Turn off auto-commit for performance optimization
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                int count = 0;

                for (String[] row : data) {
                    if (row.length >= 3) {
                        stmt.setInt(1, Integer.parseInt(row[0].trim()));
                        stmt.setString(2, row[1].trim());
                        stmt.setDouble(3, Double.parseDouble(row[2].trim()));

                        // Add to batch instead of hitting the database line-by-line
                        stmt.addBatch();
                        count++;

                        // Push data to DB in chunks of 1000 rows
                        if (count % 1000 == 0) {
                            stmt.executeBatch();
                        }
                    }
                }

                // Execute any remaining rows
                stmt.executeBatch();

                // Commit the total transaction safely
                conn.commit();
                System.out.println("Data migrated from CSV to PostgreSQL successfully! Total records: " + count);

            } catch (SQLException e) {
                System.err.println("Error during data insertion. Rolling back changes...");
                conn.rollback(); // Undo everything if a single row breaks
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}