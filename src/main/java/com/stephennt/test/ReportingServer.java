package com.stephennt.test;


import net.codestory.http.WebServer;

import java.util.logging.Logger;
import java.util.stream.Stream;

public class ReportingServer {
    private final static Logger logger = Logger.getLogger(ReportingServer.class.getName());

    public static void main(String[] args) {

        // UsersList authUsers = new UsersList.Builder().addUser( "x", "y" ).build();
        // BasicAuthFilter baf = new BasicAuthFilter( "/", "Test", authUsers );

        new WebServer().configure(
                // routes -> routes.filter( baf )
                routes -> routes.get("/sse", () -> {
                    logger.info("Starting stream");
                    return Stream.iterate(0, (a) -> a + 1).onClose( () -> {
                        logger.info("Ending stream");
                    });

                })
        ).start(8080);
    }
}
