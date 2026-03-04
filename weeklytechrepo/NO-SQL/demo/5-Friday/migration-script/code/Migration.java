package com.revature.nosql.demo.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
// Note: In an actual project, you would import the DataStax CqlSession here.

public class Migration {

    public static void main(String[] args) {
        System.out.println("Starting SQL to NoSQL ETL Pipeline...");

        // Note: This is pseudo-code for theoretical explanation purposes in the
        // curriculum.
        // It demonstrates the architectural flow of a DB migration script.

        try {
            // 1. EXTRACT: Connect to Legacy Relational Database
            System.out.println("Connecting to Legacy MySQL Database...");
            Connection mysqlConn = DriverManager.getConnection("jdbc:mysql://localhost/legacy_company", "root",
                    "password");
            Statement statement = mysqlConn.createStatement();

            // 2. EXTRACT: Pull all the relational records
            ResultSet rs = statement.executeQuery("SELECT user_id, first_name, last_name, country FROM legacy_users");

            // 3. Setup connection to Cassandra Cluster (Pseudo-code)
            System.out.println("Connecting to new Cassandra Cluster...");
            // CqlSession cassandraSession = CqlSession.builder().build();

            // 4. TRANSFORM & LOAD: Iterate over the ResultSet
            int rowCount = 0;
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                String country = rs.getString("country");

                // 5. TRANSFORM: Reshape the data for our 'users_by_country' query-driven model
                // Note: We are migrating from a system where 'user_id' was the primary key,
                // to a system where 'country' is the partition key!

                // 6. LOAD: Insert into Cassandra
                String cqlInsert = String.format(
                        "INSERT INTO store.users_by_country (country_code, user_id, first_name, last_name) VALUES ('%s', %d, '%s', '%s')",
                        country, id, first, last);

                // Execute the CQL
                // cassandraSession.execute(cqlInsert);
                rowCount++;
            }

            System.out.println("Migration Complete. Successfully migrated " + rowCount + " rows.");

            mysqlConn.close();
            // cassandraSession.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
