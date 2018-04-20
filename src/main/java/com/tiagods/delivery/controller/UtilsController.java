package com.tiagods.delivery.controller;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.tiagods.delivery.model.Cidade;
import com.tiagods.delivery.model.Endereco;
import com.tiagods.delivery.repository.helper.CidadesImpl;
import com.tiagods.delivery.util.ComboBoxAutoCompleteUtil;
import com.tiagods.delivery.util.EnderecoUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.fxutils.maskedtextfield.MaskTextField;
import org.fxutils.maskedtextfield.MaskedTextField;

import javax.persistence.EntityManager;

public abstract class UtilsController extends PersistenciaController{
	private JFXButton buttonNovo;
	private JFXButton buttonEditar;
	private JFXButton buttonSalvar;
	private JFXButton buttonExcluir;
	private JFXButton buttonCancelar;
	private JFXButton buttonSair;
	private boolean habilidarFiltroCidade = true;

	Locale locale = new Locale("pt", "BR");

	public void alert(AlertType alertType, String title, String header, String contentText,Exception ex, boolean print) {
		Alert alert = new Alert(alertType);
		alert.getDialogPane().setExpanded(true);
		alert.getDialogPane().setMinSize(400,150);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contentText);
		if(alert.getAlertType()==AlertType.ERROR && ex!=null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String exceptionText = sw.toString();
			Label label = new Label("The exception stacktrace was:");
			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);
			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);

			if(print) {
				try {
					LocalDateTime dateTime = LocalDateTime.now();
					File log = new File(System.getProperty("user.dir") + "/log/" +
							dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-erro.txt");
					if (!log.getParentFile().exists())
						log.getParentFile().mkdir();
					FileWriter fw = new FileWriter(log, true);
					String line = System.getProperty("line.separator");
					fw.write(
							dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "=" +
									header + ":" +
									contentText +
									line +
									exceptionText
					);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		alert.showAndWait();
	}

	public void comboRegiao(JFXComboBox<Cidade> cbCidade, JFXComboBox<Cidade.Estado> cbEstado, EntityManager manager){
		CidadesImpl cidades = new CidadesImpl(manager);
		Cidade cidade = cidades.findByNome("São Paulo");
		cbCidade.getItems().addAll(cidades.findByEstado(Cidade.Estado.SP));
		cbCidade.setValue(cidade);
		cbEstado.getItems().addAll(Cidade.Estado.values());
		cbEstado.setValue(Cidade.Estado.SP);
		cbEstado.valueProperty().addListener(new BuscaCep(cbCidade));
		new ComboBoxAutoCompleteUtil<>(cbCidade);
	}
	void bucarCep(MaskedTextField txCEP, JFXTextField txLogradouro, JFXTextField txNumero,JFXTextField txComplemento,
				  JFXTextField txBairro, JFXComboBox<Cidade> cbCidade, JFXComboBox<Cidade.Estado> cbEstado
				  ){
		try{
			loadFactory();
			EnderecoUtil util = EnderecoUtil.getInstance();
			if(txCEP.getPlainText().trim().length()==8) {
				Endereco endereco = util.pegarCEP(txCEP.getPlainText());
				if(endereco!=null){
					habilidarFiltroCidade = false;
					CidadesImpl cidades = new CidadesImpl(getManager());
					Cidade cidade = cidades.findByNome(endereco.getLocalidade());
					txLogradouro.setText(endereco.getLogradouro());
					txNumero.setText("");
					txComplemento.setText(endereco.getComplemento());
					txBairro.setText(endereco.getBairro());
					cbCidade.getItems().clear();

					List<Cidade> cidadeList = cidades.findByEstado(endereco.getUf());
					cbEstado.setValue(endereco.getUf());
					cbCidade.getItems().addAll(cidadeList);
					cbCidade.setValue(cidade);
					new ComboBoxAutoCompleteUtil<>(cbCidade);
					habilidarFiltroCidade = true;

				}
				else
					alert(Alert.AlertType.WARNING,"CEP Invalido",null,
							"Verifique se o cep informado é valido ou se existe uma conexão com a internet",null, false);
			}
			else{
				alert(Alert.AlertType.WARNING,"CEP Invalido",null,"Verifique o cep informado",null, false);
			}
		}catch(Exception e){
			alert(Alert.AlertType.ERROR,"Falha na conexão com o banco de dados",null,
					"Houve uma falha na conexão com o banco de dados",e,true);
		}finally {
			close();
		}
	}
	public Optional<String> cadastroRapido(){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Cadastro rapido");
		dialog.setHeaderText("Crie um novo registro:");
		dialog.setContentText("Por favor entre com um novo nome");
		return dialog.showAndWait();
	}

	public class BuscaCep implements ChangeListener<Cidade.Estado>{
		private JFXComboBox<Cidade> cbCidade;
		public BuscaCep(JFXComboBox<Cidade> cbCidade){
			this.cbCidade=cbCidade;
		}
		@Override
		public void changed(ObservableValue<? extends Cidade.Estado> observable, Cidade.Estado oldValue, Cidade.Estado newValue) {
			if (newValue != null && habilidarFiltroCidade) {
				try {
					loadFactory(getManager());
					CidadesImpl cidades = new CidadesImpl(getManager());
					cbCidade.getItems().clear();
					List<Cidade> listCidades = cidades.findByEstado(newValue);
					cbCidade.getItems().addAll(listCidades);
					cbCidade.getSelectionModel().selectFirst();
				} catch (Exception e) {
				} finally {
					close();
				}
			}
		}
	}
}
