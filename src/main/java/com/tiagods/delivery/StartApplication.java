package com.tiagods.delivery;


import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.tiagods.delivery.controller.MenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartApplication extends Application {
	@Override
    public void start(Stage primaryStage) {
        try {
        	//Icons estilo = Icons.getInstance();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            loader.setController(new MenuController());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menu Inicial");
            //stage.getIcons().add(new Image(estilo.getIcon().toString()));
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
            	public void handle(WindowEvent event) {
					System.exit(0);
				}
			});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] arg0) {
    	launch(arg0);
    }
}
