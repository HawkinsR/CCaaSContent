import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PullRequest {

    // 1. HARDCODED SECRET VIOLATION
    // Credentials should never be hardcoded in source code!
    // They belong in environment variables or a secret manager.
    private static final String DB_URL = "jdbc:mysql://production-db.company.com:3306/users";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "SuperSecretPassword123!";

    public boolean registerNewUser(String username, String password, String ssn) {

        // 2. PRIVACY / COMPLIANCE VIOLATION
        // This logs a plain text password and a Social Security Number to the
        // console/server logs.
        // This violates PCI-DSS, HIPAA, and GDPR.
        System.out.println("DEBUG: Attempting to register user: " + username);
        System.out.println("DEBUG: Password: " + password);
        System.out.println("DEBUG: SSN: " + ssn);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();

            // 3. SEVERE SECURITY VULNERABILITY: SQL INJECTION
            // The AI simply concatenated user input directly into the SQL string.
            // An attacker could submit an SSN of: "000-00-0000'); DROP TABLE users; --"
            String sql = "INSERT INTO users (username, passhash, ssn) VALUES ('" +
                    username + "', '" +
                    password + "', '" +
                    ssn + "')";

            stmt.executeUpdate(sql);
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
