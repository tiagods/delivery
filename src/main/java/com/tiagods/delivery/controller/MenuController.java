package com.tiagods.delivery.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.tiagods.delivery.model.Empresa;
import com.tiagods.delivery.repository.helper.EmpresasImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController extends UtilsController implements Initializable{
	@FXML
    private JFXButton btnCaixa;

    private EmpresasImpl empresas;
    private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        btnCaixa.setDisable(true);
	    try{
            super.loadFactory();
            empresas = new EmpresasImpl(super.getManager());
            Empresa empresa = empresas.findById(new Long(1));
            if(empresa==null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cadatro de Empresa");
                alert.setHeaderText("Confirme o cadastro da sua empresa");
                alert.setContentText("O cadastro da sua empresa ainda não foi configurado\nDeseja incluir agora?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Stage stage = new Stage();
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Empresa.fxml"));
                    loader.setController(new EmpresaCadastroController(stage));
                    final Parent root = loader.load();
                    final Scene scene = new Scene(root);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.showAndWait();
                }
            }
        }catch (Exception e){
        }
	}
	@FXML
    void caixa(ActionEvent event){

    }
	@FXML
    void cliente(ActionEvent event) {
    	try {
			Stage primaryStage = new Stage();
        	//Icons estilo = Icons.getInstance();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientePesquisa.fxml"));
            loader.setController(new ClientePesquisaController(stage));
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
            super.alert(Alert.AlertType.ERROR, "Erro",
                    "Erro ao abrir o cadastro", "Falha ao localizar o arquivo Empresa.fxml",e,true);
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
            super.alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro",
                    "Falha ao localizar o arquivo ProdutoPesquisa.fxml",e,true);
        }
    }
    @FXML
    void delivery(ActionEvent event){
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryPesquisa.fxml"));
            loader.setController(new PedidoDeliveryPesquisaController(stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        }catch(IOException e) {
            e.printStackTrace();
            super.alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro",
                    "Falha ao localizar o arquivo ProdutoPesquisa.fxml",e,true);
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
            loader.setController(new UsuarioPesquisaController(stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
            super.alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro",
                    "Falha ao localizar o arquivo Empresa.fxml",e,true);
        }
    }
}