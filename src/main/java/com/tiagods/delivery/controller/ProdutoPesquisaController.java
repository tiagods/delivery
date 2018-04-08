package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.repository.helper.ComplementosImpl;
import com.tiagods.delivery.repository.helper.ObservacaoImpl;
import com.tiagods.delivery.repository.helper.PizzasImpl;
import com.tiagods.delivery.repository.helper.ProdutosGenericosImpl;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
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
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProdutoPesquisaController extends UtilsController implements Initializable{

	@FXML
	private JFXToggleButton tgProduto;

	@FXML
	private JFXToggleButton tgPizza;

	@FXML
	private JFXToggleButton tgComplemento;

	@FXML
	private JFXToggleButton tgObservacao;

	@FXML
	private AnchorPane pnProduto;

	@FXML
	private TableView<ProdutoGenerico> tbProduto;

	@FXML
	private AnchorPane pnPizza;

	@FXML
	private TableView<Pizza> tbPizza;

	@FXML
	private AnchorPane pnComplemento;

	@FXML
	private TableView<Complemento> tbComplemento;

	@FXML
	private AnchorPane pnObservacao;
	@FXML
	private AnchorPane pnTabelas;

	@FXML
	private TableView<Observacao> tbObservacao;
	@FXML
	private JFXTextField txPesquisa;

	private Stage stage;

	private PizzasImpl pizzas;
	private ProdutosGenericosImpl genericos;
	private ComplementosImpl complementos;
	private ObservacaoImpl observacao;

	private String PRODUTO_CADASTRO="ProdutoCadastro";
    private String COMPLEMENTO_CADASTRO="ComplementoCadastro";
	private String OBSERVACAO_CADASTRO="ObservacaoCadastro";


	public ProdutoPesquisaController(Stage stage) {
		this.stage=stage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabelas();
		combos();
		tabelaAtivar();
	}
	
	private	void abrirCadastro(Object object,String fxmlName){
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
	        stage.setOnCloseRequest(event -> {
                if(event.getEventType()==WindowEvent.WINDOW_CLOSE_REQUEST) {
                    filtrar();
                }
            });
		}catch(IOException e) {
			e.printStackTrace();
			alert(AlertType.ERROR, "Erro", null, "Erro ao abrir o cadastro", e,true);
		}
	}
	@FXML
	private void cadastrar(ActionEvent event) {
		if(tgProduto.isSelected())
			abrirCadastro(new ProdutoGenerico(),PRODUTO_CADASTRO);
		else if(tgPizza.isSelected())
		    abrirCadastro(new Pizza(), PRODUTO_CADASTRO);
        else if(tgComplemento.isSelected())
            abrirCadastro(new Complemento(), COMPLEMENTO_CADASTRO);
        else if(tgObservacao.isSelected())
            abrirCadastro(new Observacao(),OBSERVACAO_CADASTRO);
    }

	private void combos(){
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(tgProduto,tgPizza,tgComplemento,tgObservacao);
		tgProduto.setSelected(true);

		ChangeListener changeListener = (observable, oldValue, newValue) -> {
			tabelaAtivar();
        };
		tgProduto.selectedProperty().addListener(changeListener);
		tgPizza.selectedProperty().addListener(changeListener);
		tgComplemento.selectedProperty().addListener(changeListener);
		tgObservacao.selectedProperty().addListener(changeListener);

	}
	private void filtrar() {
		try {
			super.loadFactory();
			if(tgProduto.isSelected()){
				genericos = new ProdutosGenericosImpl(super.getManager());
				tbProduto.getItems().addAll(genericos.findByNome(txPesquisa.getText()));
			}
			else if(tgPizza.isSelected()){
				pizzas = new PizzasImpl(super.getManager());
				tbPizza.getItems().addAll(pizzas.findByNome(txPesquisa.getText()));
			}
			else if(tgComplemento.isSelected()){
				complementos = new ComplementosImpl(super.getManager());
				tbComplemento.getItems().addAll(complementos.findByNome(txPesquisa.getText()));
			}
			else if(tgObservacao.isSelected()){
				observacao = new ObservacaoImpl(super.getManager());
				tbObservacao.getItems().addAll(observacao.findByNome(txPesquisa.getText()));
			}
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

	private void tabelaAtivar(){
		TableView aux = null;
		if(tgProduto.isSelected())
			aux = tbProduto;
		else if(tgPizza.isSelected())
			aux = tbPizza;
		else if(tgComplemento.isSelected())
			aux = tbComplemento;
		else aux = tbObservacao;

		final TableView auxFinal = aux;
		pnTabelas.getChildren().forEach(node->{
			if(node instanceof AnchorPane){
				((AnchorPane) node).getChildren().forEach(n->{
					if(n instanceof TableView){
						((TableView) n).getItems().clear();
						if(((TableView)n)==auxFinal) {
							node.setVisible(true);
							filtrar();
						}
						else
							node.setVisible(false);
					}
				});
			}
		});
	}
	private void tabelas(){
		tabelaProduto();
		tabelaPizza();
		tabelaComplemento();
		tabelaObservacao();
	}
	private void tabelaComplemento(){
		TableColumn<Complemento, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<Complemento, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);

		TableColumn<Complemento, BigDecimal> colunaValor = new  TableColumn<>("Valor");
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		colunaValor.setCellFactory(param -> new TableCell<Complemento,BigDecimal>(){
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
		TableColumn<Complemento, Number> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaEditar.setCellFactory(param -> new TableCell<Complemento,Number>(){
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
						long product = tbComplemento.getItems().get(getIndex()).getId().longValue();
						try {
							loadFactory();
							complementos = new ComplementosImpl(getManager());
							Complemento obs = complementos.findById(product);
							abrirCadastro(obs, COMPLEMENTO_CADASTRO);
						} catch (Exception e) {
							alert(AlertType.ERROR, "Erro","","Erro ao abrir registro Complemento>"+product,e,true);
						} finally {
							close();
						}
					});
					setGraphic(button);
				}
			}
		});
		tbComplemento.getColumns().addAll(columnId,columnNome,colunaValor,colunaEditar);
		tbComplemento.setTableMenuButtonVisible(true);
	}
	private void tabelaObservacao(){
		TableColumn<Observacao, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<Observacao, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);


		TableColumn<Observacao, Number> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaEditar.setCellFactory(param -> new TableCell<Observacao,Number>(){
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
						long product = tbObservacao.getItems().get(getIndex()).getId().longValue();
						try {
							loadFactory();
							observacao = new ObservacaoImpl(getManager());
							Observacao obs = observacao.findById(product);
							abrirCadastro(obs, OBSERVACAO_CADASTRO);
						} catch (Exception e) {
							alert(AlertType.ERROR, "Erro","","Erro ao abrir registro Observacao>"+product,e,true);
						} finally {
							close();
						}
					});
					setGraphic(button);
				}
			}
		});
		tbObservacao.getColumns().addAll(columnId,columnNome,colunaEditar);
		tbObservacao.setTableMenuButtonVisible(true);
	}
	private void tabelaPizza() {
		TableColumn<Pizza, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<Pizza, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);

		TableColumn<Pizza, Number> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaEditar.setCellFactory(param -> new TableCell<Pizza,Number>(){
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
						long product = tbPizza.getItems().get(getIndex()).getId().longValue();
						try {
							loadFactory();
							pizzas = new PizzasImpl(getManager());
							Pizza obs = pizzas.findById(product);
							abrirCadastro(obs, PRODUTO_CADASTRO);
						} catch (Exception e) {
							alert(AlertType.ERROR, "Erro","","Erro ao abrir registro Pizza>"+product,e,true);
						} finally {
							close();
						}
					});
					setGraphic(button);
				}
			}
		});

		tbPizza.getColumns().addAll(columnId,columnNome,colunaEditar);
		tbPizza.setTableMenuButtonVisible(true);
	}

	private void tabelaProduto() {
		TableColumn<ProdutoGenerico, Number> columnId = new  TableColumn<>("*");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setPrefWidth(40);

		TableColumn<ProdutoGenerico, String> columnNome = new  TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnNome.setPrefWidth(250);
		columnNome.setMaxWidth(320);
		columnNome.setMaxWidth(320);

		TableColumn<ProdutoGenerico, Number> colunaEditar = new  TableColumn<>("");
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaEditar.setCellFactory(param -> new TableCell<ProdutoGenerico,Number>(){
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
						long product = tbProduto.getItems().get(getIndex()).getId().longValue();
						try {
							loadFactory();
							genericos = new ProdutosGenericosImpl(getManager());
							ProdutoGenerico gen = genericos.findById(product);
							abrirCadastro(gen, PRODUTO_CADASTRO);
						} catch (Exception e) {
							alert(AlertType.ERROR, "Erro","","Erro ao abrir registro ProdutoGenerico>"+product,e,true);
						} finally {
							close();
						}
					});
					setGraphic(button);
				}
			}
		});
		tbProduto.getColumns().addAll(columnId,columnNome,colunaEditar);
		tbProduto.setTableMenuButtonVisible(true);
	}
}
