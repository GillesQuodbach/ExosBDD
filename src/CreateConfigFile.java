import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CreateConfigFile {

	public static void main(String[] args) throws IOException {
	Properties prop = readPropertiesFile("credentials.properties");
	System.out.println("driverClass=" + prop.getProperty("driverClass"));
	System.out.println("url=" + prop.getProperty("url"));
	System.out.println();
	}

	public static Properties readPropertiesFile(String fileName) throws IOException{
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		}catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}finally {
			fis.close();
		}
		return prop;
	}
	
}
