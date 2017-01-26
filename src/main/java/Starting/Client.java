package Starting;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javafx.stage.Screen;
import Database.ComplexDatabaseManager;
import Graphic.CustmerInterface.MainCustomerInterfacePanel;
import Graphic.LogIn.MainLogInPanel;
import Graphic.Register.RegisterPanel;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Client extends Application {
	public static Stage primaryStage;
	
	public static MainLogInPanel mainLogInPanel;
	public static RegisterPanel registerPanel;
	public static MainCustomerInterfacePanel mainCustomerInterfacePanel;
	
	
	private static Scene logInPanelScene;
	private static Scene registerPanelScene;
	private static Scene customerInterfaceScene;
	
	static InetAddress IP;
	static Socket socket;
	static public ObjectOutputStream out;
	static public ObjectInputStream in;
	static OutputStream outPut;
	static InputStream inPut;
	public static ServerCommunicationThread serverCommunicationThread;



	@Override
	public void start(Stage primaryStage) throws Exception {
		connectToServer();
		Client.primaryStage = primaryStage;

		mainLogInPanel = new MainLogInPanel();
		registerPanel = new RegisterPanel();
		mainCustomerInterfacePanel = new MainCustomerInterfacePanel();

		logInPanelScene = new Scene(mainLogInPanel, 669, 319, Color.TRANSPARENT);
		logInPanelScene.getStylesheets().add("stylowo.css");

		registerPanelScene = new Scene(registerPanel, 495, 330,
				Color.TRANSPARENT);
		registerPanelScene.getStylesheets().add("stylowo.css");
		
		customerInterfaceScene = new Scene(mainCustomerInterfacePanel, 895, 530,
				Color.TRANSPARENT);
		customerInterfaceScene.getStylesheets().add("stylowo.css");
		
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

		primaryStage.setScene(logInPanelScene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
		
		serverCommunicationThread.start();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	void connectToServer() {
		try {
			serverCommunicationThread = new ServerCommunicationThread();
			IP = InetAddress.getByName("localhost");
			socket = new Socket(IP, 8080);

			Client.outPut = socket.getOutputStream();
			Client.out = new ObjectOutputStream(Client.outPut);

			Client.inPut = socket.getInputStream();
			in = new ObjectInputStream(Client.inPut);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	public static void setLogInSceneActive() {
		primaryStage.setScene(logInPanelScene);
	}
	
	public static void setRegisterSceneActive() {
		primaryStage.setScene(registerPanelScene);
	}
	
	public static void setCustmerInterfaceSceneActive() {
		primaryStage.setScene(customerInterfaceScene);
	}

}
