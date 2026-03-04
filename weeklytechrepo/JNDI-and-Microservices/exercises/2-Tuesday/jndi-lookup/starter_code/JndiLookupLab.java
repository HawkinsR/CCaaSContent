package com.revature.jndilab;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.enterprise.MainframeRes;

public class JndiLookupLab {

    public void retrieveConfiguration() {
        System.out.println("Starting JNDI Component Lookup...");

        // TODO: Surround this logic in a try/catch block for NamingException

        // TODO: Create the InitialContext

        // TODO: Get the "java:comp/env" context

        // TODO: Lookup "mail/SupportEmail" and cast to String. Print value.

        // TODO: Lookup "mainframe/Connection" and cast to MainframeRes.
        // TODO: Call .connect() on the retrieved MainframeRes object.

    }

    public static void main(String[] args) {
        new JndiLookupLab().retrieveConfiguration();
    }
}
