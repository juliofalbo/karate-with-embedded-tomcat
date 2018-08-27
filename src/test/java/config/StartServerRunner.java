package config;

public abstract class StartServerRunner {

    private static ServerStart server;

    public static int startServer() throws Exception {
        if (server == null) {
            server = new ServerStart();
            server.start(new String[] { "" });
        }
        System.setProperty("demo.server.port", server.getPort() + "");
        return server.getPort();
    }

}
