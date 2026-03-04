package com.revature.jndidemo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * A simulated Servlet or Application Component performing a JNDI Lookup.
 * This class assumes the Server Administrator has already bound the resource.
 */
public class JndiLookupServlet {

    public void processRequest() {
        System.out.println("Application attempting to retrieve Database Connection...");

        try {
            // 1. Obtain the Initial Environment Context (The "Phone Book")
            Context initCtx = new InitialContext();

            // 2. Look up our specific local environment
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            // 3. Perform the actual lookup using the precise string name
            // The method returns a generic Java Object, so we must explicitly cast it.
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MyDataSource");

            System.out.println("Lookup Successful! Found object of type: " + ds.getClass().getName());

            // From here, the developer would call ds.getConnection() and execute generic
            // JDBC.

        } catch (NamingException e) {
            // This exception is thrown if the resource doesn't exist, is misspelled,
            // or if the server wasn't configured properly.
            System.err.println("CRITICAL FAILURE: Could not locate the requested JNDI resource.");
            e.printStackTrace();
        }
    }

    // Dummy main method for demonstration context
    public static void main(String[] args) {
        new JndiLookupServlet().processRequest();
    }
}
