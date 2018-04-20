package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.controller.pedido.PedidoItemAcoObsCadastroController;
import com.tiagods.delivery.controller.pedido.PedidoItemPizzaAcoObsCadastroController;
import com.tiagods.delivery.controller.pedido.PedidoPagamentoController;
import com.tiagods.delivery.controller.pedido.PedidoPagamentoTaxaDescontoController;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoTaxa;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.repository.helper.*;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Label txContato;

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
    private PedidosProdutosItensImpl itens;


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
                    Entregador etemp = cbEntregador.getValue();
                    super.loadFactory();
                    entregadores = new EntregadoresImpl(super.getManager());
                    Entregador entregador = entregadores.findByNome(result.get().trim());
                    if(entregador==null){
                        Entregador en = new Entregador();
                        en.setNome(result.get().trim());
                        en.setAtivo(true);
                        en = entregadores.save(en);
                        cbEntregador.getItems().clear();
                        final List<Entregador> lista = entregadores.getAll();
                        cbEntregador.getItems().addAll(lista);
                        if(etemp!=null) cbEntregador.setValue(etemp);
                        new ComboBoxAutoCompleteUtil<>(cbEntregador);
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
    void abrirCadastroItem(PedidoProdutoItem item){
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
            tbPrincipal.refresh();
        }catch(IOException e) {
            alert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o cadastro", "Falha ao abrir cadastro do Delivery",e,true);
        }
    }
    private	void abrirCliente(Cliente cliente){
        try {
            Stage stage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClienteCadastro.fxml"));
            loader.setController(new ClienteCadastroController(cliente,stage));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding(event -> buscarClientePeloTelefone(txTelefoneBusca.getText()));
        }catch(IOException e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "Erro", null, "Erro ao abrir o cadastro", e,true);
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
        bucarCep(txCEP,txLogradouro,txNumero,txComplemento,txBairro,cbCidade,cbEstado);
    }

    @FXML
    void buscarCliente(ActionEvent event) {
        if(cliente!=null) abrirCliente(cliente);
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

        comboRegiao(cbCidade,cbEstado,getManager());

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
        if(txTotal.getText().equals("R$ 0,00")){
            alert(Alert.AlertType.ERROR, "Erro", null, "Nenhum valor a pagar", null,false);
        }
        else{

            try {
                Stage stage = new Stage();
                final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PedidoPagamento.fxml"));
                loader.setController(new PedidoPagamentoController(delivery,stage));
                final Parent root = loader.load();
                final Scene scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
                stage.setOnHiding(event1-> {
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
                e.printStackTrace();
                alert(Alert.AlertType.ERROR, "Erro", null, "Erro ao abrir o cadastro", e,true);
            }
        }
    }
    void preencherEndereco(Cliente cliente){
        txTelefoneBusca.setText(delivery.getFoneOrigem());
        String text="";
        String style="";
        if(cliente.getTipo()==null){
            txContato.setText(NAOCADASTRADO);
            txContato.setStyle("-fx-text-fill: white; -fx-background-color:red;");
        }
        if (cliente.getTipo().equals(Cliente.ClienteTipo.EMPRESA)) {
            PessoaJuridica juridica = cliente.getJuridico();
            if(juridica.getCnpj()!=null && juridica.getCnpj().length()==13) {
                text=(CADASTRADO);
                style=("-fx-text-fill: white; -fx-background-color:green;");
            }
            else{
                text=(NAOPROMOCIONAL);
                style=("-fx-text-fill: white; -fx-background-color:orange;");
            }
        }
        else if(cliente.getTipo().equals(Cliente.ClienteTipo.PESSOA)){
            PessoaFisica fisica = cliente.getFisico();

            if(fisica.getCpf().length()==11 || fisica.getRg().trim().length()>0){
                text=(CADASTRADO);
                style=("-fx-text-fill: white; -fx-background-color:green;");

            }
            else{
                text=(NAOPROMOCIONAL);
                style=("-fx-text-fill: white; -fx-background-color:orange;");
            }
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
        this.cliente=cliente;
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
        if(txTelefoneBusca.getText().length()==10 && txTelefone.getText().trim().equals(""))
            txTelefone.setText(txTelefoneBusca.getText());
        else if(txTelefoneBusca.getText().length()==11 && txCelular.getText().trim().equals(""))
            txCelular.setText(txTelefoneBusca.getText());
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
    private boolean removerItem(PedidoProdutoItem item){
        try{
            super.loadFactory();
            itens = new PedidosProdutosItensImpl(super.getManager());
            item = itens.findById(item.getId());
            itens.remove(item);
            return true;
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,"Falha ao excluir o item", e, true);
            return false;
        }
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
            delivery.setFoneOrigem(txTelefoneBusca.getText());

            if(cliente==null && txContato.getText().equals(NAOCADASTRADO)){
                cliente = new Cliente();
                cliente.setTipo(Cliente.ClienteTipo.PESSOA);
                cliente.setCriadoEm(Calendar.getInstance());
                cliente.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
                PessoaFisica fisica = new PessoaFisica();
                fisica.setAniversario("");
                fisica.setCpf("");
                fisica.setRg("");
                cliente.setFisico(fisica);
                cliente = atualizaEndereco(cliente);
                delivery.setCliente(cliente);
            }
            else    {
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
            if(getManager().isOpen())
                close();
        }

    }
    @FXML
    void salvar(ActionEvent event){
        if(txTelefoneBusca.getText().trim().equals("") && cliente==null){
            alert(Alert.AlertType.ERROR,"Erro","Nenhum cliente foi informado",
                            "Preencha o campo telefone e clique em Buscar!", null, false);
        }
        else if(txLogradouro.getText().trim().equals("") || txBairro.getText().trim().equals("")
                || txNumero.getText().trim().equals("")){
            alert(Alert.AlertType.ERROR,"Erro", "Endereço inválido",
                    "Verifique os campos Rua,Numero e Bairro do cliente!", null, false);
        }
        else if(cliente==null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Aviso Importante");
            alert.setHeaderText("Não foi identificado um cliente");
            alert.setContentText("Para possibilitar a entrega no endereço mencionado, \n cliente deve estar cadastrado no sistema\n" +
                    "Deseja cadastrar um novo cliente para o telefone "+txTelefoneBusca.getText()+"?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK && salvar())
                super.alert(Alert.AlertType.INFORMATION,"Sucesso", null, "Salvo com sucesso!", null, false);

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
        TableColumn<PedidoProdutoItem, Number> columnQtd = new  TableColumn<>("Qtde");
        columnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnQtd.setCellFactory(param -> new TableCell<PedidoProdutoItem,Number>(){
            JFXButton button = new JFXButton();
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if(item==null){
                    setStyle("");
                    setText("");
                    setGraphic(null);
                }
                else{
                    button.setText(""+item.longValue());
                    button.getStyleClass().add("btGreen");
                    button.setOnAction(event -> {
                        Set<Integer> integers = new HashSet<>();
                        int v = 1;
                        while(v<=100){
                            integers.add(v);
                            v++;
                        }
                        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(item.intValue(),integers);
                        dialog.setTitle("Quantidade");
                        dialog.setHeaderText("Alterar Quantidade");
                        dialog.setContentText("Por favor, escolha uma nova quantidade");
                        Optional<Integer> result = dialog.showAndWait();
                        if(result.isPresent()){
                            PedidoProdutoItem newItem = tbPrincipal.getItems().get(getIndex());
                            newItem.setQuantidade(result.get());
                            try{
                                loadFactory();
                                itens = new PedidosProdutosItensImpl(getManager());
                                itens.save(newItem);

                                pedidos = new PedidosDeliveryImpl(getManager());
                                delivery = pedidos.findById(delivery.getId());
                                preencherFormulario(delivery);
                                salvar();
                            }catch (Exception e){
                                alert(Alert.AlertType.ERROR,"Erro",null,
                                        "Erro ao atualizar a quantidade do item",e,true);
                            }finally {
                                close();
                            }
                        }

                    });
                    setGraphic(button);
                }
            }
        });
        columnQtd.setPrefWidth(60);
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
        colunaNome.setMinWidth(300);
        colunaNome.setMaxWidth(400);


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
        TableColumn<PedidoProdutoItem, BigDecimal> colunaAdd = new  TableColumn<>("Adicional");
        colunaAdd.setCellValueFactory(new PropertyValueFactory<>("valorExtra"));
        colunaAdd.setCellFactory(param -> new TableCell<PedidoProdutoItem,BigDecimal>(){
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
                    PedidoProdutoItem produtoItem = tbPrincipal.getItems().get(getIndex());
                    button.getStyleClass().add(
                            produtoItem.getComplementos().size()==0 && produtoItem.getObservacoes().size()==0?
                                    "btGreen":"btBlue");

                    button.setOnAction(event -> {
                        abrirCadastroItem(tbPrincipal.getItems().get(getIndex()));
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
                        PedidoProdutoItem produtoItem = tbPrincipal.getItems().get(getIndex());
                        if(removerItem(produtoItem)) {
                            tbPrincipal.getItems().remove(getIndex());
                            salvar();
                        }
                    });
                    setGraphic(button);
                }
            }
        });
        tbPrincipal.getColumns().addAll(columnQtd,colunaNome,colunaValor,colunaAdd,colunaTotal,colunaEditar,colunaExcluir);
        tbPrincipal.setTableMenuButtonVisible(true);
        tbPrincipal.setFixedCellSize(80);
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
