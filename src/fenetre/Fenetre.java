package fenetre;

// Import
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Fenetre extends Application {

	// Main method who launch the application.
	public static void main(String[] args) {
		launch(args);
	}

	// Start method for implement the stage.
	public void start(Stage primaryStage) {

		// Set window title & initialize the scene.
		primaryStage.setTitle("Interpretor Fork");
		Scene scene = new Scene(new VBox(), 700, 600);
		scene.setFill(Color.OLDLACE);

		// Element variable in fonction of what you click on the Menu Barre.
		final VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		// Create window element and action.
		// MenuBar
		MenuBar choice = new MenuBar();

		// Elements used by vbox.
		TextArea manualcommand = new TextArea();
		TextArea responsecommand = new TextArea();
		responsecommand.setEditable(false);
		Button validatecommand = new Button();
		validatecommand.setText("Execute.");
		Button chooser = new Button();
		chooser.setText("Open a File.");
		Label title = new Label("Commande : ");
		Label titleChooser = new Label("Choisir une file :");
		Label response = new Label("Réponse : ");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");

		// Item of MenuBar :
		// First Menu :
		Label menuLabel = new Label("In Ligne Command");
		menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (vbox.getChildren().contains(chooser)
						|| vbox.getChildren().contains(titleChooser)) {
					vbox.getChildren().clear();
				}
				if (!vbox.getChildren().contains(manualcommand)
						|| !vbox.getChildren().contains(title)) {
					validatecommand
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									// TODO
								}
							});
					vbox.getChildren().addAll(title, manualcommand,
							validatecommand, response, responsecommand);
				}
				vbox.setVisible(true);
			}
		});
		Menu commandligne = new Menu();
		commandligne.setGraphic(menuLabel);

		// Second Menu :
		Label menuLabel2 = new Label("Import File");
		menuLabel2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (vbox.getChildren().contains(manualcommand)
						|| vbox.getChildren().contains(title)) {
					vbox.getChildren().clear();
				}
				if (!vbox.getChildren().contains(chooser)
						|| !vbox.getChildren().contains(titleChooser)) {
					chooser.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							File file = fileChooser
									.showOpenDialog(primaryStage);
							if (file != null) {
								// TODO
							}
						}
					});
					vbox.getChildren().addAll(titleChooser, chooser, response,
							responsecommand);
				}
				vbox.setVisible(true);
			}
		});
		Menu commandfile = new Menu();
		commandfile.setGraphic(menuLabel2);

		// Add the menu to the the menubar.
		choice.getMenus().addAll(commandligne, commandfile);

		// Scene.
		// Insert MenuBar & Vbox.
		((VBox) scene.getRoot()).getChildren().addAll(choice, vbox);

		// Final method for the correct execution.
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}