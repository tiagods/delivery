package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.model.Cidade;
import com.tiagods.delivery.model.Cliente;
import com.tiagods.delivery.model.PessoaFisica;
import com.tiagods.delivery.model.PessoaJuridica;
import com.tiagods.delivery.repository.helper.CidadesImpl;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskedTextField;
import org.hibernate.PersistentObjectException;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteCadastroController extends UtilsController implements Initializable {
	@FXML
	private AnchorPane pnCadastro;

	@FXML
	private JFXTextField txCodigo;

	@FXML
	private JFXTextField txNome;

	@FXML
	private Pane pnPessoaFisica;

	@FXML
	private JFXTextField txRG;

	@FXML
	private MaskedTextField txCPF;

	@FXML
	private MaskedTextField txDataNascimento;

	@FXML
	private Pane pnPessoaJuridica;

	@FXML
	private JFXTextField txRazao;

	@FXML
	private MaskedTextField txCnpj;

	@FXML
	private JFXTextField txIM;

	@FXML
	private JFXTextField txIE;

	@FXML
	private JFXTextField txEmail;

	@FXML
	private MaskedTextField txTelefone;

	@FXML
	private MaskedTextField txCelular;

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
	private JFXComboBox<Cidade.Estado> cbEstado;

	@FXML
	private JFXComboBox<Cidade> cbCidade;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private JFXButton btnSair;
	@FXML
    private JFXRadioButton rbEmpresa;
	@FXML
    private JFXRadioButton rbPessoa;

	private Stage stage;
	private CidadesImpl cidades;
    private Cliente cliente = null;
    private ClientesImpl clientes;

	public ClienteCadastroController(Cliente cliente, Stage stage) {
		this.stage = stage;
		this.cliente= cliente;
	}
    @FXML
    void bucarCep(ActionEvent event){
        bucarCep(txCEP,txLogradouro,txNumero,txComplemento,txBairro,cbCidade,cbEstado);
    }
	private void combos(){
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(rbEmpresa,rbPessoa);
        pnPessoaJuridica.setVisible(false);
        rbPessoa.setSelected(true);
        pnPessoaFisica.setVisible(true);
        ChangeListener selecao = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (rbEmpresa.isSelected()) {
                    pnPessoaJuridica.setVisible(true);
                    pnPessoaFisica.setVisible(false);
                }
                else {
                    pnPessoaFisica.setVisible(true);
                    pnPessoaJuridica.setVisible(false);
                }
            }
        };
	    rbPessoa.selectedProperty().addListener(selecao);
	    rbEmpresa.selectedProperty().addListener(selecao);

        cidades = new CidadesImpl(getManager());
        Cidade cidade = cidades.findByNome("São Paulo");
        cbCidade.getItems().setAll(cidades.findByEstado(Cidade.Estado.SP));
        cbCidade.setValue(cidade);
        cbEstado.getItems().addAll(Cidade.Estado.values());
        cbEstado.setValue(Cidade.Estado.SP);
        cbEstado.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    super.loadFactory(super.getManager());
                    cidades = new CidadesImpl(getManager());
                    cbCidade.getItems().clear();
                    List<Cidade> listCidades = cidades.findByEstado(newValue);
                    cbCidade.getItems().addAll(listCidades);
                    cbCidade.getSelectionModel().selectFirst();
                } catch (Exception e) {
                } finally {
                    if(super.getManager().isOpen()) super.close();
                }
            }
        });
        new ComboBoxAutoCompleteUtil<>(cbCidade);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		    super.loadFactory();
            combos();
            if(cliente!=null) {
                preencherFormulario(cliente);
            }
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR, "Erro", null, "Falha ao listar os registros",e,true);
            e.printStackTrace();
        }finally {
		    super.close();
        }
    }

	void preencherFormulario(Cliente cliente) {
        txCodigo.setText(String.valueOf(cliente.getId()));

        Cliente.ClienteTipo tipo = cliente.getTipo();
        if(tipo == null){
            rbPessoa.setSelected(true);
        }
        else if (tipo.equals(Cliente.ClienteTipo.PESSOA)) {
            rbPessoa.setSelected(true);
            PessoaFisica fisica = cliente.getFisico();
            txRG.setText(fisica.getRg());
            txCPF.setPlainText(fisica.getCpf());
            txDataNascimento.setPlainText(fisica.getAniversario());
        }
        else{
            rbEmpresa.setSelected(true);
            PessoaJuridica juridica = cliente.getJuridico();
            txRazao.setText(juridica.getRazao());
            txCnpj.setPlainText(juridica.getCnpj());
            txIM.setText(juridica.getIm());
            txIE.setText(juridica.getIe());
        }
        txNome.setText(cliente.getNome());
        txEmail.setText(cliente.getEmail());
        txTelefone.setPlainText(cliente.getTelefone());
        txCelular.setPlainText(cliente.getCelular());
        txCEP.setPlainText(cliente.getCep());
        txLogradouro.setText(cliente.getEndereco());
        txNumero.setText(cliente.getNumero());
	    txBairro.setText(cliente.getBairro());
	    txComplemento.setText(cliente.getComplemento());
	    cbEstado.getSelectionModel().select(cliente.getEstado());
	    cbCidade.setValue(cliente.getCidade());
	    this.cliente = cliente;
	}
	@FXML
	void salvar(ActionEvent event) {
        try{
            if(txCodigo.getText().equals("")) {
                cliente = new Cliente();
                cliente.setCriadoEm(Calendar.getInstance());
                cliente.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
            }
            Cliente.ClienteTipo tipo = rbEmpresa.isSelected() ? Cliente.ClienteTipo.EMPRESA : Cliente.ClienteTipo.PESSOA;
            cliente.setTipo(tipo);
            if(tipo.equals(Cliente.ClienteTipo.PESSOA)){
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica.setRg(txRG.getText().trim());
                pessoaFisica.setCpf(txCPF.getPlainText());
                pessoaFisica.setAniversario(txDataNascimento.getPlainText());
                cliente.setFisico(pessoaFisica);
                cliente.setJuridico(null);
            }
            else{
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setRazao(txRazao.getText());
                pessoaJuridica.setCnpj(txCnpj.getPlainText());
                pessoaJuridica.setIe(txIE.getText());
                pessoaJuridica.setIm(txIM.getText());
                cliente.setJuridico(pessoaJuridica);
                cliente.setFisico(null);
            }
            cliente.setNome(txNome.getText().trim());
            cliente.setEmail(txEmail.getText().trim());
            cliente.setTelefone(txTelefone.getPlainText());
            cliente.setCelular(txCelular.getPlainText());
            cliente.setCep(txCEP.getPlainText());
            cliente.setEndereco(txLogradouro.getText().trim());
            cliente.setNumero(txNumero.getText().trim());
            cliente.setBairro(txBairro.getText().trim());
            cliente.setComplemento(txComplemento.getText());
            cliente.setEstado(cbEstado.getValue());
            cliente.setCidade(cbCidade.getValue());
            super.loadFactory();

            clientes = new ClientesImpl(getManager());
            cliente = clientes.save((Cliente) cliente);
            preencherFormulario(cliente);
            alert(Alert.AlertType.INFORMATION,"Sucesso",null,
                    "Salvo com sucesso",null,false);
            stage.close();
            //super.desbloquear(false, pnCadastro.getChildren());
        }catch (PersistentObjectException e){
            alert(Alert.AlertType.ERROR,"Erro",null,"Erro ao salvar o registro",e,true);
        }finally {
            super.close();
        }
	}
	@FXML
    void sair(ActionEvent event){
	    stage.close();
    }

}
