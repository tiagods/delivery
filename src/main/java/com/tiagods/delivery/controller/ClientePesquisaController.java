package com.tiagods.delivery.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.model.Cliente;

import com.tiagods.delivery.model.ClienteRegistrado;
import com.tiagods.delivery.repository.helper.ClientesComunsImpl;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import com.tiagods.delivery.repository.helper.ClientesRegistradosImpl;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.fxutils.maskedtextfield.MaskTextField;
import org.fxutils.maskedtextfield.MaskedTextField;

import javax.rmi.CORBA.Util;

public class ClientePesquisaController extends UtilsController implements Initializable{
	@FXML
	private JFXTextField txPesquisa;
	@FXML
	private TableView<Cliente> tbPrincipal;
	@FXML
	private JFXComboBox<String> cbStatus;

	private ClientesRegistradosImpl registrados;
	private ClientesComunsImpl comuns;
	private ClientesImpl geral;

	private Stage stage;
	public ClientePesquisaController(Stage stage) {
		this.stage =stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			super.loadFactory();
			geral = new ClientesImpl(super.getManager());
			tbPrincipal.getItems().clear();
			tbPrincipal.getItems().addAll(geral.getAll());
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
				registrados = new ClientesRegistradosImpl(getManager());
				ClienteRegistrado cli = registrados.findById(cliente.getId().longValue());
				registrados.remove(cli);
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
			registrados = new ClientesRegistradosImpl(super.getManager());
			tbPrincipal.getItems().clear();
			List<ClienteRegistrado> clienteList = registrados.filtrar(txPesquisa.getText().trim(),"nome");
			tbPrincipal.getItems().addAll(clienteList);
			tbPrincipal.refresh();
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
		TableColumn<Cliente, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);

		TableColumn<Cliente, String> colunaTelefone = new  TableColumn<>("Telefone");
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colunaTelefone.setCellFactory((TableColumn<Cliente, String> param) -> new TableCell<Cliente, String>() {
			final MaskedTextField mask = new MaskedTextField();
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				mask.setEditable(false);
				mask.setMask("(##) ####-####");
				if (item == null || item.equals("")) {
					setText(null);
					setStyle("");
					setGraphic(null);
				} else {
					mask.setStyle("");
					mask.setPlainText(item);
					//if(!txPesquisa.getText().equals("")) mask.setStyle("-fx-text-fill: green");
					setGraphic(mask);

				}
			}
		});
		TableColumn<Cliente, String> colunaCelular = new  TableColumn<>("Celular");
		colunaCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
		colunaCelular.setCellFactory((TableColumn<Cliente, String> param) -> new TableCell<Cliente, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				final MaskedTextField mask = new MaskedTextField();
				mask.setEditable(false);
				mask.setMask("(##) ####-####");

				super.updateItem(item, empty);
				if (item == null || item.equals("")) {
					setText(null);
					setStyle("");
					setGraphic(null);
				} else {
					mask.setStyle("");
					mask.setPlainText(item);
					//if(!txPesquisa.getText().equals("")) mask.setStyle("-fx-text-fill: green");
					setGraphic(mask);				}
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
                    button.getStyleClass().add("btGreen");
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
				    button.getStyleClass().add("btRed");
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
	@FXML
	void sair(ActionEvent event){
		stage.close();
	}
}
