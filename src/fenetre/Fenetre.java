package fenetre;

// Import
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

import ast.AST;
import command.IncorrectMethodCallException;
import command.InexistantVariableException;
import command.VariableAlreadyExistException;
import command.VariableNotDeclaredException;
import exception.IncorrectConversionException;
import exception.InterpreterException;
import parser.Parser;
import parser.SyntaxErrorException;
import parser.UnexceptedEndOfFileException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Fenetre extends Application {
	
	private Parser parser;

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
		final TextArea responsecommand = new TextArea();
		responsecommand.setEditable(false);
		Button validatecommand = new Button("Ajouter à l'AST");
		Button executecommand = new Button("Executer");
		Button chooser = new Button("Open a File.");
		Button sauvegarde = new Button("Sauvegarder votre AST");
		Button restauration = new Button("Restaurer votre AST");

		Label title = new Label("Commande : ");
		Label titleChooser = new Label("Choisir un fichier :");
		Label response = new Label("Réponse : ");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("."));
		
		System.setOut(new PrintStream (new Writer(responsecommand)));
		
		
		// Item of MenuBar :
		// First Menu :
		Label menuLabel = new Label("In Ligne Command");
		menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				parser = new Parser();
				if (vbox.getChildren().contains(chooser)
						|| vbox.getChildren().contains(titleChooser)) {
					vbox.getChildren().clear();
				}
				if (!vbox.getChildren().contains(manualcommand)
						|| !vbox.getChildren().contains(title)) {
					
					final HBox command = new HBox();
					command.setSpacing(10);
					command.setPadding(new Insets(10, 10, 10, 10));
					
					command.getChildren().addAll(validatecommand, executecommand, sauvegarde, restauration);
					
					vbox.getChildren().addAll(title, manualcommand,
							command, response, responsecommand);
				}
				vbox.setVisible(true);
			}
		});
		
		validatecommand.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					parser.parse(manualcommand.getText());
					manualcommand.clear();
				}  catch (SyntaxErrorException e) {
					System.out.println("Erreur syntaxique : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (VariableNotDeclaredException e) {
					System.out.println("Variable non déclaré : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (IncorrectConversionException e) {
					System.out.println("Conversion incorret : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("IO error");
					System.out.println(e.getMessage());
				} 
			}
		});
		executecommand.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(parser.isComplete()){
					try{
						parser.getAST().execute();
					} catch (VariableAlreadyExistException e) {
						System.out.println("Ne peut pas redeclarer la variable : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
					} catch (IncorrectMethodCallException e) {
						System.out.println("Appel de méthode incorrect : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
					} catch (InexistantVariableException e) {
						System.out.println("Variable non existante : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (InterruptedException e) {
						System.out.println("Exception d'interruption");		
						System.out.println(e.getMessage());
					} catch (SyntaxErrorException e) {
						System.out.println("Erreur syntaxique : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (VariableNotDeclaredException e) {
						System.out.println("Variable non déclaré : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (IncorrectConversionException e) {
						System.out.println("Conversion incorret : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (InterpreterException e){
						System.out.println("Erreur d'éxecution : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
			
				}else{
					System.out.println("AST non complet");
				}
			}
		});
		sauvegarde.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Save AST");
				FileChooser.ExtensionFilter extFilter = 
		                new FileChooser.ExtensionFilter("Java fork interpreter AST (*.ji.ast)", "*.ji.ast");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser
						.showSaveDialog(primaryStage);
				if (file != null) {
					ObjectOutputStream oos = null;
					try {
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(parser.getAST());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(oos != null ){
							try {
								oos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
			}
		});
		
		restauration.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Charger l'AST");
				FileChooser.ExtensionFilter extFilter = 
		                new FileChooser.ExtensionFilter("Java fork interpreter AST (*.ji.ast)", "*.ji.ast");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser
						.showOpenDialog(primaryStage);
				if (file != null) {
					ObjectInputStream ois = null;
					try {
						ois = new ObjectInputStream(new FileInputStream(file));
						parser.setAST((AST) ois.readObject());
					} catch (IOException e) {
						System.out.println("Erreur IO");
					} catch (ClassNotFoundException e) {
						System.out.println("Erreur lors de la récuperation");
					} catch(ClassCastException e){
						System.out.println("Le fichier est incorrect ou corrompu");
					} finally{
				
						if(ois != null ){
							try {
								ois.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
			}
		});
		Menu commandligne = new Menu();
		commandligne.setGraphic(menuLabel);
		
		

		// Second Menu :
		Label menuLabel2 = new Label("Import File");
		menuLabel2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (vbox.getChildren().contains(manualcommand) || vbox.getChildren().contains(title)) {
					vbox.getChildren().clear();
				}
				if (!vbox.getChildren().contains(chooser) || !vbox.getChildren().contains(titleChooser)) {
					vbox.getChildren().addAll(titleChooser, chooser, response,
							responsecommand);
				}
				vbox.setVisible(true);
			}
		});
		chooser.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Open Resource File");
				FileChooser.ExtensionFilter extFilter = 
		                new FileChooser.ExtensionFilter("Java fork interpreter (*.ji)", "*.ji");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser
						.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						parser = new Parser(file);
						parser.parse();
						parser.getAST().execute();
					} catch (VariableAlreadyExistException e) {
						System.out.println("Ne peut pas redeclarer la variable : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (IncorrectMethodCallException e) {
						System.out.println("Appel de méthode incorrect : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (InexistantVariableException e) {
						System.out.println("Variable non existante : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (InterruptedException e) {
						System.out.println("Exception d'interruption");		
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						System.out.println("Fichier non trouvé");
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("IO error");
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (SyntaxErrorException e) {
						System.out.println("Erreur syntaxique : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (VariableNotDeclaredException e) {
						System.out.println("Variable non déclaré : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (IncorrectConversionException e) {
						System.out.println("Conversion incorret : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (UnexceptedEndOfFileException e) {
						System.out.println("Fin du fichier non attendu : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					} catch (InterpreterException e){
						System.out.println("Erreur d'éxecution : Ligne " + e.getLineNumber() + ", commande : " + e.getCommand());
						System.out.println(e.getMessage());
						e.printStackTrace();
					}finally{
						if(parser != null){
							try {
								parser.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
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