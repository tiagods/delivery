package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.repository.helper.CidadesImpl;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import com.tiagods.delivery.repository.helper.EmpresasImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskedTextField;
import org.hibernate.PersistentObjectException;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class EmpresaCadastroController extends UtilsController implements Initializable {

    @FXML
    private AnchorPane pnCadastro;

    @FXML
    private JFXTextField txCodigo;

    @FXML
    private JFXTextField txNome;

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
    private MaskedTextField txCEP;

    @FXML
    private JFXTextField txLogradouro;

    @FXML
    private JFXTextField txBairro;

    @FXML
    private JFXTextField txComplemento;

    @FXML
    private JFXComboBox<Cidade.Estado> cbEstado;

    @FXML
    private JFXComboBox<Cidade> cbCidade;

    @FXML
    private MaskedTextField txTelefone;

    @FXML
    private MaskedTextField txCelular;

    @FXML
    private JFXTextField txNumero;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnSair;

	private Empresa empresa = null;
	private Stage stage;
	private CidadesImpl cidades;
	private EmpresasImpl empresas;

	public EmpresaCadastroController(Stage stage) {
		this.stage = stage;
	}
	@FXML
	void bucarCep(ActionEvent event){
		bucarCep(txCEP,txLogradouro,txNumero,txComplemento,txBairro,cbCidade,cbEstado);

	}
	private void combos(){
	    comboRegiao(cbCidade,cbEstado,getManager());
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        try {
            super.loadFactory();
            combos();
            empresas = new EmpresasImpl(getManager());
            empresa = empresas.findById(new Long(1));

            if(empresa!=null) preencherFormulario(empresa);
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR, "Erro", null,
                    "Falha ao listar os registros",e,true);
            e.printStackTrace();
        }finally {
            super.close();
        }

    }
	void preencherFormulario(Empresa empresa) {
        txCodigo.setText(String.valueOf(empresa.getId()));
        PessoaJuridica juridica = empresa.getJuridico();
        txRazao.setText(juridica.getRazao());
        txCnpj.setPlainText(juridica.getCnpj());
        txIM.setText(juridica.getIm());
        txIE.setText(juridica.getIe());
        Pessoa pessoa = empresa.getPessoa();
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
	    this.empresa = empresa;
	}
	@FXML
	void salvar(ActionEvent event) {
        try{
            Pessoa pessoa = new Pessoa();
            if(txCodigo.getText().equals("")) {
                empresa = new Empresa();
                pessoa.setCriadoEm(Calendar.getInstance());
                pessoa.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
            }
            else
                pessoa = empresa.getPessoa();
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setRazao(txRazao.getText());
            pessoaJuridica.setCnpj(txCnpj.getPlainText());
            pessoaJuridica.setIe(txIE.getText());
            pessoaJuridica.setIm(txIM.getText());
            empresa.setJuridico(pessoaJuridica);

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
            empresa.setPessoa(pessoa);
            super.loadFactory();
            empresas = new EmpresasImpl(getManager());
            empresa = empresas.save(empresa);
            alert(Alert.AlertType.INFORMATION,"Sucesso",null,
                    "Salvo com sucesso",null,false);
            //super.desbloquear(false, pnCadastro.getChildren());
            stage.close();
        }catch (PersistentObjectException e){
            alert(Alert.AlertType.ERROR,"Erro",null,
                    "Erro ao salvar o registro",e,true);
        }finally {
            super.close();
        }
	}
	@FXML
    void sair(ActionEvent event){
	    stage.close();
    }

}
