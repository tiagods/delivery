package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.rmi.CORBA.Util;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfaceController extends UtilsController implements Initializable{
    JFXButton btnNovo,btnEditar,btnSalvar,btnExcluir,btnCancelar;
    AnchorPane pnCadastro;
    TableView tbPrincipal;
    Object object;

    private void combos(){

    }
    @FXML
    void editar(ActionEvent event) {
        super.desbloquear(true, pnCadastro.getChildren());
    }
    @FXML
    void excluir(ActionEvent event) {
        super.desbloquear(false, pnCadastro.getChildren());
        super.limpar(pnCadastro.getChildren());
    }
    void filtrar(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.Initializer(btnNovo, btnEditar, btnSalvar, btnExcluir, btnCancelar);
        combos();
    }
    @FXML
    void novo(ActionEvent event) {
        super.desbloquear(true, pnCadastro.getChildren());
        super.limpar(pnCadastro.getChildren());
    }
    void pesquisar(KeyEvent event) {
        filtrar();
    }

    void preencherFormulario(Object objetc) {
        this.object = objetc;
    }

    @FXML
    void salvar(ActionEvent event) {
        preencherFormulario(object);
        super.desbloquear(false, pnCadastro.getChildren());
    }
        @SuppressWarnings("unchecked")
    private void tabela() {
        TableColumn<Object, Number> columnId = new TableColumn<>("*");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnId.setPrefWidth(40);
//		columnId.setCellFactory((TableColumn<Cliente, PfPj> param) -> new TableCell<Cliente, PfPj>() {
//			@Override
//			protected void updateItem(PfPj item, boolean empty) {
//				super.updateItem(item, empty);
//				if (item == null) {
//					setText(null);
//					setStyle("");
//				} else {
//					setText(item.getTelefone());
//				}
//			}
//		});
        tbPrincipal.getColumns().addAll(columnId);
        tbPrincipal.setTableMenuButtonVisible(true);
    }

}
