package Starting;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import Database.ComplexDatabaseManager;
import Graphic.LogIn.MainLogInPanel;
import Graphic.Register.RegisterPanel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Client extends Application {
	public static Stage primaryStage;
	public static MainLogInPanel mainLogInPanel;
	public static RegisterPanel registerPanel;
	public static Scene logInPanelScene;
	public static Scene registerPanelScene;
	
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
		JavaFX.primaryStage = primaryStage;

		mainLogInPanel = new MainLogInPanel();
		registerPanel = new RegisterPanel();

		logInPanelScene = new Scene(mainLogInPanel, 669, 319, Color.TRANSPARENT);

		logInPanelScene.getStylesheets().add("stylowo.css");

		registerPanelScene = new Scene(registerPanel, 495, 330,
				Color.TRANSPARENT);
		registerPanelScene.getStylesheets().add("stylowo.css");

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

			JavaFX.outPut = socket.getOutputStream();
			JavaFX.out = new ObjectOutputStream(JavaFX.outPut);

			JavaFX.inPut = socket.getInputStream();
			in = new ObjectInputStream(JavaFX.inPut);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

}
