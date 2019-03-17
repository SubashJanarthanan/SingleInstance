package com.singleinstance;

import java.net.InetAddress;
import java.net.ServerSocket;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * The Class SingleInstanceClass.
 * 
 * @author  Subash.Janarthanan 
 * 
 */
public class SingleInstanceClass {

	/** The socket. */
	public static ServerSocket socket;

	/** The Constant APP_SUCCESS_WELCOME_MESS. */
	public static final String APP_SUCCESS_WELCOME_MESS = "Hello " + System.getProperty("user.name")
			+ "! welcome to the single instance application";

	/** The Constant APP_ALREADY_EXIST_MESS. */
	public static final String APP_ALREADY_EXIST_MESS = "Application is already running....";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			// Execute the method isProgramAlreadyRunning() to check the app status
			isProgramAlreadyRunning();
			
			// If it doesn't give any exception then start the application
			initiateApp();
		} catch (Exception e) {
			MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Error", e.getMessage());
		}

	}

	/**
	 * Initiate app.
	 */
	public static void initiateApp() {
		MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Info", APP_SUCCESS_WELCOME_MESS);
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public static String getUserName() {
		String userName = System.getProperty("user.name");
		return userName;
	}

	/**
	 * Checks if is program already running.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public static void isProgramAlreadyRunning() throws Exception {
		try {

			// Get localhost address from the system property
			InetAddress localAddress = InetAddress.getLocalHost();

			// Create a serverSocket instance and pass <Port No> <Backlog> <BindAddress> as arguments
			// <Port No> The port must be between 0 and 65535
			// <Backlog> Requested maximum length of the queue of incoming connections
			// <BindAddress> The local InetAddress the server will bind to
			socket = new ServerSocket(12345, 1, localAddress);
		} catch (Exception ex) {

			// Throw new custom exception so that it can be caught and displayed
			throw new Exception(APP_ALREADY_EXIST_MESS);
		}
	}
}
