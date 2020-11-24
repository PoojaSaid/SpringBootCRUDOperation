package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPropertyFile {
	final static Logger logger = LoggerFactory.getLogger(ReadPropertyFile.class.getName());

	private static ReadPropertyFile obj = null;

	private ReadPropertyFile() {
	}

	// Now we are providing global point of access.
	public static ReadPropertyFile getInstance() {
		if (obj == null) {
			obj = new ReadPropertyFile();
			System.out.println("Getting Values from Properties file");
		}
		return obj;

	}

	public Properties getPropConst() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("D:\\Properties\\config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

}
