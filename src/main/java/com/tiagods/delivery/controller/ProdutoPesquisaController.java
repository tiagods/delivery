package com.tiagods.delivery.controller;

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
		    	loader.setController(new ProdutoGenericoCadastroController((ProdutoGenerico)object,stage));
	        else if(object instanceof Pizza)
	        	loader.setController(new PizzaCadastroController((Pizza) object,stage));
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
			alert(AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", e.getMessage());
		}
	}
	@FXML
	private void cadastrar(ActionEvent event) {
		if(tgProduto.isSelected())
			abrirCadastro(new ProdutoGenerico(),"ProdutoGenericoCadastro");
		else if(tgPizza.isSelected())
		    abrirCadastro(new Pizza(), "PizzaCadastro");
        else if(tgComplemento.isSelected())
            abrirCadastro(new Complemento(), "ComplementoCadastro");
        else if(tgObservacao.isSelected())
            abrirCadastro(new Complemento(),"ObservacaoCadastro");
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
			alert(AlertType.ERROR, "Erro", "Erro ao listar", e.getMessage());
			e.printStackTrace();
		}finally {
			super.close();
		}
	}
	@FXML
	void pesquisar(KeyEvent event) {
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
		tbComplemento.getColumns().addAll(columnId);
		tbComplemento.setTableMenuButtonVisible(true);
	}
	private void tabelaObservacao(){
		TableColumn<Observacao, Number> columnId = new  TableColumn<>("*");
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
		tbObservacao.getColumns().addAll(columnId);
		tbObservacao.setTableMenuButtonVisible(true);
	}
	private void tabelaPizza() {
		TableColumn<Pizza, Number> columnId = new  TableColumn<>("*");
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
		tbPizza.getColumns().addAll(columnId);
		tbPizza.setTableMenuButtonVisible(true);
	}

	private void tabelaProduto() {
		TableColumn<ProdutoGenerico, Number> columnId = new  TableColumn<>("*");
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
		tbProduto.getColumns().addAll(columnId);
		tbProduto.setTableMenuButtonVisible(true);
	}
}
