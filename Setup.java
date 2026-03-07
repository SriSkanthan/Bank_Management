public class Setup {
    public static void main(String[] args) {
        // Setup the database
        DatabaseSetup.createTable();
        DatabaseSetup.migrateDataFromCSV();
        System.out.println("Database setup complete. You can now run the main application.");
    }
}