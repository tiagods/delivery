package com.tiagods.delivery.controller.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.model.pedido.PedidoProduto;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoProdutoItemAdicional;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.model.produto.pizza.PizzaTipo;
import com.tiagods.delivery.repository.helper.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class PedidoItemPizzaAcoObsCadastroController extends UtilsController implements Initializable{
    @FXML
    private Label lbPizzaNome;
    @FXML
    private JFXComboBox<Pizza> cbPizzas;
    @FXML
    private HBox pnPizzas;
    @FXML
    private ListView<Object> lvComplemento;
    @FXML
    private ListView<Object> lvObservacao;
    @FXML
    private TableView<PedidoProdutoItemAdicional> tbPizzas;

    ToggleGroup group = new ToggleGroup();

    Locale locale = new Locale("pt", "BR");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private ComplementosImpl complementos;
    private ObservacaoImpl observacao;
    private PedidosProdutosItensImpl items;
    private PizzasImpl pizzas;

    private Stage stage;
    private PedidoProdutoItem pedidoProduto;

    public PedidoItemPizzaAcoObsCadastroController(PedidoProdutoItem pedidoProduto,Stage stage){
        this.pedidoProduto=pedidoProduto;
        this.stage=stage;
    }

    @FXML
    void adicionarPizza(ActionEvent event) {
        if(cbPizzas.getValue()!=null || receberSelecionado()!=null){
            PedidoProdutoItemAdicional item = new PedidoProdutoItemAdicional();
            item.setCriadoEm(Calendar.getInstance());
            item.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
            item.setPedido(this.pedidoProduto.getPedido());
            Pizza pizza = cbPizzas.getValue();
            item.setProduto(pizza);

            PizzaTipo selecionado = receberSelecionado();
            BigDecimal valor = new BigDecimal(0.00);
            if(selecionado.equals(PizzaTipo.FATIA))
                valor = pizza.getFatia().getVendaFatia();
            else if(selecionado.equals(PizzaTipo.PEQUENA))
                valor = pizza.getPequena().getVendaPequeno();
            else if(selecionado.equals(PizzaTipo.MEDIA))
                valor = pizza.getMedia().getVendaMedia();
            else if(selecionado.equals(PizzaTipo.GRANDE))
                valor = pizza.getGrande().getVendaGrande();
            item.setValor(valor);

            tbPizzas.getItems().add(item);
            salvar();
        }
    }
    private void acionarPainelPizzas(PizzaTipo pizzaVendida,Pizza pizza){
        adicionarPizzasNoPainel(pizzaVendida,pizza.isFatiaHabilitada(),pizza.getFatia()==null ? new BigDecimal(0.00):pizza.getFatia().getVendaFatia(),PizzaTipo.FATIA);
        adicionarPizzasNoPainel(pizzaVendida,pizza.isPequenaHabilitada(),pizza.getPequena()==null ? new BigDecimal(0.00):pizza.getPequena().getVendaPequeno(),PizzaTipo.PEQUENA);
        adicionarPizzasNoPainel(pizzaVendida,pizza.isMediaHabilitada(),pizza.getMedia()==null ? new BigDecimal(0.00):pizza.getMedia().getVendaMedia(),PizzaTipo.MEDIA);
        adicionarPizzasNoPainel(pizzaVendida,pizza.isGrandeHabilitada(),pizza.getGrande()==null ? new BigDecimal(0.00):pizza.getGrande().getVendaGrande(),PizzaTipo.GRANDE);

    }

    private void adicionarPizzasNoPainel(PizzaTipo pizzaVendida, boolean habilitado, BigDecimal preco, PizzaTipo tipo){
        JFXRadioButton radio = new JFXRadioButton();
        radio.setId(""+tipo.getCodigo());
        radio.setText(tipo+" ("+currencyFormatter.format(preco.doubleValue())+")");
        radio.setDisable(!habilitado);
        if(pizzaVendida!=null)
            radio.setSelected(true);
        group.getToggles().add(radio);
        radio.selectedProperty().addListener(new CliqueRadio());
        pnPizzas.getChildren().add(radio);
    }
    void combos(){

    }
    private void inicializarComboBox() throws Exception{
        pizzas = new PizzasImpl(getManager());
        cbPizzas.getItems().clear();
        List<Pizza> lista = pizzas.filtrarPorTamanho(receberSelecionado());
        cbPizzas.setConverter(new StringConverter<Pizza>() {
            @Override
            public String toString(Pizza object) {
                PizzaTipo selecionado = receberSelecionado();
                String valor = "";
                if(selecionado.equals(PizzaTipo.FATIA))
                    valor = currencyFormatter.format(object.getFatia().getVendaFatia().doubleValue());
                else if(selecionado.equals(PizzaTipo.PEQUENA))
                    valor = currencyFormatter.format(object.getPequena().getVendaPequeno().doubleValue());
                else if(selecionado.equals(PizzaTipo.MEDIA))
                    valor = currencyFormatter.format(object.getMedia().getVendaMedia().doubleValue());
                else if(selecionado.equals(PizzaTipo.GRANDE))
                    valor = currencyFormatter.format(object.getGrande().getVendaGrande().doubleValue());
                return object.getNome()+"("+valor+")";
            }
            @Override
            public Pizza fromString(String string) {
                return null;
            }
        });
        cbPizzas.getItems().addAll(lista);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            super.loadFactory();
            complementos = new ComplementosImpl(super.getManager());
            observacao = new ObservacaoImpl(super.getManager());

            Set<Complemento> complementoSet= pedidoProduto.getComplementos();
            Set<Observacao> observacaosSet = pedidoProduto.getObservacoes();

            ProdutoCategoria categoria = pedidoProduto.getProduto().getCategoria();
            List<Complemento> complementoList = complementos.findByCategoria(categoria);
            List<Observacao> observacaoList = observacao.findByCategoria(categoria);

            for(Complemento c : complementoList){
                JFXCheckBox ck = new JFXCheckBox(c.getNome()+" - ("+currencyFormatter.format(c.getValor())+")");
                ck.setId(""+c.getId());
                if(complementoSet.contains(c)) ck.setSelected(true);
                ck.selectedProperty().addListener(new CliqueGeral());
                lvComplemento.getItems().add(ck);
            };
            for(Observacao c : observacaoList){
                JFXCheckBox ck = new JFXCheckBox(c.getNome());
                ck.setId(""+c.getId());
                if(observacaosSet.contains(c)) ck.setSelected(true);
                ck.selectedProperty().addListener(new CliqueGeral());
                lvObservacao.getItems().add(ck);
            };
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            if(lvComplemento.getItems().size()==0){
                label.setText("Nenhum complemento");
                lvComplemento.getItems().add(label);
            }
            if(lvObservacao.getItems().size()==0){
                label.setText("Nenhuma observação");
                lvObservacao.getItems().add(label);
            }
            Pizza pizza = (Pizza)pedidoProduto.getProduto();

            lbPizzaNome.setText("Pizza "+pizza.getNome());
            if(pedidoProduto.getId()!=null){
                acionarPainelPizzas(pedidoProduto.getPizzaVendida(),pizza);
                inicializarComboBox();
            }
            else{
                acionarPainelPizzas(null,pizza);
            }
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,
                    "Erro ao preencher listas Complemento e Observacao",e,true);
        }finally {
            super.close();
        }

    }
    private void tabelaProduto() {
        TableColumn<PedidoProdutoItemAdicional, String> columnNome = new  TableColumn<>("Nome");
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnNome.setPrefWidth(100);

        TableColumn<PedidoProdutoItemAdicional, BigDecimal> colunaValor = new  TableColumn<>("Valor");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setCellFactory(param -> new TableCell<PedidoProdutoItemAdicional,BigDecimal>(){
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
        TableColumn<PedidoProdutoItemAdicional, Number> colunaEditar = new  TableColumn<>("");
        colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaEditar.setCellFactory(param -> new TableCell<PedidoProdutoItemAdicional,Number>(){
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
                        long product = tbPizzas.getItems().get(getIndex()).getId().longValue();
                        try {
                            loadFactory();

                        } catch (Exception e) {
                            alert(Alert.AlertType.ERROR, "Erro","","Erro ao abrir registro ProdutoGenerico>"+product,e,true);
                        } finally {
                            close();
                        }
                    });
                    setGraphic(button);
                }
            }
        });
        TableColumn<PedidoProdutoItemAdicional, Number> colunaExcluir = new  TableColumn<>("");
        colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaExcluir.setCellFactory(param -> new TableCell<PedidoProdutoItemAdicional,Number>(){
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
                        tbPizzas.getItems().remove(getIndex());
                        salvar();
                    });
                    setGraphic(button);
                }
            }
        });
        tbPizzas.getColumns().addAll(columnNome,colunaValor,colunaEditar,colunaExcluir);
        tbPizzas.setTableMenuButtonVisible(true);
    }
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    PizzaTipo receberSelecionado(){
        for (Node node : pnPizzas.getChildren()) {
            if (node instanceof JFXRadioButton && ((JFXRadioButton) node).isSelected()) {
                for(PizzaTipo t : PizzaTipo.values()){
                    if(String.valueOf(t.getCodigo()).equals(node.getId())) return t;
                }
            }
        }
        return null;
    }
    void salvar(){
        if(receberSelecionado()==null) {
            alert(Alert.AlertType.ERROR, "Erro", "Produto não informado",
                    "Informe o tamanho da pizza antes", null, false);
            return;
        }
        Set<Complemento> comList = new HashSet<>();
        lvComplemento.getItems().forEach(c->{
            if(c instanceof JFXCheckBox && ((JFXCheckBox)c).isSelected()){
                Complemento com = new Complemento();
                com.setId(Long.parseLong(((JFXCheckBox)c).getId()));
                comList.add(com);
            }
        });
        Set<Observacao> obsList = new HashSet<>();
        lvObservacao.getItems().forEach(c->{
            if(c instanceof JFXCheckBox && ((JFXCheckBox)c).isSelected()){
                Observacao obs = new Observacao();
                obs.setId(Long.parseLong(((JFXCheckBox)c).getId()));
                obsList.add(obs);
            }
        });
        try{
            loadFactory();
            if(pedidoProduto instanceof PedidoProdutoItem) {
                items = new PedidosProdutosItensImpl(getManager());
                pedidoProduto.setComplementos(comList);
                pedidoProduto.setObservacoes(obsList);

                Set<PedidoProdutoItemAdicional> itemAdicionalSet = new HashSet<>();
                itemAdicionalSet.addAll(tbPizzas.getItems());
                pedidoProduto.setProdutoItemAdicional(itemAdicionalSet);
                pedidoProduto = items.save(pedidoProduto);

                tbPizzas.getItems().clear();
                tbPizzas.getItems().addAll(pedidoProduto.getProdutoItemAdicional());
            }
        }catch (Exception e){
            alert(Alert.AlertType.ERROR,"Erro",null,
                    "Erro ao salvar",e,true);
        }finally {
            close();
        }
    }



    private class CliqueGeral implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            salvar();
        }
    }
    private class CliqueRadio implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            try {
                loadFactory();
                inicializarComboBox();
            }catch (Exception e){
                alert(Alert.AlertType.ERROR,"Erro",null,
                        "Erro ao listas pizzas",e,true);
            }finally {
                close();
            }
        }
    }

}
