package libraries.mobile;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import libraries.utility.Common;
import libraries.utility.Log;
//import org.apache.commons.exec.OS;

import java.io.File;

public class AppiumServerManager {

	private static String address = "";
	private static String port = "";
	private static String path = "";

	private static AppiumServerManager instance = null;
	private static AppiumServer appiumServer;
	public File appiumFolder;

	private AppiumServerManager() {
		File directory = new File(path);
		if (directory.exists()) {
			appiumFolder = directory;

			ServerArguments serverArguments = new ServerArguments();
			serverArguments.setArgument("--address", address);
			serverArguments.setArgument("--bootstrap-port", port);
			serverArguments.setArgument("--no-reset", true);
			serverArguments.setArgument("--full-reset", false); // Not install
																// app
			serverArguments.setArgument("--local-timezone", true);
			try {
				appiumServer = new AppiumServer(appiumFolder, serverArguments);
			} catch (Exception e) {
				Log.info("Don't have option for this Operation");
			}
		} else {
			System.out.println("FolderNotFound");
		}
	}

	public static AppiumServerManager getInstance() {
		if (instance == null) {
			instance = new AppiumServerManager();
		}
		return instance;
	}

	public static void setupConnection(String _address, String _port, String _path) {
		address = _address;
		port = _port;
		path = _path;
	}

	public AppiumServer createServer(String address, String port, String path) {
		File directory = new File(path);
		if (directory.exists()) {
			appiumFolder = directory;

			ServerArguments serverArguments = new ServerArguments();
			serverArguments.setArgument("--address", address);
			serverArguments.setArgument("--bootstrap-port", port);
			serverArguments.setArgument("--full-reset", false); // Not install
																// app
			serverArguments.setArgument("--no-reset", true);
			serverArguments.setArgument("--local-timezone", true);

			try {
				return new AppiumServer(appiumFolder, serverArguments);
			} catch (Exception e) {
				Log.info("Don't have option for this Operation");
			}
		} else {
			System.out.println("FolderNotFound");
		}
		return null;
	}

	/**
	 * Start Appium Server
	 */
	public void start() throws Exception {
		if (appiumServer != null) {
			appiumServer.startServer();
			Log.info("Appium server has been started successfully.");
		} else {
			throw new Exception("Server not inited");
		}

	}

	/**
	 * Stop Appium Server
	 */
	public void stop() throws Exception {
		if (appiumServer != null) {
			appiumServer.stopServer();
			Common.wait(3);
		} else {
			throw new Exception("Server not closed");
		}

		Log.info("Appium server has been stopped successfully.");

	}

	public boolean isRunning() {
		return appiumServer.isServerRunning();
	}

	public void status() {
		if (isRunning() == true) {
			Log.debug("Appium server keep working");

		} else {
			Log.debug("Appium server stop working");

		}
	}

}
