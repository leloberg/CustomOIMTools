package oim.test;

import java.util.Hashtable;

import javax.security.auth.login.LoginException;

import oracle.iam.platform.OIMClient;

public class ConnectOIM {

	static OIMClient oimClient;
	
	public static void main(String[] args) throws LoginException {
		System.out.println("Creating client....");
		String ctxFactory = "weblogic.jndi.WLInitialContextFactory";
        String serverURL = "t3://oimserver:14000";
        String username = "xelsysadm";
        char[] password = "Admin1234".toCharArray(); //TODO Hide clear text password
        
    	//setting system properties
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(OIMClient.JAVA_NAMING_FACTORY_INITIAL,ctxFactory);
        env.put(OIMClient.JAVA_NAMING_PROVIDER_URL, serverURL);
		System.setProperty("APPSERVER_TYPE", "wls");
		System.setProperty("java.security.auth.login.config", "conf/authwl.conf");
		System.setProperty("weblogic.Name", "WLS_OIM1");

        oimClient = new OIMClient(env);
        System.out.println("Logging in");               
        oimClient.login(username, password);
        System.out.println("Log in successful");

	}

}
