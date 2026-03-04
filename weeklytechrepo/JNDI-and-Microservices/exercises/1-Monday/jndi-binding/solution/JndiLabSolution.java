package com.revature.jndilab;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;

import java.io.File;

public class JndiLabSolution {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // 1. Enable JNDI naming
        tomcat.enableNaming();

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        // 2. Create Support Email String
        ContextResource emailRes = new ContextResource();
        emailRes.setName("mail/SupportEmail");
        emailRes.setType("java.lang.String");
        emailRes.setProperty("value", "support@enterprise.com");

        // 3. Create Custom Mainframe resource
        ContextResource mainframeRes = new ContextResource();
        mainframeRes.setName("mainframe/Connection");
        mainframeRes.setType("com.enterprise.MainframeRes");
        mainframeRes.setProperty("port", "8081");

        System.out.println("Binding resources to JNDI Tree...");

        // 4. Register both resources
        tomcat.getServer().getGlobalNamingResources().addResource(emailRes);
        tomcat.getServer().getGlobalNamingResources().addResource(mainframeRes);

        // Start the server
        tomcat.start();
        System.out.println("Server started. Resources bound successfully.");
        tomcat.getServer().await();
    }
}
