package com.tiagods.delivery.controller.pedido;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Pedido;
import com.tiagods.delivery.model.pagamento.CartaoBandeira;
import com.tiagods.delivery.model.pagamento.ModalidadePagamento;
import com.tiagods.delivery.model.pagamento.ValeBandeira;
import com.tiagods.delivery.model.pedido.PedidoCaixa;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.model.pedido.PedidoPagamento;
import com.tiagods.delivery.model.pedido.PedidoProduto;
import com.tiagods.delivery.repository.helper.PedidosCaixaImpl;
import com.tiagods.delivery.repository.helper.PedidosDeliveryImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class PedidoPagamentoController extends UtilsController implements Initializable {
    @FXML
    private FlowPane pnMeio;
    @FXML
    private FlowPane pnBandeiras;
    @FXML
    private HBox pnPagamento;
    @FXML
    private JFXTextField txValor;
    @FXML
    private TableView<PedidoPagamento> tbPrincipal;
    @FXML
    private Label txTotal;
    @FXML
    private Label txAPagar;

    private Locale locale = new Locale("pt", "BR");
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    private Stage stage;
    private Pedido pedido;
    private PedidosDeliveryImpl pedidosDelivery;
    private PedidosCaixaImpl pedidosCaixa;
    public PedidoPagamentoController(Pedido pedido, Stage stage){
        this.stage=stage;
        this.pedido=pedido;
    }
    private double aPagar(){
        try {
            BigDecimal pag = tbPrincipal.getItems().stream()
                    .map(item -> item.getValor())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            double total = currencyFormatter.parse(txTotal.getText()).doubleValue();
            double apagar = total - pag.doubleValue();
            txAPagar.setText(currencyFormatter.format(apagar));
            return new BigDecimal(String.format("%.2f", apagar).replace(",", ".")).doubleValue();
        }catch (ParseException e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,"Erro ao receber valor total",e,true);
            return 0;
        }
    }
    private void combos(){
        tabela();
        ToggleGroup group = new ToggleGroup();
        for(ModalidadePagamento mp : ModalidadePagamento.values()){
            JFXRadioButton radioButton = new JFXRadioButton();
            radioButton.setText(mp.getDescricao());
            radioButton.setPadding(new Insets(0,0,20,0));
            group.getToggles().add(radioButton);
            radioButton.selectedProperty().addListener(new AlterarMeioPagamento(radioButton,mp));
            pnMeio.getChildren().add(radioButton);
            if(mp.equals(ModalidadePagamento.DINHEIRO)){
                aPagar();
                radioButton.setSelected(true);
                pnPagamento.setVisible(true);
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txTotal.setText(currencyFormatter.format(pedido.getTotal()));
        tbPrincipal.getItems().addAll(pedido.getPagamentos());
        aPagar();
        combos();
    }
    @FXML
    public void pagar(ActionEvent event) {
        PedidoPagamento pedidoPagamento = new PedidoPagamento();
        ModalidadePagamento modalidadePagamento = pegarModalidade(pnMeio.getChildren());
        if(modalidadePagamento==null) {
            super.alert(Alert.AlertType.ERROR,"Erro", null, "Erro ao Receber Pagamento",
                    new IllegalArgumentException(),true);

            return;
        }
        pedidoPagamento.setModalidadePagamento(modalidadePagamento);
        if(modalidadePagamento.equals(ModalidadePagamento.VALE)){
            ValeBandeira vale = pegarVale(pnBandeiras.getChildren());
            if(vale==null) {
                super.alert(Alert.AlertType.ERROR, "Erro", null, "Erro ao Receber Pagamento",
                        new IllegalArgumentException(), true);
                return;
            }
            pedidoPagamento.setValeBandeira(vale);
        }
        else if(modalidadePagamento.equals(ModalidadePagamento.CREDITO) ||
                modalidadePagamento.equals(ModalidadePagamento.DEBITO) ){
            CartaoBandeira cartao = pegarCartao(pnBandeiras.getChildren());
            if(cartao==null) {
                super.alert(Alert.AlertType.ERROR, "Erro", null, "Erro ao Receber Pagamento",
                        new IllegalArgumentException(), true);
                return;
            }
            pedidoPagamento.setCartaoBandeira(cartao);
        }
        try {
            double valor = Double.parseDouble(txValor.getText().trim().replace(",","."));
            pedidoPagamento.setValor(new BigDecimal(String.format("%.2f",valor).replace(",",".")));
            tbPrincipal.getItems().add(pedidoPagamento);
            salvar();
        } catch (NumberFormatException e) {
            super.alert(Alert.AlertType.ERROR,"Erro",null, "Valor de pagamento incorreto",null,false);
        }
    }

    private ValeBandeira pegarVale(ObservableList<Node> nodes){
        for(Node bandeira : nodes) {
            if(bandeira instanceof JFXRadioButton && ((JFXRadioButton) bandeira).isSelected()){
                for(ValeBandeira vale : ValeBandeira.values())
                    if(vale.getDescricao().equals(((JFXRadioButton) bandeira).getText())) return vale;
            }
        }
        return null;
    }
    private CartaoBandeira pegarCartao(ObservableList<Node> nodes){
        for(Node bandeira : nodes) {
            if(bandeira instanceof JFXRadioButton && ((JFXRadioButton) bandeira).isSelected()){
                for(CartaoBandeira card : CartaoBandeira.values())
                    if(card.getDescricao().equals(((JFXRadioButton) bandeira).getText())) return card;
            }
        }
        return null;
    }
    private ModalidadePagamento pegarModalidade(ObservableList<Node> nodes){
        for(Node node : pnMeio.getChildren()){
            if(node instanceof JFXRadioButton && ((JFXRadioButton) node).isSelected()){
                for(ModalidadePagamento m : ModalidadePagamento.values())
                    if(m.getDescricao().equals(((JFXRadioButton) node).getText())) return m;
            }
        }
        return null;
    }
    @FXML
    public void sair(ActionEvent event) {
        stage.close();
    }
    @FXML
    public void salvar(ActionEvent event) {
        salvar();
    }
    void salvar(){
        if(!validarValor()) return;
        try{
            super.loadFactory();
            Set<PedidoPagamento> pagamentoSet = new HashSet<>();
            pagamentoSet.addAll(tbPrincipal.getItems());

            double devendo = aPagar();
            pedido.setPago(devendo==0.00);
            pedido.setValorPago(devendo==0.00?pedido.getTotal():
                    (new BigDecimal(
                            pedido.getTotal().doubleValue()-devendo)
                    ));
            if(pedido instanceof PedidoDelivery){
                pedidosDelivery = new PedidosDeliveryImpl(super.getManager());
                pedido.setPagamentos(pagamentoSet);
                pedido = pedidosDelivery.save((PedidoDelivery) pedido);
            }
            else if(pedido instanceof PedidoCaixa){
                pedido.setPagamentos(pagamentoSet);
                pedido = pedidosCaixa.save((PedidoCaixa) pedido);
            }
            txValor.setText(String.valueOf(devendo).replace(".",","));
            tbPrincipal.getItems().clear();
            tbPrincipal.getItems().addAll(pedido.getPagamentos());

        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR, "Erro",null,"Erro ao salvar os pagamentos", e,true);
        }finally {
            super.close();
        }
    }
    private void tabela(){
        TableColumn<PedidoPagamento, BigDecimal> colunaValor = new  TableColumn<>("Valor");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setCellFactory(param -> new TableCell<PedidoPagamento,BigDecimal>(){
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if(item==null){
                    setStyle("");
                    setText("");
                }
                else{
                    setText(currencyFormatter.format(item.doubleValue()));
                }
            }
        });
        TableColumn<PedidoPagamento, ModalidadePagamento> colunaMeio = new  TableColumn<>("Tipo");
        colunaMeio.setCellValueFactory(new PropertyValueFactory<>("modalidadePagamento"));
        colunaMeio.setCellFactory(param -> new TableCell<PedidoPagamento,ModalidadePagamento>(){
            @Override
            protected void updateItem(ModalidadePagamento item, boolean empty) {
                super.updateItem(item, empty);
                if(item==null){
                    setStyle("");
                    setText("");
                }
                else{
                    setText(item.getDescricao());
                }
            }
        });
        TableColumn<PedidoPagamento, Number> colunaBandeira = new  TableColumn<>("Bandeira");
        colunaBandeira.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaBandeira.setCellFactory(param -> new TableCell<PedidoPagamento,Number>(){
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if(item==null){
                    setStyle("");
                    setText("");
                }
                else{
                    PedidoPagamento pagamento = tbPrincipal.getItems().get(getIndex());
                    if(pagamento.getModalidadePagamento().equals(ModalidadePagamento.VALE)){
                        setText(pagamento.getValeBandeira().getDescricao());
                    }
                    else if(pagamento.getModalidadePagamento().equals(ModalidadePagamento.CREDITO)||
                            pagamento.getModalidadePagamento().equals(ModalidadePagamento.DEBITO)){
                        setText((pagamento.getCartaoBandeira().getDescricao()));
                    }
                }
            }
        });
        TableColumn<PedidoPagamento, Number> colunaExcluir = new  TableColumn<>("");
        colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaExcluir.setCellFactory(param -> new TableCell<PedidoPagamento,Number>(){
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
                        tbPrincipal.getItems().remove(getIndex());
                        salvar();
                    });
                    setGraphic(button);
                }
            }
        });
        tbPrincipal.getColumns().addAll(colunaValor,colunaMeio,colunaBandeira,colunaExcluir);
    }

    private boolean validarValor() {
        double newValor = 0;
        try {
            double valor = Double.parseDouble(txValor.getText().replace(",", "."));
            newValor = new BigDecimal(String.format("%.2f", valor).replace(",", ".")).doubleValue();
            if (newValor > currencyFormatter.parse(txAPagar.getText()).doubleValue()) {
                super.alert(Alert.AlertType.ERROR, "Erro", null, "Valor informado superior ao valor devido", null, false);
                return false;
            }
            else return true;
        } catch (NumberFormatException e) {
            super.alert(Alert.AlertType.ERROR,"Erro",null,"Verifique o valor informado",null,false);
            return false;
        } catch (ParseException e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,"Erro ao receber valor a pagar",e,true);
            return false;
        }
    }

    public class AlterarMeioPagamento implements ChangeListener<Boolean> {
        private JFXRadioButton btn;
        private ModalidadePagamento modalidade;
        public AlterarMeioPagamento(JFXRadioButton button, ModalidadePagamento modalidadePagamento){
            this.btn=button;
            this.modalidade=modalidadePagamento;
        }
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue){
                txValor.setText(String.valueOf(aPagar()).replace(".",","));
                pnPagamento.setVisible(false);
                ToggleGroup group = new ToggleGroup();
                pnBandeiras.getChildren().clear();
                if(modalidade.equals(ModalidadePagamento.CHEQUE)
                        || modalidade.equals(ModalidadePagamento.DINHEIRO)){
                    pnPagamento.setVisible(true);
                }
                else if(modalidade.equals(ModalidadePagamento.VALE)){
                    for(ValeBandeira vb : ValeBandeira.values()){
                        JFXRadioButton radioButton = new JFXRadioButton();
                        radioButton.setPadding(new Insets(0,0,20,0));
                        radioButton.setText(vb.getDescricao());
                        radioButton.selectedProperty().addListener(new EscolherCartao());
                        group.getToggles().add(radioButton);
                        pnBandeiras.getChildren().add(radioButton);
                    }
                }
                else if(modalidade.equals(ModalidadePagamento.CREDITO) ||
                        modalidade.equals(ModalidadePagamento.DEBITO)){
                    for(CartaoBandeira cb : CartaoBandeira.values()){
                        JFXRadioButton radioButton = new JFXRadioButton();
                        radioButton.setPadding(new Insets(0,0,20,0));
                        radioButton.setText(cb.getDescricao());
                        radioButton.selectedProperty().addListener(new EscolherCartao());
                        group.getToggles().add(radioButton);
                        pnBandeiras.getChildren().add(radioButton);
                    }
                }
            }
        }
    }
    public class EscolherCartao implements ChangeListener<Boolean>{
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            for(Node node : pnBandeiras.getChildren()){
                if(node instanceof JFXRadioButton && ((JFXRadioButton) node).isSelected()){
                    pnPagamento.setVisible(true);
                }
            }
        }
    }
}
