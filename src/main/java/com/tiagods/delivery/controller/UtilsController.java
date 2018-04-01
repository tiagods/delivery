package com.tiagods.delivery.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.fxutils.maskedtextfield.MaskTextField;
import org.fxutils.maskedtextfield.MaskedTextField;

public abstract class UtilsController extends PersistenciaController{
	private JFXButton buttonNovo;
	private JFXButton buttonEditar;
	private JFXButton buttonSalvar;
	private JFXButton buttonExcluir;
	private JFXButton buttonCancelar;
	private JFXButton buttonSair;
	
	public void Initializer(JFXButton btnNovo, JFXButton btnEditar, 
			JFXButton btnSalvar, JFXButton btnExcluir,JFXButton btnCancelar, JFXButton btnSair) {
		this.buttonNovo=btnNovo;
		this.buttonEditar=btnEditar;
		this.buttonSalvar=btnSalvar;
		this.buttonExcluir=btnExcluir;
		this.buttonCancelar=btnCancelar;
		this.buttonNovo.setOnMouseClicked(new NovoEditar());
		this.buttonEditar.setOnMouseClicked(new NovoEditar());
		this.buttonSalvar.setOnMouseClicked(new Salvar());
		this.buttonExcluir.setOnMouseClicked(new Excluir());
		this.buttonCancelar.setOnMouseClicked(new Cancelar());
	}
	public void alert(AlertType alertType, String title, String header, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contentText);
		alert.show();
		if(alert.getAlertType()==AlertType.ERROR) {
			try {
				FileWriter fw = new FileWriter(System.getProperty("user.dir"), true);
			}catch (IOException e) {
				// TODO: handle exception
			}
		}
	}
	public class NovoEditar implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			buttonNovo.setDisable(true);
			buttonEditar.setDisable(true);
			buttonSalvar.setDisable(false);
			buttonExcluir.setDisable(true);
			buttonCancelar.setDisable(false);
		}
		
	}
	public class Excluir implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			buttonNovo.setDisable(false);
			buttonEditar.setDisable(false);
			buttonSalvar.setDisable(true);
			buttonExcluir.setDisable(false);
			buttonCancelar.setDisable(true);	
		}		
	}
	public class Salvar implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			buttonNovo.setDisable(false);
			buttonEditar.setDisable(false);
			buttonSalvar.setDisable(true);
			buttonExcluir.setDisable(false);
			buttonCancelar.setDisable(true);	
		}
	}
	public class Cancelar implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			buttonNovo.setDisable(false);
			buttonEditar.setDisable(false);
			buttonSalvar.setDisable(true);
			buttonExcluir.setDisable(false);
			buttonCancelar.setDisable(true);	
		}
	}
	public void desbloquear(boolean value, ObservableList<Node> nodes) {
		nodes.forEach((n) -> {
			if (n instanceof JFXTextField)
				((JFXTextField) n).setEditable(value);
			else if (n instanceof MaskTextField)
				((MaskTextField) n).setEditable(value);
			else if (n instanceof MaskedTextField)
				((MaskedTextField) n).setEditable(value);
			else if (n instanceof JFXTextArea)
				((JFXTextArea) n).setEditable(value);
			else if (n instanceof TextArea) 
				((TextArea)n).setEditable(value);
			else if (n instanceof JFXComboBox
					||n instanceof ComboBox
					||n instanceof TableView
					||n instanceof JFXCheckBox
					||n instanceof JFXDatePicker
					||n instanceof JFXRadioButton
					) 
				n.setDisable(!value);			
//			else if (n instanceof JFXComboBox) {
//				((JFXComboBox) n).setDisable(!value);
//			} else if (n instanceof ComboBox) {
//				((ComboBox) n).setDisable(!value);
//			} else if (n instanceof TableView) {
//				((TableView) n).setDisable(!value);
//			} else if (n instanceof JFXCheckBox) {
//				((JFXCheckBox) n).setDisable(!value);
//			} else if (n instanceof JFXDatePicker) {
//				((JFXDatePicker) n).setDisable(!value);
//			} else if (n instanceof JFXRadioButton) {
//				((JFXRadioButton) n).setDisable(!value);
//			} 
			else if (n instanceof Pane)
				desbloquear(value, ((Pane) n).getChildren());
			else if (n instanceof AnchorPane) 
				desbloquear(value, ((AnchorPane) n).getChildren());
			else if (n instanceof JFXTabPane) {
				ObservableList<Tab> tabs = ((JFXTabPane) n).getTabs();
				tabs.forEach(c -> {
					if (c.getContent() instanceof AnchorPane)
						desbloquear(value, ((AnchorPane) c.getContent()).getChildren());
				});
			} else if (n instanceof Accordion) {
				ObservableList<TitledPane> panes = ((Accordion) n).getPanes();
				panes.forEach(c -> desbloquear(value, c.getChildrenUnmodifiable()));
			}
		});
	}
	public void limpar(ObservableList<Node> nodes) {
		nodes.forEach((n) -> {
			if (n instanceof JFXTextField) {
				((JFXTextField) n).setText("");
			} else if (n instanceof MaskTextField) {
				((MaskTextField) n).setText("");
			} else if (n instanceof MaskedTextField) {
				((MaskedTextField) n).setText("");
			} else if (n instanceof JFXComboBox) {
				((JFXComboBox<?>) n).getSelectionModel().clearSelection();
			} else if (n instanceof ComboBox) {
				((ComboBox<?>) n).getSelectionModel().clearSelection();
			} else if (n instanceof JFXTextArea) {
				((JFXTextArea) n).setText("");
			} else if (n instanceof TableView) {
				((TableView<?>) n).getItems().clear();
			} else if (n instanceof JFXCheckBox) {
				((JFXCheckBox) n).setSelected(false);
			} else if (n instanceof JFXRadioButton) {
				((JFXRadioButton) n).setSelected(false);
			} else if (n instanceof JFXDatePicker) {
				((JFXDatePicker) n).setValue(null);
			} else if (n instanceof Pane) {
				limpar(((Pane) n).getChildren());
			} else if (n instanceof AnchorPane) {
				limpar(((AnchorPane) n).getChildren());
			} else if (n instanceof Accordion) {
				ObservableList<TitledPane> titledPanes = ((Accordion) n).getPanes();
				titledPanes.forEach(c -> {
					limpar(c.getChildrenUnmodifiable());
				});
			} else if (n instanceof JFXTabPane) {
				ObservableList<Tab> tabs = ((JFXTabPane) n).getTabs();
				tabs.forEach(c -> {
					if (c.getContent() instanceof AnchorPane)
						limpar(((AnchorPane) c.getContent()).getChildren());
				});
			} else if (n instanceof Accordion) {
				ObservableList<TitledPane> panes = ((Accordion) n).getPanes();
				panes.forEach(c -> limpar(c.getChildrenUnmodifiable()));
			}
		});
	}
}
