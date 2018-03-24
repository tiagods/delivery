package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.model.Cliente;
import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProdutoPesquisaController extends UtilsController implements Initializable{
	private TableView<Produto> tbPrincipal;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			super.loadFactory();
	//		clientes = new ClientesImpl(super.getManager());
			tbPrincipal.getItems().clear();
	//		tbPrincipal.getItems().addAll(clientes.getAll());
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", "Erro ao lista clientes", e.getMessage());
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
	        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					if(event.getEventType()==WindowEvent.WINDOW_CLOSE_REQUEST) {
						filtrar();
					}
				}
			});
		}catch(IOException e) {
			e.printStackTrace();
			alert(AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", e.getMessage());
		}
	}
	@FXML
	private void cadastrar(ActionEvent event) {
		abrirCadastro(null);
	}
	
	private void filtrar() {
		try {
			super.loadFactory();
	//		clientes = new ClientesImpl(super.getManager());
			tbPrincipal.getItems().clear();
	//		tbPrincipal.getItems().addAll(clientes.getAll());
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", "Erro ao lista clientes", e.getMessage());
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
		TableColumn<Produto, Number> columnId = new  TableColumn<>("*");
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
