package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.skins.JFXToggleNodeSkin;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProdutoPesquisaController extends UtilsController implements Initializable{

	@FXML
	private JFXToggleButton tgProduto;

	@FXML
	private JFXToggleButton tgPizza;

	@FXML
	private JFXToggleButton tgAdicional;

	@FXML
	private JFXToggleButton tgObservacao;

	@FXML
	private AnchorPane pnProduto;

	@FXML
	private TableView<?> tbProduto;

	@FXML
	private AnchorPane pnPizza;

	@FXML
	private TableView<?> tbPizza;

	@FXML
	private AnchorPane pnAdicional;

	@FXML
	private TableView<?> tbAdicional;

	@FXML
	private AnchorPane pnObservacao;

	@FXML
	private TableView<?> tbObservacao;
	private Stage stage;
	public ProdutoPesquisaController(Stage stage) {
		this.stage=stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			super.loadFactory();
			combos();
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

	private void combos(){
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(tgProduto,tgPizza,tgAdicional,tgObservacao);
	}
	private void filtrar() {
		try {
			super.loadFactory();
	//		clientes = new ClientesImpl(super.getManager());
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
//		tbPrincipal.getColumns().addAll(columnId);
//		tbPrincipal.setTableMenuButtonVisible(true);
	}
}
