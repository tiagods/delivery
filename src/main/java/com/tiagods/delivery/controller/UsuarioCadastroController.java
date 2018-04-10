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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    private JFXButton btnSalvar;
    @FXML
    private JFXButton btnSair;

	private Usuario usuario = null;
	private Stage stage;
	private CidadesImpl cidades;
	private UsuariosImpl usuarios;
	private boolean primeiroUsuario = false;

	public UsuarioCadastroController(Usuario usuario, Stage stage,boolean primeiroUsuario) {
		this.stage = stage;
		this.usuario=usuario;
		this.primeiroUsuario=primeiroUsuario;
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
            super.alert(Alert.AlertType.ERROR,"Falha na conexão com o banco de dados",null,"Houve uma falha na conexão com o banco de dados",e,true);
		}finally {
		    super.close();
		}
	}
	private void combos(){
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
        cbNivel.getItems().addAll(UsuarioNivel.values());
        cbNivel.setValue(UsuarioNivel.ADMIN);
        new ComboBoxAutoCompleteUtil<>(cbCidade);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		    super.loadFactory();
            combos();
            if(usuario!=null) {
                preencherFormulario(usuario);
            }
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR, "Erro", null,
                    "Falha ao listar os registros", e,true);
            e.printStackTrace();
        }finally {
		    super.close();
        }
    }
	void preencherFormulario(Usuario usuario) {
        txCodigo.setText(String.valueOf(usuario.getId()));
        cbNivel.setValue(usuario.getNivel());
        txLogin.setText(usuario.getLogin());
        txLogin.setEditable(false);
        PessoaFisica fisica = usuario.getPessoaFisica();
        txRG.setText(fisica.getRg()==null?"":fisica.getRg());
        txCPF.setPlainText(fisica.getCpf()==null?"":fisica.getCpf());
        txDataNascimento.setPlainText(fisica.getAniversario()==null?"":fisica.getAniversario());
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
        boolean validarLogin = false;
        if(txLogin.getText().trim().equals("")){
            super.alert(Alert.AlertType.ERROR, "Login Invalido", null, "Login não informado",null, false);
            return;
        }

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
            usuario.setLogin(txLogin.getText());
            usuario.setPessoa(pessoa);
            //super.desbloquear(false, pnCadastro.getChildren());
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
                    txSenha.setText("");
                    txConfirmarSenha.setText("");
                    if(primeiroUsuario) {
                        UsuarioLogado.getInstance().setUsuario(usuario);
                        try {
                            //Icons estilo = Icons.getInstance();
                            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
                            loader.setController(new MenuController());
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage1 = new Stage();
                            stage1.setScene(scene);
                            stage1.setTitle("Menu Inicial");
                            //stage.getIcons().add(new Image(estilo.getIcon().toString()));
                            stage1.show();
                            stage.setOnHiding(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    stage.close();
                } catch (Exception e) {
                    super.alert(Alert.AlertType.ERROR,"Erro",null,"Erro ao salvar o registro do Usuario",e,true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
	}
	@FXML
    void sair(ActionEvent event){
	    stage.close();
    }
    private boolean validarLogin(String login){
        Usuario u = usuarios.findByLogin(login);
        if(u!=null) {
            super.alert(Alert.AlertType.ERROR,"Informação incompleta!","Login inválido!",
                    u.getPessoa().getNome()+" já esta usando esse login",null,false);
            return false;
        }
        else
            return true;
    }
    private boolean validarSenha() {
        if (txSenha.getText().trim().equals("")) {
            super.alert(Alert.AlertType.ERROR,"Informação incompleta!","Senhas em branco ou não conferem",
                    "Por favor verifique se a senha esta correta",null,false);
            return false;
        } else {
            if (txSenha.getText().trim().equals(txConfirmarSenha.getText().trim()))
                return true;
            else {
                super.alert(Alert.AlertType.ERROR,"Informação incompleta!","Senhas em branco ou não conferem",
                        "Por favor verifique se a senha esta correta",null,false);
                return false;
            }
        }
    }
}
