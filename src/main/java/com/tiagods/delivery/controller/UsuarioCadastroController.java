package com.tiagods.delivery.controller;

import com.jfoenix.controls.*;
import com.tiagods.delivery.config.UsuarioLogado;
import com.tiagods.delivery.model.*;
import com.tiagods.delivery.repository.helper.CidadesImpl;
import com.tiagods.delivery.repository.helper.ClientesImpl;
import com.tiagods.delivery.repository.helper.UsuariosImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.CriptografiaUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskedTextField;
import org.hibernate.PersistentObjectException;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsuarioCadastroController extends UtilsController implements Initializable {
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
    private JFXComboBox<Estado> cbEstado;

    @FXML
    private JFXComboBox<Cidade> cbCidade;

    @FXML
    private MaskedTextField txTelefone;

    @FXML
    private MaskedTextField txCelular;

    @FXML
    private JFXTextField txNumero;

    @FXML
    private JFXTextField txLogin;

    @FXML
    private JFXPasswordField txSenha;

    @FXML
    private JFXPasswordField txConfirmarSenha;

    @FXML
    private JFXComboBox<UsuarioNivel> cbNivel;

    @FXML
    private JFXButton btnNovo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnExcluir;
	private Usuario usuario = null;
	private Stage stage;
	private CidadesImpl cidades;
	private UsuariosImpl usuarios;

	public UsuarioCadastroController(Usuario usuario, Stage stage) {
		this.stage = stage;
		if(usuario!=null) {
			preencherFormulario(usuario);
		}
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
                    cbEstado.setValue(endereco.getUf());
                    cidades = new CidadesImpl(super.getManager());
                    cbCidade.getItems().clear();
                    cbCidade.getItems().addAll(cidades.findByEstado(cbEstado.getValue()));
                    Cidade cidade = cidades.findByNome(endereco.getLocalidade());
                    System.out.println(cidade.getNome());
                    cbCidade.setValue(cidade);
				}
				else
				    super.alert(Alert.AlertType.WARNING,"CEP Invalido",null,
                            "Verifique se o cep informado é valido ou se existe uma conexão com a internet");
            }
			else{
				super.alert(Alert.AlertType.WARNING,"CEP Invalido",null,"Verifique o cep informado");
			}
		}catch(Exception e){
            super.alert(Alert.AlertType.ERROR,"Falha na conexão com o banco de dados",null,"Houve uma falha na conexão com o banco de dados");
		}finally {
		    super.close();
		}
	}
	@FXML
	void cancelar(ActionEvent event) {
		stage.close();
	}

	private void combos(){
        cidades = new CidadesImpl(getManager());
        Cidade cidade = cidades.findByNome("São Paulo");
        cbCidade.getItems().setAll(cidades.findByEstado(Estado.SP));
        cbCidade.setValue(cidade);
        cbEstado.getItems().addAll(Estado.values());
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
	@FXML
	void editar(ActionEvent event) {
		super.desbloquear(true, pnCadastro.getChildren());
	}
	@FXML
	void excluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exclusão...");
        alert.setHeaderText(null);
        if (!txCodigo.getText().equals("")) {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza disso?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                try {
                    super.loadFactory();
                    usuarios = new UsuariosImpl(super.getManager());
                    usuario = usuarios.findById(Long.parseLong(txCodigo.getText()));
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Registro excluido com sucesso!");
                    alert.showAndWait();
                    super.limpar(pnCadastro.getChildren());
                    super.desbloquear(false,pnCadastro.getChildren());
                    usuario = null;
                } catch (Exception e) {
                    super.alert(Alert.AlertType.ERROR,"Erro ao excluir",null,"Falha ao tentar excluir o registro\n"+e.getMessage());
                } finally {
                    close();
                }
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Nenhum registro selecionado!");
            alert.showAndWait();
        }
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		    super.loadFactory();
            combos();
            super.Initializer(btnNovo, btnEditar, btnSalvar, btnExcluir, btnCancelar);
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR, "Erro", null, "Falha ao listar os registros\n" + e.getMessage());
            e.printStackTrace();
        }finally {
		    super.close();
        }
    }
	@FXML
	void novo(ActionEvent event) {
		super.desbloquear(true, pnCadastro.getChildren());
        super.limpar(pnCadastro.getChildren());
	}
	void preencherFormulario(Usuario usuario) {
        txCodigo.setText(String.valueOf(usuario.getId()));
        cbNivel.setValue(usuario.getNivel());
        txLogin.setText(usuario.getLogin());
        PessoaFisica fisica = usuario.getPessoaFisica();
        txRG.setText(fisica.getRg());
        txCPF.setPlainText(fisica.getCpf());
        txDataNascimento.setPlainText(fisica.getAniversario());

        Pessoa pessoa = usuario.getPessoa();
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
	    this.usuario= usuario;
	}
	@FXML
	void salvar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean validarLogin = false;
        try {
            super.loadFactory();
            usuarios = new UsuariosImpl(getManager());
            Pessoa pessoa = new Pessoa();
            if(txCodigo.getText().equals("")) {
                usuario = new Usuario();
                pessoa.setCriadoEm(Calendar.getInstance());
                pessoa.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
                validarLogin = validarLogin(txLogin.getText());
                if (!validarLogin)
                    return;
                else
                    validarLogin = validarSenha();
            } else {
                usuario.setId(Integer.parseInt(txCodigo.getText()));
                pessoa = usuario.getPessoa();
                if (txSenha.getText().trim().equals(""))
                    validarLogin = true;
                else
                    validarLogin = validarSenha();
            }

            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setRg(txRG.getText().trim());
            pessoaFisica.setCpf(txCPF.getPlainText());
            pessoaFisica.setAniversario(txDataNascimento.getPlainText());
            usuario.setPessoaFisica(pessoaFisica);

            usuario.setNivel(cbNivel.getValue());
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
            usuario.setPessoa(pessoa);
            super.desbloquear(false, pnCadastro.getChildren());
            if (validarLogin) {
                if (!txSenha.getText().trim().equals("")) {
                    CriptografiaUtil cripto = new CriptografiaUtil();
                    usuario.setSenha(cripto.criptografar(txSenha.getText()));
                }
                try {
                    super.loadFactory();
                    usuarios = new UsuariosImpl(getManager());
                    usuario = usuarios.save(usuario);
                    preencherFormulario(usuario);
                    super.desbloquear(false,pnCadastro.getChildren());
                    txSenha.setText("");
                    txConfirmarSenha.setText("");
                    txLogin.setEditable(false);
                } catch (Exception e) {
                    alert(Alert.AlertType.ERROR,"Erro",null,"Erro ao salvar o registro \n"+e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
	}
    private boolean validarLogin(String login){
        Usuario u = usuarios.findByLogin(login);
        if(u!=null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Informação incompleta");
            alert.setHeaderText("Já existe alguem usando esse login");
            alert.setContentText(u.getPessoa().getNome()+" já utiliza esse login");
            alert.showAndWait();
            return false;
        }
        else
            return true;
    }
    private boolean validarSenha() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Informação incompleta");
        alert.setContentText(null);
        if (txSenha.getText().trim().equals("")) {
            alert.setHeaderText("Senha não informada!");
            alert.showAndWait();
            return false;
        } else {
            if (txSenha.getText().trim().equals(txConfirmarSenha.getText().trim()))
                return true;
            else {
                alert.setHeaderText("Senhas não conferem!");
                alert.showAndWait();
                return false;
            }
        }
    }

}
