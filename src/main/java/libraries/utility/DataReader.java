package libraries.utility;

import java.io.*;
import java.util.Properties;

public class DataReader {
    private Properties props;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private File fileProps = null;
    private String filename;

    public DataReader(String filename) {
        this.filename = filename;
        String pathRoot = "src/main/config/";
        fileProps = new File(pathRoot + filename);
        loadFile(fileProps);
        System.out.println("--- File \"" + filename + "\" is ready");
    }


    private void loadFile(File file) {

        props = new Properties();
        if (file.exists()) {
            try {
                inputStream = new FileInputStream(file);
                props.load(inputStream);
            } catch (IOException e) {
                System.out.println("- Error --- File can not open");
            } finally {
                if (inputStream != null) try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("- Error --- Unable to close file");
                }
            }
        } else {
            System.out.println("Can not find \"" + filename + "\" file");
        }
    }

    /**
     * Get property from list
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * Set property to list
     */
    public void setProperty(String key, String value) {
        try {
            outputStream = new FileOutputStream(fileProps);
            props.setProperty(key, value);
            props.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
