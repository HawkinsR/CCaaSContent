package com.revature.jndilab;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;

import java.io.File;

public class JndiLab {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        
        // TODO: Enable JNDI naming

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        // TODO: Create a ContextResource named "mail/SupportEmail" of type "java.lang.String"
        // Don't forget to set the 'value' property.

        // TODO: Create a ContextResource named "mainframe/Connection" of type "com.enterprise.MainframeRes"
        // Set a 'port' property.

        System.out.println("Binding resources to JNDI Tree...");
        // TODO: Add both resources to the global naming directory

        // Start the server
        tomcat.start();
        System.out.println("Server started. Resources bound.");
        tomcat.getServer().await();
    }
}
