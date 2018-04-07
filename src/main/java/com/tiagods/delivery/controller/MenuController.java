package com.tiagods.delivery.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.tiagods.delivery.repository.helper.UsuariosImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MenuController extends UtilsController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	@FXML
    void cliente(ActionEvent event) {
    	try {
			Stage primaryStage = new Stage();
        	//Icons estilo = Icons.getInstance();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientePesquisa.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pesquisa de Clientes");
            //stage.getIcons().add(new Image(estilo.getIcon().toString()));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void empresa(ActionEvent event) {
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Empresa.fxml"));
            loader.setController(new EmpresaCadastroController(stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        }catch(IOException e) {
            e.printStackTrace();
            super.alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", e.getMessage());
        }
    }
    @FXML
    void produto(ActionEvent event) {
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPesquisa.fxml"));
            loader.setController(new ProdutoPesquisaController(stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        }catch(IOException e) {
            e.printStackTrace();
            super.alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", e.getMessage());
        }
    }

    @FXML
    void sobre(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void usuario(ActionEvent event) {
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UsuarioPesquisa.fxml"));
            loader.setController(new UsuarioPesquisaController());
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
            super.alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", e.getMessage());
        }
    }
}
