package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.pedido.PedidoItemAcoObsCadastroController;
import com.tiagods.delivery.controller.pedido.PedidoItemPizzaAcoObsCadastroController;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.model.pedido.PedidoProduto;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.model.produto.pizza.PizzaFatia;
import com.tiagods.delivery.model.produto.pizza.PizzaGrande;
import com.tiagods.delivery.model.produto.pizza.PizzaMedia;
import com.tiagods.delivery.model.produto.pizza.PizzaPequena;
import com.tiagods.delivery.repository.helper.*;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class PedidoProdutoPesquisaController extends UtilsController implements Initializable{
	@FXML
	private JFXTextField txPesquisa;

	@FXML
	private AnchorPane pnTabelas;

	@FXML
	private TableView<Produto> tbPrincipal;

	private Pedido pedido;
	private Stage stage;

	private ProdutosImpl produtos;
	private PedidosProdutosItensImpl items;

	public PedidoProdutoPesquisaController(Pedido pedido, Stage stage) {
		this.pedido = pedido;
		this.stage=stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabela();
		try {
			tbPrincipal.getItems().clear();
			super.loadFactory();
			produtos = new ProdutosImpl(super.getManager());
			List<Produto> listaProdutos = produtos.getAll();
			tbPrincipal.getItems().addAll(listaProdutos);
		}catch (Exception e) {
			alert(AlertType.ERROR, "Erro", null, "Erro ao listar produtos", e, true);
			e.printStackTrace();
		}finally {
			super.close();
		}

	}

	private	void abrirCadastro(long id, Object object,String fxmlName){
		try {
			Stage stage = new Stage();
		    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+fxmlName+".fxml"));
		    if(object instanceof ProdutoGenerico)
		    	loader.setController(new ProdutoCadastroController((ProdutoGenerico)object,stage));
	        else if(object instanceof Pizza)
	        	loader.setController(new ProdutoCadastroController((Pizza) object,stage));
            else if(object instanceof Complemento)
                loader.setController(new ComplementoCadastroController((Complemento) object, stage));
            else if(object instanceof Observacao)
                loader.setController(new ObservacaoCadastroController((Observacao) object, stage));
	        final Parent root = loader.load();
	        final Scene scene = new Scene(root);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        //stage.initStyle(StageStyle.UNDECORATED);
	        stage.setScene(scene);
	        stage.show();
			stage.setOnHiding(event -> filtrar());
		}catch(IOException e) {
			e.printStackTrace();
			alert(AlertType.ERROR, "Erro", null, "Erro ao abrir o cadastro>"+fxmlName+">"+id,e,true);
		}
	}
	@FXML
	private void cadastrar(ActionEvent event) {

    }
	void editarProduto(PedidoProdutoItem item){
		try {
			Stage stage = new Stage();
			FXMLLoader loader = null;
			if(item.getProduto() instanceof Pizza) {
				loader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPedidoItemPizza.fxml"));
				loader.setController(new PedidoItemPizzaAcoObsCadastroController(item,stage));
			}
			else {
				loader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPedidoItem.fxml"));
				loader.setController(new PedidoItemAcoObsCadastroController(item,stage));
			}
			final Parent root = loader.load();
			final Scene scene = new Scene(root);
			stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", "Falha ao abrir cadastro do Delivery",e,true);
		}
	}
	private void filtrar() {
		try {
			tbPrincipal.getItems().clear();
			super.loadFactory();
			produtos = new ProdutosImpl(super.getManager());
			List<Produto> listaProdutos = produtos.findByNomeOrCategoria(txPesquisa.getText());
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
		TableColumn<Produto, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<Produto, ProdutoCategoria> columnCategoria = new  TableColumn<>("Categoria");
		columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

		TableColumn<Produto, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);
		columnNome.setMaxWidth(320);

		TableColumn<Produto, BigDecimal> colunaCusto = new  TableColumn<>("Custo");
		colunaCusto.setCellValueFactory(new PropertyValueFactory<>("custo"));
		colunaCusto.setCellFactory(param -> new TableCell<Produto,BigDecimal>(){
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
		colunaCusto.setPrefWidth(100);

		TableColumn<Produto, BigDecimal> colunaValor = new  TableColumn<>("Valor");
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("venda"));
		colunaValor.setCellFactory(param -> new TableCell<Produto,BigDecimal>(){
			@Override
			protected void updateItem(BigDecimal item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
				}
				else{
					Locale locale = new Locale("pt", "BR");
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
					setText(currencyFormatter.format(item.doubleValue()));
				}
			}
		});
		colunaValor.setPrefWidth(100);
		TableColumn<Produto, ProdutoCategoria> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colunaEditar.setCellFactory(param -> new TableCell<Produto,ProdutoCategoria>(){
			JFXButton button = new JFXButton("Incluir");
			@Override
			protected void updateItem(ProdutoCategoria item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null){
					setStyle("");
					setText("");
					setGraphic(null);
				}
				else{
					Produto p = tbPrincipal.getItems().get(getIndex());
					setGraphic(null);
					if(!item.getNome().equals("Pizzas")) {
						button.getStyleClass().add("btBlue");
						button.setOnAction(event -> {
							if(p instanceof ProdutoGenerico)
								salvarProdutoDiretoNoPedido((ProdutoGenerico)p);
						});
						setGraphic(button);
					}
				}
			}
		});
		colunaEditar.setPrefWidth(100);
		TableColumn<Produto, Number> colunaExcluir = new  TableColumn<>("");
		colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaExcluir.setCellFactory(param -> new TableCell<Produto,Number>(){
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
					Produto p = tbPrincipal.getItems().get(getIndex());
					button.getStyleClass().add("btGreen");
					button.setOnAction(event -> {
						PedidoProdutoItem produtoItem = new PedidoProdutoItem();
						produtoItem.setCriadoEm(Calendar.getInstance());
						produtoItem.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
						produtoItem.setQuantidade(1);
						if(p instanceof ProdutoGenerico)
							produtoItem.setValor(((ProdutoGenerico) p).getVenda());
						produtoItem.setNome(p.getNome());
						produtoItem.setProduto(p);
						produtoItem.setPedido(pedido);
						editarProduto(produtoItem);
					});
					setGraphic(button);
				}
			}
		});
		tbPrincipal.getColumns().addAll(columnCategoria,columnNome,colunaCusto,colunaValor,colunaEditar,colunaExcluir);
		tbPrincipal.setTableMenuButtonVisible(true);
	}
	PedidoProdutoItem salvarProdutoDiretoNoPedido(ProdutoGenerico p){
		PedidoProdutoItem produtoItem = new PedidoProdutoItem();
		produtoItem.setCriadoEm(Calendar.getInstance());
		produtoItem.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
		produtoItem.setQuantidade(1);
		produtoItem.setValor(((ProdutoGenerico) p).getVenda());
		produtoItem.setNome(p.getNome());
		produtoItem.setProduto(p);
		produtoItem.setPedido(pedido);
		try{
			super.loadFactory();
			PedidosProdutosItensImpl items = new PedidosProdutosItensImpl(getManager());
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


	@FXML
	void sair(ActionEvent event){
		stage.close();
	}
}
