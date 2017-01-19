package Starting;
import Graphic.LogIn.MainLogInPanel;
import Graphic.Register.RegisterPanel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class JavaFX extends Application{
	public static Stage primaryStage;
	public  static MainLogInPanel mainLogInPanel;
	public  static RegisterPanel registerPanel;
	public  static Scene logInPanelScene;
	public static Scene registerPanelScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		JavaFX.primaryStage = primaryStage;
		
		mainLogInPanel = new MainLogInPanel();
		registerPanel = new RegisterPanel();
		
		logInPanelScene = new Scene(mainLogInPanel, 669, 319, Color.TRANSPARENT);
		
		logInPanelScene.getStylesheets().add("stylowo.css");
		
		
		registerPanelScene = new Scene(registerPanel, 495, 330, Color.TRANSPARENT);
		registerPanelScene.getStylesheets().add("stylowo.css");
		
		primaryStage.setScene(logInPanelScene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
