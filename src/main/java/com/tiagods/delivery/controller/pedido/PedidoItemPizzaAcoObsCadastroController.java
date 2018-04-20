package com.tiagods.delivery.controller.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoProdutoItemAdicional;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.pizza.PizzaTipo;
import com.tiagods.delivery.repository.helper.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private PedidosProdutosItensAddImpl itemsAdd;
    private PizzasImpl pizzas;

    private Stage stage;
    private PedidoProdutoItem pedidoProduto;

    List<Complemento> complementoList = new ArrayList<>();
    List<Observacao> observacaoList = new ArrayList<>();

    public PedidoItemPizzaAcoObsCadastroController(PedidoProdutoItem pedidoProduto,Stage stage){
        this.pedidoProduto=pedidoProduto;
        this.stage=stage;
    }

    void abrirCadastroItem(PedidoProdutoItemAdicional item){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = loader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPedidoItem.fxml"));
            loader.setController(new PedidoItemAcoObsCadastroController(item,stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding(event -> {
                try{
                    loadFactory();
                    items = new PedidosProdutosItensImpl(getManager());
                    pedidoProduto = items.findById(pedidoProduto.getId());
                    salvar();
                }catch (Exception e){
                    alert(Alert.AlertType.ERROR, "Erro", "Erro ao preencher formulario", "Falha preencher o Delivery",e,true);
                }finally {
                    if(getManager().isOpen())
                        super.close();
                }
            });
            tbPizzas.refresh();
        }catch(IOException e) {
            alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", "Falha ao abrir cadastro do Delivery",e,true);
        }
    }
    @FXML
    void adicionarPizza(ActionEvent event) {
        if(cbPizzas.getValue()!=null || receberSelecionado()!=null){
            PedidoProdutoItemAdicional item = new PedidoProdutoItemAdicional();
            item.setCriadoEm(Calendar.getInstance());
            item.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
            item.setPedido(this.pedidoProduto.getPedido());
            item.setPizzaVendida(receberSelecionado());
            item.setQuantidade(pedidoProduto.getQuantidade());

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
            item.setNome(pizza.getNome());
            tbPizzas.getItems().add(item);

            /*
            double adicionais = tbPizzas.getItems().stream().map(m->
                    m.getComplementos()
                            .stream()
                            .map(Complemento::getValor)
                            .mapToDouble(BigDecimal::doubleValue)
                            .sum())
                    .mapToDouble(Double::doubleValue).sum();
            */
            //item.setValorExtra(new BigDecimal(adicionais));
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
        if(pizzaVendida!=null && pizzaVendida.equals(tipo))
            radio.setSelected(true);
        group.getToggles().add(radio);
        radio.selectedProperty().addListener(new CliqueRadio());
        pnPizzas.getChildren().add(radio);
    }
    void combos(){

    }
    private void inicializarComboBox() throws Exception{
        loadFactory(super.getManager());
        pizzas = new PizzasImpl(super.getManager());
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
            tabelaProduto();
            complementos = new ComplementosImpl(super.getManager());
            observacao = new ObservacaoImpl(super.getManager());

            Set<Complemento> complementoSet= pedidoProduto.getComplementos();
            Set<Observacao> observacaosSet = pedidoProduto.getObservacoes();

            ProdutoCategoria categoria = pedidoProduto.getProduto().getCategoria();
            complementoList = complementos.findByCategoria(categoria);
            observacaoList = observacao.findByCategoria(categoria);

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
            tbPizzas.getItems().addAll(pedidoProduto.getProdutoItemAdicional());
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
                        PedidoProdutoItemAdicional product = tbPizzas.getItems().get(getIndex());
                        abrirCadastroItem(product);
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
                        PedidoProdutoItemAdicional itemAdicional = tbPizzas.getItems().get(getIndex());
                        try{
                            loadFactory();
                            itemsAdd = new PedidosProdutosItensAddImpl(getManager());
                            itemAdicional = itemsAdd.findById(itemAdicional.getId());
                            itemsAdd.remove(itemAdicional);
                            tbPizzas.getItems().remove(getIndex());
                        }catch (Exception e){
                            e.printStackTrace();
                            alert(Alert.AlertType.ERROR,"Erro",null,"Erro ao excluir",e,true);
                        }finally {
                            close();
                        }
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
                long id = Long.parseLong(((JFXCheckBox)c).getId());
                Optional<Complemento> com = complementoList.stream().filter(f->f.getId().longValue()==id).findFirst();
                comList.add(com.get());
            }
        });
        Set<Observacao> obsList = new HashSet<>();
        lvObservacao.getItems().forEach(c->{
            if(c instanceof JFXCheckBox && ((JFXCheckBox)c).isSelected()){
                long id = Long.parseLong(((JFXCheckBox)c).getId());
                Optional<Observacao> obs = observacaoList.stream().filter(f->f.getId().longValue()==id).findFirst();
                obsList.add(obs.get());
            }
        });
        Set<PedidoProdutoItemAdicional> itemAdicionalSet = new HashSet<>();

        String divisor = tbPizzas.getItems().size()==0?"":" 1/"+(tbPizzas.getItems().size()+1)+" ";
        //renomeando item_add
            tbPizzas.getItems().forEach(t->{
                String complementoName = t.getComplementos().stream()
                        .map(Complemento::getNome)
                        .collect(Collectors.joining(" +"));
                String observacaoName = t.getObservacoes().stream()
                        .map(Observacao::getNome)
                        .collect(Collectors.joining(" +"));
                t.setNome(" +Pizza "+t.getProduto().getNome()+divisor
                        +(complementoName.trim().length()==0?"":(" +"+complementoName))
                        +(observacaoName.trim().length()==0?"":(" +"+observacaoName))
                );
                double adicionais = t.getComplementos().stream()
                        .map(Complemento::getValor)
                        .mapToDouble(BigDecimal::doubleValue).sum();
                t.setValorExtra(new BigDecimal(adicionais));
                itemAdicionalSet.add(t);
            });
        String complementoName = comList.stream()
                .map(Complemento::getNome)
                .collect(Collectors.joining(" +"));
        String observacaoName = obsList.stream()
                .map(Observacao::getNome)
                .collect(Collectors.joining(" +"));
        String nomeAdicionais= itemAdicionalSet.stream()
                .map(PedidoProdutoItemAdicional::getNome)
                .collect(Collectors.joining(" Pizza"));
        pedidoProduto.setNome("Pizza "+pedidoProduto.getProduto().getNome()+" "
                +receberSelecionado().getDescricao()
                +divisor
                +(complementoName.trim().length()==0?"":(" +"+complementoName))
                +(observacaoName.trim().length()==0?"":(" +"+observacaoName))
                +nomeAdicionais);

        double adicionais = comList.stream()
                .map(Complemento::getValor)
                .mapToDouble(BigDecimal::doubleValue).sum();
        Optional<PedidoProdutoItemAdicional> maior = itemAdicionalSet
                .stream()
                .max(Comparator.comparing(PedidoProdutoItemAdicional::getValor));

        try{
            loadFactory();
            if(pedidoProduto instanceof PedidoProdutoItem) {
                items = new PedidosProdutosItensImpl(getManager());
                pedidoProduto.setComplementos(comList);
                pedidoProduto.setObservacoes(obsList);

                PizzaTipo tipo = receberSelecionado();
                pedidoProduto.setPizzaVendida(tipo);

                Pizza pizza = (Pizza)pedidoProduto.getProduto();

                if(tipo.equals(PizzaTipo.FATIA)){
                    pedidoProduto.setValor(pizza.getFatia().getVendaFatia());
                }
                else if(tipo.equals(PizzaTipo.PEQUENA)){
                    pedidoProduto.setValor(pizza.getPequena().getVendaPequeno());
                }
                else if(tipo.equals(PizzaTipo.MEDIA)){
                    pedidoProduto.setValor(pizza.getMedia().getVendaMedia());
                }
                else if(tipo.equals(PizzaTipo.GRANDE)){
                    pedidoProduto.setValor(pizza.getGrande().getVendaGrande());
                }
                if(maior.isPresent()){//verificando qual o preço da pizza mais cara da tabela e incluindo
                    if(pedidoProduto.getValor().compareTo(maior.get().getValor())==-1){
                        pedidoProduto.setValor(maior.get().getValor());
                    }
                }
                double taxas = itemAdicionalSet.stream()
                        .map(PedidoProdutoItemAdicional::getValorExtra)
                        .mapToDouble(BigDecimal::doubleValue).sum();
                pedidoProduto.setValorExtra(new BigDecimal(adicionais+taxas));

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
                salvar();
            }catch (Exception e){
                alert(Alert.AlertType.ERROR,"Erro",null,
                        "Erro ao listas pizzas",e,true);
            }finally {
                close();
            }
        }
    }

}
