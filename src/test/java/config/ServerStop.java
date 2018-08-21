package config;

import org.junit.Test;


public class ServerStop {

	@Test
	public void stopServer() {
	    try {
            MonitorThread.stop(8081);
        }catch (Exception e){

        }
	}
	
}
