import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLReader {
    public static List<String[]> readAccounts() {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT customer_id, pin, balance FROM accounts";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] row = new String[3];
                row[0] = String.valueOf(rs.getInt("customer_id"));
                row[1] = rs.getString("pin");
                row[2] = String.valueOf(rs.getDouble("balance"));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}