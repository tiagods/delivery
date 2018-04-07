package com.tiagods.delivery;


import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.tiagods.delivery.config.JPAConfig;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.MenuController;
import com.tiagods.delivery.controller.PersistenciaController;
import com.tiagods.delivery.controller.UsuarioCadastroController;
import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Usuario;
import com.tiagods.delivery.repository.helper.UsuariosImpl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.persistence.EntityManager;
import java.io.IOException;

public class StartApplication extends Application {
	@Override
    public void start(Stage primaryStage) {
        EntityManager manager = null;
        try {
            manager = JPAConfig.getInstance().createManager();
            UsuariosImpl usuarios = new UsuariosImpl(manager);
            if(usuarios.getAll().size()==10){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Conta de Usuário");
                alert.setContentText("Por favor cadastre uma conta para acessar o sistema!");
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
                    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            final UsuarioLogado ul = UsuarioLogado.getInstance();
                            if(ul.getUsuario()==null) {
                                alert.setTitle("Conta de Usuário");
                                alert.setContentText("Nenhum usuario foi identificado!");
                                alert.showAndWait();
                                System.exit(0);
                            }
                        }
                    });
               }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            else{
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
        }catch (Exception e){
            //super.alert(Alert.AlertType.ERROR,"Pesquisa de Usuarios", null, "Falha ao listar usuarios");
        }finally {
            if (manager != null) manager.close();
        }
    }
    public static void main(String[] arg0) {
    	launch(arg0);
    }
}
