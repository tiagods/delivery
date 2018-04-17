package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.pedido.PedidoItemAcoObsCadastroController;
import com.tiagods.delivery.controller.pedido.PedidoItemPizzaAcoObsCadastroController;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoProdutoItemAdicional;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.repository.helper.PedidosProdutosItensAddImpl;
import com.tiagods.delivery.repository.helper.PedidosProdutosItensImpl;
import com.tiagods.delivery.repository.helper.PizzasImpl;
import com.tiagods.delivery.repository.helper.ProdutosImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PedidoProdutoPesquisaPizzaController extends UtilsController implements Initializable{
	@FXML
	private JFXTextField txPesquisa;

	@FXML
	private AnchorPane pnTabelas;

	@FXML
	private TableView<Pizza> tbPrincipal;

	private Pedido pedido;
	private Stage stage;
	private PedidoProdutoItem produtoItem;

	private PizzasImpl produtos;
	private PedidosProdutosItensAddImpl items;

	public PedidoProdutoPesquisaPizzaController(Pedido pedido, PedidoProdutoItem produtoItem, Stage stage) {
		this.pedido = pedido;
		this.stage=stage;
		this.produtoItem=produtoItem;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			tbPrincipal.getItems().clear();
			super.loadFactory();
			produtos = new PizzasImpl(super.getManager());
			List<Pizza> listaProdutos = produtos.getAll();
			tbPrincipal.getItems().addAll(listaProdutos);
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", null, "Erro ao listar produtos", e, true);
			e.printStackTrace();
		}finally {
			super.close();
		}

	}
	@FXML
	private void cadastrar(ActionEvent event) {

    }
	private void filtrar() {
		try {
			tbPrincipal.getItems().clear();
			super.loadFactory();
			produtos = new PizzasImpl(super.getManager());
			List<Pizza> listaProdutos = produtos.findByNome(txPesquisa.getText());
			tbPrincipal.getItems().addAll(listaProdutos);
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", null, "Erro ao listar produtos", e, true);
			e.printStackTrace();
		}finally {
			super.close();
		}
	}
	@FXML
	public void pesquisar(KeyEvent event) {
		filtrar();
	}


	private void tabela() {
		TableColumn<Pizza, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<Pizza, ProdutoCategoria> columnCategoria = new  TableColumn<>("Categoria");
		columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

		TableColumn<Pizza, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);
		columnNome.setMaxWidth(320);

		TableColumn<Pizza, BigDecimal> colunaCusto = new  TableColumn<>("Custo");
		colunaCusto.setCellValueFactory(new PropertyValueFactory<>("custo"));
		colunaCusto.setCellFactory(param -> new TableCell<Pizza,BigDecimal>(){
			@Override
			protected void updateItem(BigDecimal item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
				}
				else if(item!=null) {
					Locale locale = new Locale("pt", "BR");
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
					setText(currencyFormatter.format(item.doubleValue()));
				}
			}
		});
		TableColumn<Pizza, BigDecimal> colunaValor = new  TableColumn<>("Valor");
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("venda"));
		colunaValor.setCellFactory(param -> new TableCell<Pizza,BigDecimal>(){
			@Override
			protected void updateItem(BigDecimal item, boolean empty) {
				super.updateItem(item, empty);
//				if(item==null){
//					setStyle("");
//					setText("");
//				}
//				else{
//					Locale locale = new Locale("pt", "BR");
//					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
//					setText(currencyFormatter.format(item.doubleValue()));
//				}
			}
		});

		TableColumn<Pizza, Number> colunaExcluir = new  TableColumn<>("");
		colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaExcluir.setCellFactory(param -> new TableCell<Pizza,Number>(){
			JFXButton button = new JFXButton("Incluir e Editar");
			@Override
			protected void updateItem(Number item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					Pizza p = tbPrincipal.getItems().get(getIndex());
					button.getStyleClass().add("btGreen");
					button.setOnAction(event -> {
						PedidoProdutoItemAdicional produtoItem = salvarProdutoDiretoNoPedido(p);
						if(produtoItem!=null){
							editarProduto(produtoItem);
						}
					});
					setGraphic(button);
				}
			}
		});
		tbPrincipal.getColumns().addAll(columnCategoria,columnNome,colunaCusto,colunaValor,colunaExcluir);
		tbPrincipal.setTableMenuButtonVisible(true);
	}
	PedidoProdutoItemAdicional salvarProdutoDiretoNoPedido(Pizza p){
		PedidoProdutoItemAdicional produtoItem = new PedidoProdutoItemAdicional();
		produtoItem.setCriadoEm(Calendar.getInstance());
		produtoItem.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
		produtoItem.setQuantidade(1);
		//produtoItem.setValor((p.getVenda());
		produtoItem.setNome(p.getNome());
		produtoItem.setProduto(p);
		try{
			produtoItem.setPedido(pedido);
			super.loadFactory();
			PedidosProdutosItensAddImpl items = new PedidosProdutosItensAddImpl(getManager());
			items.save(produtoItem);
			alert(AlertType.INFORMATION,"Sucesso",null,
					"Adicionado com sucesso!",null,false);
			return produtoItem;
		}catch (Exception e){
			alert(AlertType.ERROR, "Erro", null, "Erro ao adicionar produto", e, true);
			return null;
		}finally {
			close();
		}
	}
	void editarProduto(PedidoProdutoItemAdicional item){
		try {
			Stage stage = new Stage();
			FXMLLoader loader = null;
			if(item.getProduto() instanceof Pizza) {
				loader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPedidoItemPizza.fxml"));
				loader.setController(new PedidoItemPizzaAcoObsCadastroController(item,stage));
			}
			final Parent root = loader.load();
			final Scene scene = new Scene(root);
			stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			alert(AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", "Falha ao abrir cadastro do Delivery",e,true);
		}
	}

	@FXML
	void sair(ActionEvent event){
		stage.close();
	}
}
