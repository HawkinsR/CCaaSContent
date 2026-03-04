package com.revature.jndidemo;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;

import java.io.File;

/**
 * A simple application demonstrating how an Application Server (Tomcat)
 * manually binds a resource (a Database connection pool) into its JNDI directory.
 */
public class EmbeddedTomcatJndi {

    public static void main(String[] args) throws Exception {
        // 1. Create a simulated Application Server
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.enableNaming(); // Critical: Turns on the JNDI directory service

        // Create a dummy web context so Tomcat can start
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        // 2. Define the Resource we want to bind
        // This represents a database connection pool configured by an admin
        ContextResource resource = new ContextResource();
        resource.setName("jdbc/MyDataSource"); // The JNDI Name (The "Phone Book" name)
        resource.setType("javax.sql.DataSource"); // The type of object
        resource.setProperty("driverClassName", "org.h2.Driver");
        resource.setProperty("url", "jdbc:h2:mem:testdb");
        resource.setProperty("username", "sa");
        resource.setProperty("password", "");

        // 3. Bind the Resource to the Server's Global JNDI Tree
        System.out.println("Binding resource " + resource.getName() + " to JNDI Tree...");
        tomcat.getServer().getGlobalNamingResources().addResource(resource);

        // 4. Start the server (This makes the JNDI directory available)
        tomcat.start();
        System.out.println("Tomcat Server started. Resource is now globally bound.");
        
        // Wait indefinitely so the server doesn't shut down
        tomcat.getServer().await();
    }
}
