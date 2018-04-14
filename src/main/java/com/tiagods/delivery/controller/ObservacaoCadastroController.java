package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.repository.helper.ObservacaoImpl;
import com.tiagods.delivery.repository.helper.ProdutosCategoriasImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ObservacaoCadastroController extends UtilsController implements Initializable{
    @FXML
    private AnchorPane pnPrincipal;
    @FXML
    private AnchorPane pnCadastro;
    @FXML
    private JFXTextField txCodigo;
    @FXML
    private JFXTextField txNome;
    @FXML
    private JFXCheckBox ckSelecionarTudo;
    @FXML
    private JFXButton btnSalvar;
    @FXML
    private JFXButton btnSair;
    @FXML
    private VBox vboxPanel;

    private Stage stage;
    private Observacao observacao;
    private ObservacaoImpl observacoes;
    private ProdutosCategoriasImpl categorias;
    private List<ProdutoCategoria> categoriaList;
    public ObservacaoCadastroController(Observacao observacao, Stage stage) {
        this.stage = stage;
        this.observacao = observacao;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combos();
        if(observacao.getId()!=null)
            preencherFormulario(observacao);
    }
    private void combos(){
        vboxPanel.setSpacing(10);
        ckSelecionarTudo.selectedProperty().addListener((observable, oldValue, newValue) -> {
            vboxPanel.getChildren().forEach(node->{
                if(node instanceof JFXCheckBox)
                    ((JFXCheckBox) node).setSelected(ckSelecionarTudo.isSelected());
            });
        });
        try{
            super.loadFactory();
            categorias = new ProdutosCategoriasImpl(super.getManager());
            List<ProdutoCategoria> categoriaList = categorias.getAll();
            categoriaList.forEach(c->{
                JFXCheckBox checkBox = new JFXCheckBox(c.getNome());
                checkBox.setId(String.valueOf(c.getId().longValue()));
                vboxPanel.getChildren().add(checkBox);
            });
            if(observacao.getId()!=null)
                preencherFormulario(observacao);
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,
                    "Erro ao preencher combos",e,true);
        }finally {
            super.close();
        }
    }
    void preencherFormulario(Observacao observacao) {
        txCodigo.setText(String.valueOf(observacao.getId()));
        txNome.setText(observacao.getNome());
        observacao.getCategorias().forEach(n->{
            ObservableList nodes = vboxPanel.getChildren();
            Stream<JFXCheckBox> stream = nodes.stream();
            Optional<JFXCheckBox> result = stream.filter(
                    c->c.getId().equals(String.valueOf(n.getId().longValue())))
                    .findFirst();
            if(result.isPresent()) result.get().setSelected(true);
        });
        this.observacao = observacao;
    }


    @FXML
    void salvar(ActionEvent event) {
        try {
            super.loadFactory();
            observacoes = new ObservacaoImpl(super.getManager());
            observacao.setNome(txNome.getText());
            Set<ProdutoCategoria> produtoCategoriaSet = new HashSet<>();
            vboxPanel.getChildren().forEach(node -> {
                if(node instanceof JFXCheckBox){
                    if (((JFXCheckBox) node).isSelected()){
                        ProdutoCategoria produtoCategoria = new ProdutoCategoria();
                        produtoCategoria.setId(Long.parseLong(node.getId()));
                        produtoCategoria.setNome(((JFXCheckBox) node).getText());
                        produtoCategoriaSet.add(produtoCategoria);
                    }
                }
            });
            observacao.setCategorias(produtoCategoriaSet);
            observacoes.save(observacao);
            alert(Alert.AlertType.INFORMATION,"Sucesso",null,
                    "Salvo com sucesso",null,false);
            stage.close();
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,
                    "Erro","Erro ao salvar o registro",
                    "Ocorreu um erro ao tentar salvar o registro",e,true);
        }finally {
            super.close();
        }
    }
    @FXML
    void sair(ActionEvent event){
        stage.close();
    }
}
