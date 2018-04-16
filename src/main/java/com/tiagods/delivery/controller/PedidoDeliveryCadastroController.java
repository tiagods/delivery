package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.pedido.PedidoPagamentoTaxaDescontoController;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoTaxa;
import com.tiagods.delivery.repository.helper.*;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;
import org.fxutils.maskedtextfield.MaskedTextField;

import java.io.IOException;
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
    private JFXComboBox<Cidade.Estado> cbEstado;

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
    private PedidosTaxasImpl pedidosTaxas;
    private Stage stage;
    private Cliente cliente;
    private String telefone;

    private String NAOCADASTRADO= "Não Cadastrado";
    private String CADASTRADO="Cadastrado";
    private String NAOPROMOCIONAL="Não Promocional";

    Locale locale = new Locale("pt", "BR");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private ClientesImpl clientes;


    public PedidoDeliveryCadastroController(PedidoDelivery pedidoDelivery, Cliente cliente, String telefone, Stage stage) {
        this.delivery= pedidoDelivery;
        this.cliente=cliente;
        this.telefone=telefone;
        this.stage = stage;
    }
    @FXML
    void adicionarEntregador(ActionEvent event) {
        Optional<String> result = cadastroRapido();
        if(result.isPresent()){
            if(result.get().trim().equals("")){
                super.alert(Alert.AlertType.ERROR,"Erro","Nome inválido",
                        "Informe um nome válido",null,false);
            }
            else{
                try{
                    super.loadFactory();
                    entregadores = new EntregadoresImpl(super.getManager());
                    Entregador entregador = entregadores.findByNome(result.get().trim());
                    if(entregador==null){
                        Entregador en = new Entregador();
                        en.setNome(result.get().trim());
                        en.setAtivo(true);
                        entregadores.save(en);
                        List<Entregador> entregadorList = entregadores.getAll();
                        Entregador etemp = cbEntregador.getValue();
                        cbEntregador.getItems().clear();
                        cbEntregador.getItems().addAll(entregadorList);
                        if(etemp!=null) cbEntregador.setValue(etemp);
                    }
                    else{
                        super.alert(Alert.AlertType.ERROR,"Duplicado",null,
                                "Já existe um cadastro com o nome indicado!",null,false);
                    }
                }catch (Exception e){
                    super.alert(Alert.AlertType.ERROR,"Erro",
                            null,"Ocorreu um erro ao salvar o registro", e, true);
                }finally {
                    super.close();
                }
            }
        }
    }
    void adicionarProduto(){
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PedidoProdutoPesquisa.fxml"));
            loader.setController(new PedidoProdutoPesquisaController(delivery,stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding(event -> {
                try{
                    loadFactory();
                    pedidos = new PedidosDeliveryImpl(getManager());
                    delivery = pedidos.findById(delivery.getId());
                    preencherFormulario(delivery);
                    salvar();
                }catch (Exception e){
                    alert(Alert.AlertType.ERROR, "Erro", "Erro ao preencher formulario", "Falha preencher o Delivery",e,true);
                }finally {
                    if(getManager().isOpen())
                        super.close();
                }
            });
        }catch(IOException e) {
            alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", "Falha ao abrir cadastro do Delivery",e,true);
        }
    }
    @FXML
    void adicionarProduto(ActionEvent event) {
        adicionarProduto();
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
                    cbEstado.setValue(endereco.getUf());
                    cbCidade.setValue(cidade);
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
        if(!txTelefoneBusca.getText().trim().equals("")
                ||txTelefoneBusca.getText().trim().length()==10
                ||txTelefoneBusca.getText().trim().length()==11)
            buscarClientePeloTelefone(txTelefoneBusca.getText());
        else{
            super.alert(Alert.AlertType.ERROR, "Erro", null,
                    "Informe um telefone inválido",null, false);
        }
    }
    public void buscarClientePeloTelefone(String telefone){
        txTelefoneBusca.setText(telefone);
        try{
            super.loadFactory();
            clientes = new ClientesImpl(super.getManager());
            cliente = clientes.procurarTelefone(txTelefoneBusca.getText());
            if(cliente!=null){
                preencherEndereco(cliente);

                delivery.setCliente(cliente);
                pedidos = new PedidosDeliveryImpl(super.getManager());
                delivery = pedidos.save(delivery);
                preencherFormulario(delivery);
            }
            else{
                txContato.setText(NAOCADASTRADO);
                txContato.setStyle("-fx-text-fill: red;");
            }
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro", null,"Erro ao pesquisar cliente",e,true);
        }finally {
            super.close();
        }
    }

    void calculateTime(Calendar calendar){

    }

    void combos(){
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(tgIniciado,tgEspera,tgAndamento,tgEntregue);
        tgIniciado.setSelected(true);

        cidades = new CidadesImpl(getManager());
        Cidade cidade = cidades.findByNome("São Paulo");
        cbCidade.getItems().setAll(cidades.findByEstado(Cidade.Estado.SP));
        cbCidade.setValue(cidade);
        cbEstado.getItems().addAll(Cidade.Estado.values());
        cbEstado.setValue(Cidade.Estado.SP);
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

        pedidosTaxas = new PedidosTaxasImpl(super.getManager());

        cbTaxa.getItems().addAll(pedidosTaxas.getAll());
        cbTaxa.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null && newValue!=oldValue){
                txTaxa.setText(currencyFormatter.format(cbTaxa.getValue().getValor().doubleValue()));
            }
        });
    }
    @FXML
    void desconto(ActionEvent event) {
        taxaServicoDesconto();
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
        txTemporizador.setVisible(false);
        tabela();
        try{
            super.loadFactory();
            combos();
            pedidos = new PedidosDeliveryImpl(super.getManager());
            if(delivery!=null){
                this.delivery = pedidos.findById(delivery.getId().longValue());
                preencherFormulario(this.delivery);
            }
            else{
                delivery = new PedidoDelivery();
                if(cliente!=null)
                    delivery.setCliente(this.cliente);
                delivery.setStatus(PedidoDelivery.PedidoStatus.INICIADO);
                delivery.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
                delivery.setCriadoEm(Calendar.getInstance());
                delivery.setEntregador(cbEntregador.getValue());
                delivery.setSubTotal(new BigDecimal(0.00));
                delivery.setDesconto(new BigDecimal(0.00));
                delivery.setServico(new BigDecimal(0.00));
                delivery.setTaxa(cbTaxa.getValue());
                delivery.setValorTaxa(new BigDecimal(0.00));
                delivery.setTotal(new BigDecimal(0.00));
                delivery = pedidos.save(delivery);
                preencherFormulario(delivery);
            }
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro", null,"Erro ao criar Pedido Delivery",e, true);
        }finally {
            super.close();
        }
    }
    @FXML
    private void pagamento(ActionEvent event) {

    }
    void preencherEndereco(Cliente cliente){
        txTelefoneBusca.setText(cliente.getTelefone());

        String text="";
        String style="";
        if (cliente.getTipo().equals(Cliente.ClienteTipo.EMPRESA)) {
            PessoaJuridica juridica = cliente.getJuridico();
            if(juridica.getCnpj().length()==13) {
                text=(CADASTRADO);
                style=("-fx-text-fill: write; -fx-background-color:green;");
            }
            else{
                text=(NAOPROMOCIONAL);
                style=("-fx-text-fill: write; -fx-background-color:orange;");
            }
        }
        else if(cliente.getTipo().equals(Cliente.ClienteTipo.PESSOA)){
            PessoaFisica fisica = cliente.getFisico();
            if(fisica.getCpf().length()==11 || fisica.getRg().trim().length()>0){
                text=(CADASTRADO);
                style=("-fx-text-fill: write; -fx-background-color:green;");

            }
            else{
                text=(NAOPROMOCIONAL);
                style=("-fx-text-fill: write; -fx-background-color:orange;");
            }
        }
        else {
            txContato.setText(NAOCADASTRADO);
            txContato.setStyle("-fx-text-fill: write; -fx-background-color:red;");
        }
        txContato.setStyle(style);
        txContato.setText(text);

        txTelefone.setText(cliente.getTelefone());
        txCelular.setText(cliente.getCelular());
        txCEP.setPlainText(cliente.getCep());
        txLogradouro.setText(cliente.getEndereco());
        txNumero.setText(cliente.getNumero());
        txBairro.setText(cliente.getBairro());
        txComplemento.setText(cliente.getComplemento());
        cbEstado.setValue(cliente.getEstado());
        cbCidade.setValue(cliente.getCidade());
    }

    void preencherFormulario(PedidoDelivery delivery){
//        if(delivery.getStatus().equals(PedidoDelivery.PedidoStatus.ENTREGUE))
//            calculateTime(delivery.getCriadoEm());
        txCodigo.setText(String.valueOf(delivery.getId()));
        //endereco
        Cliente cliente = delivery.getCliente();
        if(cliente != null) {
            preencherEndereco(cliente);
        }
        tbPrincipal.getItems().clear();
        tbPrincipal.getItems().addAll(delivery.getProdutos());

        cbEntregador.setValue(delivery.getEntregador());
        if(delivery.getStatus().equals(PedidoDelivery.PedidoStatus.INICIADO))
            tgIniciado.setSelected(true);
        else if(delivery.getStatus().equals(PedidoDelivery.PedidoStatus.AGUARDANDO))
            tgEspera.setSelected(true);
        else if(delivery.getStatus().equals(PedidoDelivery.PedidoStatus.ANDAMENTO))
            tgAndamento.setSelected(true);
        else tgEntregue.setSelected(true);

        txSubTotal.setText(currencyFormatter.format(delivery.getSubTotal().doubleValue()));
        txDesconto.setText(currencyFormatter.format(delivery.getDesconto().doubleValue()));
        txServico.setText(currencyFormatter.format(delivery.getServico().doubleValue()));
        txTaxa.setText(currencyFormatter.format(delivery.getValorTaxa().doubleValue()));
        cbTaxa.setValue(delivery.getTaxa());
        txTotal.setText(currencyFormatter.format(delivery.getTotal().doubleValue()));

    }
    private Cliente atualizaEndereco(Cliente cliente) {
        cliente.setTelefone(txTelefone.getText());
        cliente.setCelular(txCelular.getText());
        cliente.setCep(txCEP.getPlainText());
        cliente.setEndereco(txLogradouro.getText());
        cliente.setNumero(txNumero.getText());
        cliente.setBairro(txBairro.getText());
        cliente.setComplemento(txComplemento.getText());
        cliente.setEstado(cbEstado.getValue());
        cliente.setCidade(cbCidade.getValue());
        clientes = new ClientesImpl(getManager());
        cliente = clientes.save(cliente);
        return cliente;

    }
    public boolean salvar(){
        //tabela
        Set<PedidoProdutoItem> produtoItems = new HashSet<>();
        produtoItems.addAll(tbPrincipal.getItems());
        delivery.setProdutos(produtoItems);

        delivery.setEntregador(cbEntregador.getValue());
        if(tgIniciado.isSelected())
            delivery.setStatus(PedidoDelivery.PedidoStatus.INICIADO);
        else if(tgEspera.isSelected())
            delivery.setStatus(PedidoDelivery.PedidoStatus.AGUARDANDO);
        else if(tgAndamento.isSelected())
            delivery.setStatus(PedidoDelivery.PedidoStatus.ANDAMENTO);
        else {
            delivery.setStatus(PedidoDelivery.PedidoStatus.ENTREGUE);
            if(delivery.getFimEntrega()==null) delivery.setFimEntrega(Calendar.getInstance());
        }
        BigDecimal subSaldo = produtoItems.stream()
                .map(item-> item.getTotal())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        delivery.setSubTotal(subSaldo);
        if(cbTaxa.getValue()==null || cbTaxa.getValue().getId().longValue()==-1){
            delivery.setTaxa(cbTaxa.getValue());
        }
        else delivery.setTaxa(null);
        try{
            loadFactory(getManager());
            if(cliente==null && txContato.getText().equals(NAOCADASTRADO)){
                cliente = new Cliente();
                cliente = atualizaEndereco(cliente);
                delivery.setCliente(cliente);
            }
            else if(txContato.getText().equals(CADASTRADO)){
                cliente = atualizaEndereco(cliente);
            }
            //primeiro aplico o desconto, e depois soma o total com as taxas e servicos
            double desconto=currencyFormatter.parse(txDesconto.getText()).doubleValue();
            double servico=currencyFormatter.parse(txServico.getText()).doubleValue();
            double taxa=currencyFormatter.parse(txTaxa.getText()).doubleValue();
            double total = (subSaldo.doubleValue()-desconto)+servico+taxa;
            delivery.setDesconto(new BigDecimal(desconto));
            delivery.setServico(new BigDecimal(servico));
            delivery.setValorTaxa(new BigDecimal(taxa));
            delivery.setTotal(new BigDecimal(total));

            pedidos = new PedidosDeliveryImpl(getManager());
            this.delivery = pedidos.save(delivery);
            preencherFormulario(this.delivery);
            return true;
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro", null, "Erro ao salvar", e, true);
            return false;
        }finally {
            close();
        }

    }
    @FXML
    void salvar(ActionEvent event){
        if(delivery.getCliente()==null || txTelefoneBusca.getText().trim().length()<10
                ||txTelefoneBusca.getText().trim().length()>11) {
            alert(Alert.AlertType.ERROR,"Erro", null,
                    "Nenhum cliente foi informado\n" +
                            "Preencha o campo telefone e em Buscar ou informe um telefone correto:", null, false);
        }
       else if(salvar())
            super.alert(Alert.AlertType.INFORMATION,"Sucesso", null, "Salvo com sucesso!", null, false);
    }
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }

    @FXML
    void servico(ActionEvent event) {
        taxaServicoDesconto();
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
                    area.setWrapText(true);
                    area.setEditable(false);
                    setGraphic(area);
                }
            }
        });
        colunaNome.setMinWidth(150);
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
                    button.getStyleClass().add("btGreen");
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
                    button.getStyleClass().add("btRed");
                    button.setOnAction(event -> {
                        tbPrincipal.getItems().remove(getIndex());
                        salvar();
                    });
                    setGraphic(button);
                }
            }
        });
        tbPrincipal.getColumns().addAll(colunaNome,columnQtd,colunaValor,colunaTotal,colunaEditar,colunaExcluir);
        tbPrincipal.setTableMenuButtonVisible(true);
        tbPrincipal.setFixedCellSize(50);
    }


    void taxaServicoDesconto(){
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PedidoPagamentoTaxaDesconto.fxml"));
            loader.setController(new PedidoPagamentoTaxaDescontoController(delivery,stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding(windowEvent ->{
                try {
                    super.loadFactory();
                    pedidos = new PedidosDeliveryImpl(super.getManager());
                    PedidoDelivery u = pedidos.findById(delivery.getId().longValue());
                    preencherFormulario(u);
                } catch (Exception e) {
                    super.alert(Alert.AlertType.ERROR,"Erro ao lista",null,
                            "Falha ao tentar recuperar o registro do Delivery",e,true);
                } finally {
                    super.close();
                }
            });
        }catch(IOException e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "Erro", null, "Erro ao abrir o cadastro", e,true);
        }
    }
}
