package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.model.pedido.Pedido;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.repository.helper.PedidosDeliveryImpl;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PedidoDeliveryPesquisaController extends UtilsController implements Initializable{
	@FXML
	private JFXTextField txPesquisa;
	@FXML
	private TableView<PedidoDelivery> tbPrincipal;
	private Stage stage;
	private PedidosDeliveryImpl pedidos;

	public PedidoDeliveryPesquisaController(Stage stage) {
		this.stage = stage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			super.loadFactory();
			pedidos = new PedidosDeliveryImpl(super.getManager());
			tbPrincipal.getItems().clear();
			tbPrincipal.getItems().addAll(pedidos.getAll());
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", "Erro ao lista clientes", "Falha ao listar clientes",e,true);
		}finally {
			super.close();
		}
	}

	private	void abrirCadastro(PedidoDelivery pedidoDelivery){
		try {
			Stage stage = new Stage();
		    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PedidoDeliveryCadastro.fxml"));
	        loader.setController(new PedidoDeliveryCadastroController(pedidoDelivery,stage));
	        final Parent root = loader.load();
	        final Scene scene = new Scene(root);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        //stage.initStyle(StageStyle.UNDECORATED);
	        stage.setScene(scene);
	        stage.show();
			stage.setOnHiding(event -> filtrar());
		}catch(IOException e) {
			alert(AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", "Falha ao abrir cadastro do Delivery",e,true);
		}
	}
	@FXML
	private void cadastrar(ActionEvent event) {
		abrirCadastro(null);
	}

	boolean excluir(PedidoDelivery pedidoDelivery) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Exclusão...");
		alert.setHeaderText(null);
		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setContentText("Tem certeza disso?");
		Optional<ButtonType> optional = alert.showAndWait();
		if (optional.get() == ButtonType.OK) {
			try {
				super.loadFactory();
				pedidos = new PedidosDeliveryImpl(super.getManager());
				PedidoDelivery u = pedidos.findById(pedidoDelivery.getId().longValue());
				pedidos.remove(u);
				alert(AlertType.INFORMATION, "Sucesso", null, "Removido com sucesso!",null, false);
				return true;
			} catch (Exception e) {
				super.alert(AlertType.ERROR,"Erro ao excluir",null,
						"Falha ao tentar excluir o registro do Delivery",e,true);
				return false;
			} finally {
				close();
			}
		}
		else return false;

	}

	private void filtrar() {
		try {
			super.loadFactory();
			pedidos = new PedidosDeliveryImpl(super.getManager());
			tbPrincipal.getItems().clear();
			//List<PedidoDelivery> pedidosDelivery = pedidos.filtrar(txPesquisa.getText().trim(),1,"pessoa.nome");
			tbPrincipal.getItems().addAll(pedidos.getAll());
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", "Erro ao lista pedidos", "Falha ao listar pedidos",e,true);
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
		TableColumn<PedidoDelivery, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

//		TableColumn<PedidoDelivery, Pessoa> columnNome = new  TableColumn<>("Nome");
//		columnNome.setCellValueFactory(new PropertyValueFactory<>("pessoa"));
//		columnNome.setCellFactory(param -> new TableCell<Usuario,Pessoa>(){
//			@Override
//			protected void updateItem(Pessoa item, boolean empty) {
//				super.updateItem(item, empty);
//				if(item==null){
//					setStyle("");
//					setText("");
//					setGraphic(null);
//				}
//				else{
//					setText(item.getNome());
//				}
//			}
//		});
//		columnNome.setPrefWidth(250);
//		columnNome.setMaxWidth(320);

		TableColumn<PedidoDelivery, Number> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaEditar.setCellFactory(param -> new TableCell<PedidoDelivery,Number>(){
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
		TableColumn<PedidoDelivery, Number> colunaExcluir = new  TableColumn<>("");
		colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaExcluir.setCellFactory(param -> new TableCell<PedidoDelivery,Number>(){
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
		tbPrincipal.getColumns().addAll(columnId,colunaEditar,colunaExcluir);
		tbPrincipal.setTableMenuButtonVisible(true);
	}
	@FXML
	void sair(ActionEvent event){
		stage.close();
	}
}
