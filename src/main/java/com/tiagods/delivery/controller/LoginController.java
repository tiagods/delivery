package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.model.Usuario;
import com.tiagods.delivery.repository.helper.UsuariosImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.CriptografiaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController extends UtilsController implements Initializable{
    @FXML
    private JFXPasswordField txSenha;
    @FXML
    private JFXComboBox<Usuario> cbNome;
    @FXML
    private JFXButton btnEntrar;
    @FXML
    private JFXButton btnCancelar;
    private UsuariosImpl usuarios;
    private Stage stage;

    public LoginController(Stage stage){
        this.stage=stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadFactory();
            usuarios = new UsuariosImpl(super.getManager());
            List<Usuario> contas = usuarios.filtrar("", 1, "pessoa.nome");
            cbNome.getItems().addAll(contas);
            cbNome.getSelectionModel().selectFirst();
        }catch(Exception e) {
            super.alert(Alert.AlertType.ERROR,"Login",null,"Erro ao listar Logins",e,true);
        }finally {
            super.close();
        }
        txSenha.requestFocus();
    }

    @FXML
    public void enterAcionado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            logon();
        }
    }
    @FXML
    public void entrar(ActionEvent event) {
        logon();
    }
    private void logon() {
        String mensagem;
        if (cbNome.getValue() == null || txSenha.getText().equals("")) {
            mensagem = "Usuario ou senha em branco!";
            super.alert(Alert.AlertType.ERROR,"Erro",null,"Senha em branco",null,false);
            return;
        } else {
            try {
                loadFactory();
                usuarios = new UsuariosImpl(super.getManager());
                Usuario usuario = usuarios.findByLoginAndSenha(
                        cbNome.getValue().getLogin(),
                        new CriptografiaUtil().criptografar(txSenha.getText().trim())
                );
                if (usuario != null) {
                    UsuarioLogado.getInstance().setUsuario(usuario);
                    Stage stage1 = new Stage();
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
                    loader.setController(new MenuController());
                    final Parent root = loader.load();
                    final Scene scene = new Scene(root);
                    stage1.setScene(scene);
                    stage1.show();
                    stage.close();
                }
                else
                    super.alert(Alert.AlertType.ERROR,"Erro",null,"usuario ou senha inválidos",null,false);
            }catch(Exception e) {
                super.alert(Alert.AlertType.ERROR,"Erro",null,"Falha ao buscar usuario",e,true);
                e.printStackTrace();
            }finally {
                close();
            }
        }
    }

    @FXML
    void sair(ActionEvent event) {
        System.exit(0);
    }
}
