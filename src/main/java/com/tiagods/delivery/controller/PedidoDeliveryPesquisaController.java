package com.tiagods.delivery.controller;

import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXComboBoxListViewSkin;
import com.tiagods.delivery.model.Cliente;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.repository.helper.PedidosDeliveryImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PedidoDeliveryPesquisaController extends UtilsController implements Initializable{
	@FXML
	private JFXTextField txPesquisa;
	@FXML
	private TableView<PedidoDelivery> tbPrincipal;
	private Stage stage;
	private PedidosDeliveryImpl pedidos;
	Locale locale = new Locale("pt", "BR");
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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
	        loader.setController(new PedidoDeliveryCadastroController(pedidoDelivery,null, "",stage));
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
		TableColumn<PedidoDelivery, Number> columnId = new  TableColumn<>("Pedido");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<PedidoDelivery, Calendar> colunaData = new  TableColumn<>("Data");
		colunaData.setCellValueFactory(new PropertyValueFactory<>("criadoEm"));
		colunaData.setCellFactory(param -> new TableCell<PedidoDelivery,Calendar>(){
			@Override
			protected void updateItem(Calendar item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					setText(sdf.format(item.getTime()));
				}
			}
		});
		colunaData.setPrefWidth(120);

		TableColumn<PedidoDelivery, Cliente> colunaCliente = new  TableColumn<>("Cliente");
		colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colunaCliente.setCellFactory(param -> new TableCell<PedidoDelivery,Cliente>(){
			@Override
			protected void updateItem(Cliente item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					setText(item.getNome());
				}
			}
		});
		TableColumn<PedidoDelivery, Cliente> colunaTelefone = new  TableColumn<>("Telefone");
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colunaTelefone.setCellFactory(param -> new TableCell<PedidoDelivery,Cliente>(){
			@Override
			protected void updateItem(Cliente item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					setText(item.getTelefone());
				}
			}
		});
		TableColumn<PedidoDelivery, Cliente> colunaCelular = new  TableColumn<>("Celular");
		colunaCelular.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colunaCelular.setCellFactory(param -> new TableCell<PedidoDelivery,Cliente>(){
			@Override
			protected void updateItem(Cliente item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					setText(item.getCelular());
				}
			}
		});
		TableColumn<PedidoDelivery, Cliente> colunaEndereco = new  TableColumn<>("Endereco");
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colunaEndereco.setCellFactory(param -> new TableCell<PedidoDelivery,Cliente>(){
			@Override
			protected void updateItem(Cliente item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					setText(item.getEndereco());
				}
			}
		});
		TableColumn<PedidoDelivery, PedidoDelivery.PedidoStatus> colunaStatus = new  TableColumn<>("Status");
		colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		colunaStatus.setCellFactory(param -> new TableCell<PedidoDelivery,PedidoDelivery.PedidoStatus>(){
			@Override
			protected void updateItem(PedidoDelivery.PedidoStatus item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					JFXButton rb = new JFXButton();
					rb.setText(item.getDescricao());
					rb.getStyleClass().add(item.getStyle());

					rb.onActionProperty().set(event -> {

						Dialog dialog = new Dialog();
						dialog.setTitle("Alteração de Status");
						dialog.setHeaderText("Selecione um status");

						VBox stackPane = new VBox();
						stackPane.setSpacing(15);

						Map<JFXRadioButton,PedidoDelivery.PedidoStatus> map = new HashMap<>();
						ToggleGroup group = new ToggleGroup();
						for(PedidoDelivery.PedidoStatus ps : PedidoDelivery.PedidoStatus.values()){
							JFXRadioButton jfxRadioButton = new JFXRadioButton(ps.getDescricao());
							jfxRadioButton.setSelectedColor(ps.getColor());
							jfxRadioButton.setUnSelectedColor(ps.getColor());
							if(ps.equals(item)) jfxRadioButton.setSelected(true);
							group.getToggles().add(jfxRadioButton);
							stackPane.getChildren().add(jfxRadioButton);
							map.put(jfxRadioButton,ps);
						}
						ButtonType ok = new ButtonType("Alterar");
						ButtonType cancelar = new ButtonType("Cancelar");
						dialog.getDialogPane().getButtonTypes().addAll(ok,cancelar);
						dialog.getDialogPane().setContent(stackPane);
						dialog.initModality(Modality.APPLICATION_MODAL);
						dialog.initStyle(StageStyle.UNDECORATED);
						Optional<ButtonType> result = dialog.showAndWait();
						if(result.get() == ok){
							long cod = tbPrincipal.getItems().get(getIndex()).getId().longValue();
							for(Node f : stackPane.getChildren()){
								if(f instanceof JFXRadioButton && ((JFXRadioButton) f).isSelected()) {
									PedidoDelivery.PedidoStatus p = map.get(f);
									if (!p.equals(item) && salvarStatus(cod, p)) {
										((JFXRadioButton) f).setSelected(true);
										setGraphic(f);
									}
									break;
								}
							};
						}
                    });
					setGraphic(rb);

				}
			}
		});
		TableColumn<PedidoDelivery, BigDecimal> colunaValor = new  TableColumn<>("Total");
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("total"));
		colunaValor.setCellFactory(param -> new TableCell<PedidoDelivery,BigDecimal>(){
			@Override
			protected void updateItem(BigDecimal item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else {
					setText(currencyFormatter.format(item.doubleValue()));
				}
			}
		});

		TableColumn<PedidoDelivery, Boolean> colunaPagamento = new  TableColumn<>("Situação");
		colunaPagamento.setCellValueFactory(new PropertyValueFactory<>("pago"));
		colunaPagamento.setCellFactory(param -> new TableCell<PedidoDelivery,Boolean>(){
			JFXRadioButton rb = new JFXRadioButton("");
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else {
					rb.setSelected(true);
					rb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        rb.setSelected(true);
                    });
					if(item){
						rb.setSelectedColor(Color.GREEN);
						rb.setText("Pago");
					}
					else{
						rb.setSelectedColor(Color.RED);
						rb.setText("Pendente");
					}
					setGraphic(rb);
				}
			}
		});
		colunaPagamento.setPrefWidth(120);

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
					button.getStyleClass().add("btGreen");
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
					button.getStyleClass().add("btRed");
					button.setOnAction(event -> {
						boolean removed = excluir(tbPrincipal.getItems().get(getIndex()));
						if(removed) tbPrincipal.getItems().remove(getIndex());
					});
					setGraphic(button);
				}
			}
		});
		tbPrincipal.getColumns().addAll(columnId,colunaData,colunaCliente,colunaTelefone,colunaCelular,
				colunaEndereco,colunaStatus,colunaPagamento,colunaValor,
				colunaEditar,colunaExcluir);
	}
	private boolean salvarStatus(long i, PedidoDelivery.PedidoStatus status){
		try{
			loadFactory();
			pedidos = new PedidosDeliveryImpl(getManager());
			PedidoDelivery d = pedidos.findById(i);
			if(d.getFimEntrega()==null && status.equals(PedidoDelivery.PedidoStatus.ENTREGUE))
				d.setFimEntrega(Calendar.getInstance());
			d.setStatus(status);
			pedidos.save(d);
			return true;
		}catch (Exception e){
			alert(AlertType.ERROR,"Erro",null,"Erro ao salvar",e,true);
			return false;
		}finally {
			close();
		}
	}
	@FXML
	void sair(ActionEvent event){
		stage.close();
	}
}
