package modifiedtictactoe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ModifiedTicTacToeController {

	private boolean isFirstPlayer = true;
	boolean winner = false;
	
	@FXML Button b1;
	@FXML Button b2;
	@FXML Button b3;
	@FXML Button b4;
	@FXML Button b5;
	@FXML Button b6;
	@FXML Button b7;
	@FXML Button b8;
	@FXML Button b9;

	@FXML GridPane gameBoard;
	Stage secondaryStage;
	private void openStartingWindow() {
		try {
		Pane starting = (Pane) FXMLLoader.load(getClass().getResource("StartingWindow.fxml"));
		Scene startingScene = new Scene(starting, 250, 250);
		secondaryStage = new Stage();
		secondaryStage.setScene(startingScene);
		secondaryStage.setResizable(false);
		secondaryStage.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
	public void menuClickHandler(ActionEvent evt) {
		MenuItem clickedMenu = (MenuItem) evt.getTarget();
		String menuLabel = clickedMenu.getText();
		
		if ("One Player".equals(menuLabel)) {
			menuReset();
			isFirstPlayer = true; // new game starts with X
		} else if ("Two Players".equals(menuLabel)) {
			buttonClickHandler(evt);
			
		} else if ("How To Play".equals(menuLabel)) {
			openHowToPlayWindow();
		} else if ("Easy".equals(menuLabel)) {
			menuReset();
			computerEasyRandom(evt);
		}
	}


	public void buttonClickHandler(ActionEvent evt) {

		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();

		if ("".equals(buttonLabel) && isFirstPlayer) {
			clickedButton.setText("X");
			isFirstPlayer = false;
			winner = find3InRow();
		} else if ("".equals(buttonLabel) && !isFirstPlayer) {
			clickedButton.setText("O");
			isFirstPlayer = true;
			winner = find3InRow();
		}
	}

	private boolean find3InRow() {
		//Row 1
		if (""!=b1.getText() && b1.getText() == b2.getText() && b2.getText() == b3.getText()) {
			highlightWinningCombo(b1, b2, b3);
			return true;
		}
		//Row 2
		if (""!=b4.getText() && b4.getText() == b5.getText() && b5.getText() == b6.getText()) {
			highlightWinningCombo(b4, b5, b6);
			return true;
		}
		//Row 3
		if (""!=b7.getText() && b7.getText() == b8.getText() && b8.getText() == b9.getText()) {
			highlightWinningCombo(b7, b8, b9);
			return true;
		}
		//Column 1
		if (""!=b1.getText() && b1.getText() == b4.getText() && b4.getText() == b7.getText()) {
			highlightWinningCombo(b1, b4, b7);
			return true;
		}
		//Column 2
		if (""!=b2.getText() && b2.getText() == b5.getText() && b5.getText() == b8.getText()) {
			highlightWinningCombo(b2, b5, b8);
			return true;
		}
		//Column 3
		if (""!=b3.getText() && b3.getText() == b6.getText() && b6.getText() == b9.getText()) {
			highlightWinningCombo(b3, b6, b9);
			return true;
		}
		//Diagonal 1
		if (""!=b1.getText() && b1.getText() == b5.getText() && b5.getText() == b9.getText()) {
			highlightWinningCombo(b1, b5, b9);
			return true;
		}
		//Diagonal 2
		if (""!=b3.getText() && b3.getText() == b5.getText() && b5.getText() == b7.getText()) {
			highlightWinningCombo(b3, b5, b7);
			return true;
		}
		return false;
	}
	
	private void highlightWinningCombo (Button first, Button second, Button third) {
		
		first.getStyleClass().add("winning-button");
		second.getStyleClass().add("winning-button");
		third.getStyleClass().add("winning-button");
	}

	private void menuReset() {
		ObservableList<Node> buttons = gameBoard.getChildren();
		
		buttons.forEach(btn -> {
			((Button) btn).setText("");
			
			btn.getStyleClass().remove("winning-button");
		});
	}
	private void openHowToPlayWindow() {
		try {
			Pane howTo = (Pane) FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
			Scene howToScene = new Scene(howTo, 250, 250);
			secondaryStage = new Stage();
			secondaryStage.setScene(howToScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void computerEasyRandom(ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		boolean randomEmpty = false;
		int randomButton;
		Button selectedButton = b1;

		while (!randomEmpty) {
			randomButton = (int) (Math.random() * 9) + 1;
			switch (randomButton) {
			case 1: selectedButton = b1;
			case 2: selectedButton = b2;
			case 3: selectedButton = b3;
			case 4: selectedButton = b4;
			case 5: selectedButton = b5;
			case 6: selectedButton = b6;
			case 7: selectedButton = b7;
			case 8: selectedButton = b8;
			case 9: selectedButton = b9;
			}
			if (selectedButton.getText().equals(""))
				randomEmpty = true;
		}
		selectedButton.setText("O");
		winner = find3InRow();
	}
	
	public void closeCurrentWindow(final ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	
	
}
