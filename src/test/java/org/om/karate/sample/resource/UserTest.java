package org.om.karate.sample.resource;

import com.intuit.karate.junit4.Karate;
import config.ServerStart;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Karate.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    private static ServerStart server;

    public static int startServer() throws Exception {
        if (server == null) { // keep spring boot side alive for all tests including package 'mock'
            server = new ServerStart();
            server.start(new String[] { "" }, false);
        }
        System.setProperty("demo.server.port", server.getPort() + "");
        return server.getPort();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        startServer();
    }
}
