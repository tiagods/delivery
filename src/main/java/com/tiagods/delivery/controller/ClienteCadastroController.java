package com.tiagods.delivery.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXToggleNodeSkin;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.repository.helper.CidadesImpl;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import org.fxutils.maskedtextfield.MaskedTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.PersistentObjectException;

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
	private JFXComboBox<Estado> cbEstado;

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

	private Cliente cliente = null;
	private Stage stage;
	private CidadesImpl cidades;
	private ClientesImpl clientes;

	public ClienteCadastroController(Cliente cliente, Stage stage) {
		this.stage = stage;
		this.cliente= cliente;
	}
	@FXML
	void bucarCep(ActionEvent event){
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
        cbCidade.getItems().setAll(cidades.findByEstado(Estado.SP));
        cbCidade.setValue(cidade);
        cbEstado.getItems().addAll(Estado.values());
        cbEstado.setValue(Estado.SP);
        cbEstado.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    loadFactory();
                    cidades = new CidadesImpl(getManager());
                    List<Cidade> listCidades = cidades.findByEstado(newValue);
                    cbCidade.getItems().setAll(listCidades);
                } catch (Exception e) {
                } finally {
                    close();
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
        ClienteTipo tipo = cliente.getTipo();
        if(tipo==ClienteTipo.PESSOA){
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
        Pessoa pessoa = cliente.getPessoa();
        txNome.setText(pessoa.getNome());
        txEmail.setText(pessoa.getEmail());
        txTelefone.setPlainText(pessoa.getTelefone());
        txCelular.setPlainText(pessoa.getCelular());
        txCEP.setPlainText(pessoa.getCep());
        txLogradouro.setText(pessoa.getEndereco());
        txNumero.setText(pessoa.getNumero());
	    txBairro.setText(pessoa.getBairro());
	    txComplemento.setText(pessoa.getComplemento());
	    cbEstado.setValue(pessoa.getEstado());
	    cbCidade.setValue(pessoa.getCidade());
	    this.cliente = cliente;
	}
	@FXML
	void salvar(ActionEvent event) {
        try{
            Pessoa pessoa = new Pessoa();
            if(txCodigo.getText().equals("")) {
                cliente = new Cliente();
                pessoa.setCriadoEm(Calendar.getInstance());
                pessoa.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
            }
            else{
                pessoa = cliente.getPessoa();

            }
            ClienteTipo tipo = rbEmpresa.isSelected()?ClienteTipo.EMPRESA:ClienteTipo.PESSOA;
            cliente.setTipo(tipo);
            if(tipo==ClienteTipo.PESSOA){
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
            pessoa.setNome(txNome.getText().trim());
            pessoa.setEmail(txEmail.getText().trim());
            pessoa.setTelefone(txTelefone.getPlainText());
            pessoa.setCelular(txCelular.getPlainText());
            pessoa.setCep(txCEP.getPlainText());
            pessoa.setEndereco(txLogradouro.getText().trim());
            pessoa.setNumero(txNumero.getText().trim());
            pessoa.setBairro(txBairro.getText().trim());
            pessoa.setComplemento(txComplemento.getText());
            pessoa.setEstado(cbEstado.getValue());
            pessoa.setCidade(cbCidade.getValue());
            cliente.setPessoa(pessoa);
            super.loadFactory();
            clientes = new ClientesImpl(getManager());
            cliente = clientes.save(cliente);
            preencherFormulario(cliente);
            super.desbloquear(false, pnCadastro.getChildren());
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
