package dataprovider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import utility.ExcelRW;

public class  Dp_login {
	

	@DataProvider(name="commondp")
	public static  Iterator<Object[]> getData(Method m) throws Exception{
		Iterator<Object[]> commondp_Logic = null;
		
		switch (m.getName().toLowerCase()){
		
		case "invalidlogin":
			commondp_Logic = commonDPLogic("Login", "invalidlogin");
			break;
			
		case "validlogin":
			commondp_Logic = commonDPLogic("Login", "validlogin");
			break;
		
		case "testlogin":
			commondp_Logic = commonDPLogic("Login", "invalidlogin");
			break;
			
		case "invalidsearch":
			commondp_Logic = commonDPLogic("Search", "invalidsearch");
			break;
			
		case "validsearch":
			commondp_Logic = commonDPLogic("Search", "validsearch");
			break;	
		
//		case default:
		}
		
//		if(m.getName().equalsIgnoreCase("invalidlogin")){
//			commondp_Logic = commondp_Logic("Login", "invalidlogin");
//		}else if(m.getName().equalsIgnoreCase("validlogin")){
//			commondp_Logic = commondp_Logic("Login", "validlogin");
//		}else if(m.getName().equalsIgnoreCase("invalidsearch")){
//			commondp_Logic = commondp_Logic("Search", "invalidsearch");
//		}else if(m.getName().equalsIgnoreCase("validsearch")){
//			commondp_Logic = commondp_Logic("Search", "validsearch");
//		}
//			
		return commondp_Logic;
	}
	
	
////	Valid Login
//	@DataProvider(name="validLogin")
//	public static Iterator<Object[]> getValidLogin() throws Exception{
//
//		return commonDPLogic("Login", "ValidLogin");
//	}
//	
////	Invalid Login
//	@DataProvider(name="invalidLogin")
//	public static Iterator<Object[]> getInvalidLogin() throws Exception{
//		return commonDPLogic("Login", "InvalidLogin");
//	
//		}
//	
	
	public static Iterator<Object[]> commonDPLogic(String sheetName, String ScriptName) throws Exception{
//		ExcelRW ex = new ExcelRW(System.getProperty("user.dir") + "/src/test/resources/rediff.xlsx");
		ExcelRW ex = new ExcelRW(System.getProperty("user.dir") + "/src/main/resources/testdata/data.xlsx");// + "src\\main\\resources\\data.xlsx"));
		int row = ex.getRowcount(sheetName);
		int col = ex.getColcount(sheetName);
		
		List<Object[]> ls = new ArrayList<Object[]>();
		
		for (int irow=1;irow<row;irow++ ){
//			read testData only if execution flag is Y
			String executeFlag = ex.readcell(sheetName, irow, 2);
			String scrptName = ex.readcell(sheetName, irow, 3);
			if(scrptName.equalsIgnoreCase(ScriptName)&& executeFlag.equalsIgnoreCase("Y")){
			Object[]obj=new Object[1];
			Map<String, String> hm = new HashMap<String , String>();
			for(int icol=0;icol<col;icol++){
				String key = ex.readcell(sheetName, 0, icol);
				String	value = ex.readcell(sheetName, irow, icol);	
			
			hm.put(key, value);
			
		}
			
		obj[0]=hm;	
		ls.add(obj);
			}
}
return ls.iterator();
	
	
	}
}
