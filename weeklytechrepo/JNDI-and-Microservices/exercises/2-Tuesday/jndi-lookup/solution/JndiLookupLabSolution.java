package com.revature.jndilab;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.enterprise.MainframeRes;

public class JndiLookupLabSolution {

    public void retrieveConfiguration() {
        System.out.println("Starting JNDI Component Lookup...");

        try {
            // 1. Create the InitialContext
            Context initCtx = new InitialContext();

            // 2. Get the local context
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            // 3. Lookup the string
            String supportEmail = (String) envCtx.lookup("mail/SupportEmail");
            System.out.println("Support Email retrieved: " + supportEmail);

            // 4. Lookup the generic object
            MainframeRes connection = (MainframeRes) envCtx.lookup("mainframe/Connection");
            connection.connect(); // Invoke mock method

        } catch (NamingException e) {
            System.err.println("A critical JNDI misconfiguration occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JndiLookupLabSolution().retrieveConfiguration();
    }
}
