package oim.test;

import javax.security.auth.login.LoginException;

import oracle.iam.provisioning.api.ApplicationInstanceService;
import oracle.iam.provisioning.exception.ApplicationInstanceNotFoundException;
import oracle.iam.provisioning.exception.GenericAppInstanceServiceException;
import oracle.iam.provisioning.vo.ApplicationInstance;

public class FindApplicationInstance {
	
	public static String getAppInst(String applicationInstanceName)
	{ 
		String appInstKey = null;
		ApplicationInstanceService appInstServ = ConnectOIM.oimClient.getService(ApplicationInstanceService.class);
		ApplicationInstance appInst = null;
		
		try {
			System.out.println("Looking for Application Instance with name "+applicationInstanceName);
			appInst = appInstServ.findApplicationInstanceByName(applicationInstanceName);
			appInstKey = appInst.getApplicationInstanceName();
			System.out.println("AppInstKey: "+appInst.getApplicationInstanceKey());
			System.out.println("Display Name: "+appInst.getDisplayName());
			System.out.println("Description: "+appInst.getDescription());
			
		} catch (ApplicationInstanceNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ApplicationInstance not found");
		} catch (GenericAppInstanceServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return appInstKey;
	}
	
	public static void main(String[] args)
	{
		try {
			ConnectOIM.main(args);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String applicationInstanceName = "testAppInst1";
		getAppInst(applicationInstanceName);
		
	}

}
