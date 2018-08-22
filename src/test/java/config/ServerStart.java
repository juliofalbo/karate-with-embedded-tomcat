package config;

import java.util.Objects;

import org.om.karate.sample.SampleApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ServerStart {

    private static final Logger logger = LoggerFactory.getLogger(ServerStart.class);

    private ConfigurableApplicationContext context;
    private int port = 0;

    public void start(String[] args, boolean wait) throws Exception {
        SpringApplication app = new SpringApplication(SampleApplication.class);
        app.setAdditionalProfiles("test");
        context = app.run(args);
        port = Integer.parseInt(Objects.requireNonNull(context.getEnvironment().getProperty("local.server.port")));
        logger.info("started server on port: {}", port);
    }

    public int getPort() {
        return port;
    }

}
