package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.model.Cidade;
import com.tiagods.delivery.model.Endereco;
import com.tiagods.delivery.model.Entregador;
import com.tiagods.delivery.model.Estado;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoStatus;
import com.tiagods.delivery.model.pedido.PedidoTaxa;
import com.tiagods.delivery.repository.helper.CidadesImpl;
import com.tiagods.delivery.repository.helper.EntregadoresImpl;
import com.tiagods.delivery.repository.helper.PedidosDeliveryImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;
import org.fxutils.maskedtextfield.MaskedTextField;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class PedidoDeliveryCadastroController extends UtilsController implements Initializable{
    @FXML
    private Label txCodigo;

    @FXML
    private ImageView imTempo;

    @FXML
    private Label txTemporizador;

    @FXML
    private MaskTextField txTelefoneBusca;

    @FXML
    private JFXTextField txContato;

    @FXML
    private JFXTextField txTelefone;

    @FXML
    private JFXTextField txCelular;

    @FXML
    private MaskedTextField txCEP;

    @FXML
    private JFXTextField txLogradouro;

    @FXML
    private JFXTextField txNumero;

    @FXML
    private JFXTextField txBairro;

    @FXML
    private JFXTextField txComplemento;

    @FXML
    private JFXComboBox<Cidade> cbCidade;

    @FXML
    private JFXComboBox<Estado> cbEstado;

    @FXML
    private TableView<PedidoProdutoItem> tbPrincipal;

    @FXML
    private JFXComboBox<Entregador> cbEntregador;

    @FXML
    private JFXToggleButton tgIniciado;

    @FXML
    private JFXToggleButton tgEspera;

    @FXML
    private JFXToggleButton tgAndamento;

    @FXML
    private JFXToggleButton tgEntregue;

    @FXML
    private Label txSubTotal;

    @FXML
    private Label txDesconto;

    @FXML
    private Label txServico;

    @FXML
    private JFXComboBox<PedidoTaxa> cbTaxa;

    @FXML
    private Label txTaxa;

    @FXML
    private Label txTotal;

    private PedidoDelivery delivery;
    private PedidosDeliveryImpl pedidos;
    private CidadesImpl cidades;
    private EntregadoresImpl entregadores;
    private Stage stage;
    Locale locale = new Locale("pt", "BR");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);


    public PedidoDeliveryCadastroController(PedidoDelivery pedidoDelivery, Stage stage) {
        this.delivery= pedidoDelivery;
        this.stage = stage;
    }
    @FXML
    void adicionarEntregador(ActionEvent event) {

    }

    @FXML
    void adicionarProduto(ActionEvent event) {

    }

    @FXML
    void buscarCep(ActionEvent event) {
        try{
            super.loadFactory();
            EnderecoUtil util = EnderecoUtil.getInstance();
            if(txCEP.getPlainText().trim().length()==8) {
                Endereco endereco = util.pegarCEP(txCEP.getPlainText());
                if(endereco!=null){
                    txLogradouro.setText(endereco.getLogradouro());
                    txNumero.setText("");
                    txComplemento.setText(endereco.getComplemento());
                    txBairro.setText(endereco.getBairro());
                    cidades = new CidadesImpl(super.getManager());
                    cbCidade.getItems().clear();
                    cbCidade.getItems().addAll(cidades.findByEstado(endereco.getUf()));
                    Cidade cidade = cidades.findByNome(endereco.getLocalidade());
                    cbCidade.setValue(cidade);
                    cbEstado.setValue(endereco.getUf());
                }
                else
                    super.alert(Alert.AlertType.WARNING,"CEP Invalido",null,
                            "Verifique se o cep informado é valido ou se existe uma conexão com a internet",null,false);
            }
            else{
                super.alert(Alert.AlertType.WARNING,"CEP Invalido",null,"Verifique o cep informado",null,false);
            }
        }catch(Exception e){
            super.alert(Alert.AlertType.ERROR,"Falha na conexão com o banco de dados",null,
                    "Houve uma falha na conexão com o banco de dados",e,true);
        }finally {
            super.close();
        }
    }

    @FXML
    void buscarCliente(ActionEvent event) {

    }

    @FXML
    void buscarClienteTelefone(ActionEvent event) {

    }
    void calculateTime(Calendar calendar){

    }

    void combos(){
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(tgIniciado,tgEspera,tgAndamento,tgEntregue);
        tgIniciado.setSelected(true);

        cidades = new CidadesImpl(getManager());
        Cidade cidade = cidades.findByNome("São Paulo");
        cbCidade.getItems().setAll(cidades.findByEstado(Estado.SP));
        cbCidade.setValue(cidade);
        cbEstado.getItems().addAll(Estado.values());
        cbEstado.setValue(Estado.SP);
        cbEstado.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    loadFactory();
                    cidades = new CidadesImpl(getManager());
                    cbCidade.getItems().clear();
                    List<Cidade> listCidades = cidades.findByEstado(newValue);
                    cbCidade.getItems().addAll(listCidades);
                    cbCidade.getSelectionModel().selectFirst();
                } catch (Exception e) {
                } finally {
                    close();
                }
            }
        });
        new ComboBoxAutoCompleteUtil<>(cbCidade);

        entregadores = new EntregadoresImpl(super.getManager());
        cbEntregador.getItems().addAll(entregadores.filtrarAtivos());
        cbEntregador.getSelectionModel().selectFirst();

        new ComboBoxAutoCompleteUtil<>(cbEntregador);
    }
    @FXML
    void desconto(ActionEvent event) {


    }

    @FXML
    void excluir(ActionEvent event) {
        if(txCodigo.getText().equals("")) return;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exclusão...");
        alert.setHeaderText(null);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza disso?");
        Optional<ButtonType> optional = alert.showAndWait();

        if (optional.get() == ButtonType.OK) {
            try {
                super.loadFactory();
                pedidos = new PedidosDeliveryImpl(super.getManager());
                PedidoDelivery u = pedidos.findById(delivery.getId().longValue());
                pedidos.remove(u);
                alert(Alert.AlertType.INFORMATION, "Sucesso", null, "Removido com sucesso!",null, false);
                stage.close();
            } catch (Exception e) {
                super.alert(Alert.AlertType.ERROR,"Erro ao excluir",null,
                        "Falha ao tentar excluir o registro do Delivery",e,true);
            } finally {
                close();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabela();
        try{
            super.loadFactory();
            combos();
            pedidos = new PedidosDeliveryImpl(super.getManager());
            if(delivery!=null){
                preencherFormulario(this.delivery);
            }
            else{
//                delivery = new PedidoDelivery();
//                delivery = pedidos.save(delivery);
//                preencherFormulario(delivery);
            }
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro", null,"Erro ao criar Pedido Delivery",e, true);
        }finally {
            super.close();
        }


    }

    @FXML
    void pagamento(ActionEvent event) {

    }
    void preencherFormulario(PedidoDelivery delivery){
        if(delivery.getStatus()!=PedidoStatus.ENTREGUE)
            calculateTime(delivery.getCriadoEm());
        txCodigo.setText(String.valueOf(delivery.getId()));
        //endereco

        tbPrincipal.getItems().clear();
        tbPrincipal.getItems().addAll(delivery.getProdutos());

        cbEntregador.setValue(delivery.getEntregador());
        if(delivery.getStatus()==PedidoStatus.INICIADO)
            tgIniciado.setSelected(true);
        else if(delivery.getStatus()==PedidoStatus.AGUARDANDO)
            tgEspera.setSelected(true);
        else if(delivery.getStatus()==PedidoStatus.ANDAMENTO)
            tgAndamento.setSelected(true);
        else tgEntregue.setSelected(true);

        txSubTotal.setText(currencyFormatter.format(delivery.getTotal().doubleValue()));
        txDesconto.setText(currencyFormatter.format(delivery.getDesconto().doubleValue()));
        txServico.setText(currencyFormatter.format(delivery.getServico().doubleValue()));
        cbTaxa.setValue(delivery.getTaxa());
        txServico.setText(currencyFormatter.format(delivery.getValorTaxa().doubleValue()));

    }
    void tabela(){
        TableColumn<PedidoProdutoItem, Number> columnId = new  TableColumn<>("*");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnId.setPrefWidth(40);

        TableColumn<PedidoProdutoItem, String> colunaNome = new  TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaNome.setCellFactory(param -> new TableCell<PedidoProdutoItem,String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                TextArea area = new TextArea();
                super.updateItem(item, empty);
                if(item==null){
                    setStyle("");
                    setText("");
                    setGraphic(null);
                }
                else{
                    area.setText(item);
                    area.setPrefColumnCount(200);
                    area.setWrapText(true);
                    setGraphic(area);
                }
            }
        });
        colunaNome.setPrefWidth(250);
        colunaNome.setMaxWidth(320);

        TableColumn<PedidoProdutoItem, Number> columnQtd = new  TableColumn<>("Qtde");
        columnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnQtd.setPrefWidth(40);

        TableColumn<PedidoProdutoItem, BigDecimal> colunaValor = new  TableColumn<>("Unit.");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setCellFactory(param -> new TableCell<PedidoProdutoItem,BigDecimal>(){
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
        TableColumn<PedidoProdutoItem, BigDecimal> colunaTotal = new  TableColumn<>("Total");
        colunaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colunaTotal.setCellFactory(param -> new TableCell<PedidoProdutoItem,BigDecimal>(){
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

        TableColumn<PedidoProdutoItem, Number> colunaEditar = new  TableColumn<>("");
        colunaEditar.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaEditar.setCellFactory(param -> new TableCell<PedidoProdutoItem,Number>(){
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
                        //abrirCadastro(tbPrincipal.getItems().get(getIndex()));
                    });
                    setGraphic(button);
                }
            }
        });
        TableColumn<PedidoProdutoItem, Number> colunaExcluir = new  TableColumn<>("");
        colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaExcluir.setCellFactory(param -> new TableCell<PedidoProdutoItem,Number>(){
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
                        //boolean removed = excluirProduto(tbPrincipal.getItems().get(getIndex()));
                        //if(removed) tbPrincipal.getItems().remove(getIndex());
                    });
                    setGraphic(button);
                }
            }
        });
        tbPrincipal.getColumns().addAll(columnId,colunaNome,columnQtd,colunaValor,colunaTotal,colunaEditar,colunaExcluir);
        tbPrincipal.setTableMenuButtonVisible(true);
        tbPrincipal.prefHeight(40);
    }
    public void salvar(){

    }
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }

    @FXML
    void servico(ActionEvent event) {

    }


}
