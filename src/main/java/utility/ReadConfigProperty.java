package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigProperty {

	public String getURLink() throws Exception {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "/src/main/resources/testdata/config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(prop.getProperty("env"));

	}

}
