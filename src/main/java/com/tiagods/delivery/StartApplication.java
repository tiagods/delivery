package com.tiagods.delivery;


import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.tiagods.delivery.config.JPAConfig;
import com.tiagods.delivery.config.LogConfig;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.*;
import com.tiagods.delivery.model.Empresa;
import com.tiagods.delivery.model.Usuario;
import com.tiagods.delivery.model.UsuarioNivel;
import com.tiagods.delivery.repository.helper.EmpresasImpl;
import com.tiagods.delivery.repository.helper.UsuariosImpl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Optional;

public class StartApplication extends Application {
	@Override
    public void start(Stage primaryStage) {
        LogConfig.getInstance();
        EntityManager manager = null;
        try {
            manager = JPAConfig.getInstance().createManager();
            UsuariosImpl usuarios = new UsuariosImpl(manager);
            if(usuarios.getAll().size()==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().setExpanded(true);
                alert.getDialogPane().setMinSize(400,150);
                alert.setTitle("Conta de Usuário");
                alert.setContentText("Por favor cadastre uma conta administrador para \nacessar o sistema!");
                alert.showAndWait();
                try {
                    Stage stage = new Stage();
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UsuarioCadastro.fxml"));
                    loader.setController(new UsuarioCadastroController(null,stage,true));
                    final Parent root = loader.load();
                    final Scene scene = new Scene(root);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                    stage.setOnHiding(event -> {
                        UsuarioLogado ul = UsuarioLogado.getInstance();
                        if(ul.getUsuario()==null) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.getDialogPane().setExpanded(true);
                            alert2.getDialogPane().setMinSize(400,150);
                            alert2.setHeaderText("Nenhum usuario foi identificado!");
                            alert2.setContentText("Cadastre um usuario para \nter o primeiro acesso ao sistema liberado");
                            alert2.showAndWait();
                            System.exit(0);
                        }
                    });
               }catch(IOException e) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.getDialogPane().setExpanded(true);
                    alert2.getDialogPane().setMinSize(400,600);
                    alert2.setTitle("Erro");
                    alert2.setContentText("Falha ao abrir Formulario de cadastro de Usuario!\n"+e);
                    alert2.showAndWait();
                    e.printStackTrace();
                }
            }
            else{
                try {
                    //Icons estilo = Icons.getInstance();
                    Stage stage = new Stage();
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    loader.setController(new LoginController(stage));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Acesso");
                    //stage.getIcons().add(new Image(estilo.getIcon().toString()));
                    stage.show();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.getDialogPane().setExpanded(true);
                    alert.getDialogPane().setMinSize(400,600);
                    alert.setTitle("Erro");
                    alert.setContentText("Falha ao abrir Menu Inicial!\n"+e);
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            //super.alert(Alert.AlertType.ERROR,"Pesquisa de Usuarios", null, "Falha ao listar usuarios");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().setExpanded(true);
            alert.getDialogPane().setMinSize(400,600);
            alert.setTitle("Erro");
            alert.setContentText("Falha ao listar usuarios!\n"+e);
            alert.showAndWait();
        }finally {
            if (manager != null) manager.close();
        }
    }
    public static void main(String[] arg0) {
    	launch(arg0);
    }
}
