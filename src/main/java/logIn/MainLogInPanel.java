package logIn;

import javafx.scene.layout.BorderPane;

public class MainLogInPanel extends BorderPane {
	public CenterLogInPanel myVBox;
	private final RightLogInPanel registerPane;

	public MainLogInPanel() {
		myVBox = new CenterLogInPanel();
		registerPane = new RightLogInPanel();
		this.setStyle("-fx-background-image: url('rounded_corners.png');"
				+ "-fx-background-radius: 50px;");
		this.setCenter(myVBox);
		this.setRight(registerPane);
	}

}
