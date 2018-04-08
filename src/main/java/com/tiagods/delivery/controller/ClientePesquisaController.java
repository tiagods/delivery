package com.tiagods.delivery.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.model.Cliente;

import com.tiagods.delivery.model.Pessoa;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import javax.rmi.CORBA.Util;

public class ClientePesquisaController extends UtilsController implements Initializable{
	@FXML
	private JFXTextField txPesquisa;
	@FXML
	private TableView<Cliente> tbPrincipal;
	private ClientesImpl clientes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			super.loadFactory();
			clientes = new ClientesImpl(super.getManager());
			tbPrincipal.getItems().clear();
			tbPrincipal.getItems().addAll(clientes.getAll());
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", null, "Erro ao lista clientes", e, true);
			e.printStackTrace();
		}finally {
			super.close();
		}
	}
	
	private	void abrirCadastro(Cliente cliente){
		try { 	
			Stage stage = new Stage();
		    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClienteCadastro.fxml"));
	        loader.setController(new ClienteCadastroController(cliente,stage));
	        final Parent root = loader.load();
	        final Scene scene = new Scene(root);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        //stage.initStyle(StageStyle.UNDECORATED);
	        stage.setScene(scene);
	        stage.show();
	        stage.setOnHiding(event -> filtrar());
		}catch(IOException e) {
			e.printStackTrace();
			alert(AlertType.ERROR, "Erro", null, "Erro ao abrir o cadastro", e,true);
		}
	}
	@FXML
	private void cadastrar(ActionEvent event) {
		abrirCadastro(null);
	}

	boolean excluir(Cliente cliente) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Exclusão...");
		alert.setHeaderText(null);
		alert.setAlertType(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Tem certeza disso?");
		Optional<ButtonType> optional = alert.showAndWait();
		if (optional.get() == ButtonType.OK) {
			try{
				super.loadFactory();
				clientes = new ClientesImpl(getManager());
				Cliente cli = clientes.findById(cliente.getId().longValue());
				clientes.remove(cli);
				alert(AlertType.INFORMATION, "Sucesso", null, "Removido com sucesso!",null, false);
				return true;
			}catch(Exception e){
				super.alert(Alert.AlertType.ERROR, "Erro", null,
						"Falha ao excluir o registro", e,true);
				return false;
			}finally{
				super.close();
			}
		}
		else return false;
	}
	private void filtrar() {
		try {
			super.loadFactory();
			clientes = new ClientesImpl(super.getManager());
			tbPrincipal.getItems().clear();
			List<Cliente> clienteList = clientes.filtrar(txPesquisa.getText().trim(),"nome");
			tbPrincipal.getItems().addAll(clienteList);
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", null, "Erro ao lista clientes", e, true);
			e.printStackTrace();
		}finally {
			super.close();
		}
	}
	@FXML
	void pesquisar(KeyEvent event) {
		filtrar();
	}
	@SuppressWarnings("unchecked")
	private void tabela() {
		TableColumn<Cliente, Number> columnId = new  TableColumn<>("*");
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
		TableColumn<Cliente, Pessoa> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("pessoa"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);
		columnNome.setCellFactory((TableColumn<Cliente, Pessoa> param) -> new TableCell<Cliente, Pessoa>() {
			@Override
			protected void updateItem(Pessoa item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
					setStyle("");
				} else {
					setText(item.getNome());
				}
			}
		});

		TableColumn<Cliente, Pessoa> colunaTelefone = new  TableColumn<>("Telefone");
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("pessoa"));
		colunaTelefone.setCellFactory((TableColumn<Cliente, Pessoa> param) -> new TableCell<Cliente, Pessoa>() {
			@Override
			protected void updateItem(Pessoa item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
					setStyle("");
				} else {
					setText(item.getTelefone());
				}
			}
		});
		TableColumn<Cliente, Pessoa> colunaCelular = new  TableColumn<>("Celular");
		colunaCelular.setCellValueFactory(new PropertyValueFactory<>("pessoa"));
		colunaCelular.setCellFactory((TableColumn<Cliente, Pessoa> param) -> new TableCell<Cliente, Pessoa>() {
			@Override
			protected void updateItem(Pessoa item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
					setStyle("");
				} else {
					setText(item.getCelular());
				}
			}
		});
		TableColumn<Cliente, Number> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaEditar.setCellFactory(param -> new TableCell<Cliente,Number>(){
			JFXButton button = new JFXButton("Editar");
			@Override
			protected void updateItem(Number item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					button.setOnAction(event -> {
						abrirCadastro(tbPrincipal.getItems().get(getIndex()));
                    });
					setGraphic(button);
				}
			}
		});
		TableColumn<Cliente, Number> colunaExcluir = new  TableColumn<>("");
		colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaExcluir.setCellFactory(param -> new TableCell<Cliente,Number>(){
			JFXButton button = new JFXButton("Excluir");
			@Override
			protected void updateItem(Number item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					button.setOnAction(event -> {
						boolean removed = excluir(tbPrincipal.getItems().get(getIndex()));
						if(removed) tbPrincipal.getItems().remove(getIndex());
					});
					setGraphic(button);
				}
			}
		});

		tbPrincipal.getColumns().addAll(columnId,columnNome,colunaTelefone,colunaCelular,colunaEditar,colunaExcluir);
		tbPrincipal.setTableMenuButtonVisible(true);
	}
}
