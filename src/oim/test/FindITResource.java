package oim.test;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import Thor.API.tcResultSet;
import Thor.API.Exceptions.tcAPIException;
import Thor.API.Exceptions.tcColumnNotFoundException;
import Thor.API.Operations.tcITResourceDefinitionOperationsIntf;

public class FindITResource {
	
	static long itResourceKey;
	static String itResTypeName = null;
	
	public static long getItResource() {
		
		String colName = null;
		String value = null;
		
		tcITResourceDefinitionOperationsIntf itResInstDef=(tcITResourceDefinitionOperationsIntf) ConnectOIM.oimClient.getService(tcITResourceDefinitionOperationsIntf.class);
		Map<String, String> getMap = new HashMap<>();
		getMap.put("IT Resources Type Definition.Server Type", itResTypeName);
		tcResultSet rs = null;
		
		try {
			rs = itResInstDef.getITResourceDefinition(getMap);
			
			for(int i = 0; i < rs.getRowCount(); i++){
				rs.goToRow(i);
				String column[] = rs.getColumnNames();
				for(int j = 0; j < column.length; j++){
					colName = column[j];
					value = rs.getStringValue(colName);
					
					if (colName.matches("IT Resources Type Definition.Key") ) {
						itResourceKey = Long.parseLong(value);
						}
				}
			}
		} catch (tcAPIException | tcColumnNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itResourceKey;

	}
	
	public static void main(String[] args)
	{
		itResTypeName = "OIA";	
		
		try {
			ConnectOIM.main(args);
			getItResource();
		if(!(itResourceKey == 0)) {
			System.out.println("IT Resource Key for "+itResTypeName+" is "+itResourceKey);
		}
		else {
			System.out.println("IT Resource Type does not exist");
		}
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to log in");
		}
	}

}
