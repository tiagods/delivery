package com.tiagods.delivery.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.repository.helper.ComplementosImpl;
import com.tiagods.delivery.repository.helper.ProdutosCategoriasImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class ComplementoCadastroController extends UtilsController implements Initializable{
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
    private MaskTextField txCusto;
    @FXML
    private JFXButton btnSalvar;
    @FXML
    private JFXButton btnSair;
    @FXML
    private VBox vboxPanel;

    private Stage stage;
    private Complemento complemento;
    private ComplementosImpl complementos;
    private ProdutosCategoriasImpl categorias;
    private List<ProdutoCategoria> categoriaList;

    public ComplementoCadastroController(Complemento complemento, Stage stage) {
        this.stage = stage;
        this.complemento = complemento;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.Initializer(new JFXButton(), new JFXButton(), btnSalvar, new JFXButton(), new JFXButton(),btnSair);
        combos();
        if(complemento.getId()!=null)
            preencherFormulario(complemento);
    }
    private void combos(){
        vboxPanel.setSpacing(10);
        ckSelecionarTudo.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolean selecionado = ckSelecionarTudo.isSelected();

                ObservableList<Node> nodes = vboxPanel.getChildren();
                nodes.forEach(node->{
                    if(node instanceof JFXCheckBox)
                        ((JFXCheckBox) node).setSelected(selecionado);
                });
            }
        });
        try{
            super.loadFactory();
            categorias = new ProdutosCategoriasImpl(super.getManager());
            List<ProdutoCategoria> categoriaList = categorias.getAll();
            categoriaList.forEach(c->{
                JFXCheckBox checkBox = new JFXCheckBox(c.getNome());
                checkBox.setId(String.valueOf(c.getId()));
                vboxPanel.getChildren().add(checkBox);
            });
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro","Erro ao preencher combos","");
        }finally {
            super.close();
        }
    }
    void preencherFormulario(Complemento complemento) {
        txCodigo.setText(String.valueOf(complemento.getId()));
        txNome.setText(complemento.getNome());
        txCusto.setText(complemento.getValor().toString().replace(".",","));
        complemento.getCategorias().forEach(n->{
            ObservableList nodes = vboxPanel.getChildren();
            Stream<JFXCheckBox> stream = nodes.stream();
            Optional<JFXCheckBox> result = stream.filter(c->c.getId()==String.valueOf(n.getId())).findFirst();
            if(result.isPresent()) result.get().setSelected(true);
        });
        this.complemento = complemento;
    }


    @FXML
    void salvar(ActionEvent event) {
        try {
            super.loadFactory();
            complementos = new ComplementosImpl(super.getManager());
            complemento.setNome(txNome.getText());
            double d = Double.parseDouble(txCusto.getText().replace(",","."));
            BigDecimal bigDecimal = new BigDecimal(String.format("%.2f",d).replace(",","."));
            complemento.setValor(bigDecimal);
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
            complemento.setCategorias(produtoCategoriaSet);
            complementos.save(complemento);
            //super.desbloquear(false, pnCadastro.getChildren());
            stage.close();
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,
                    "Erro","Erro ao salvar o registro",
                    "Ocorreu um erro ao tentar salvar o registro"+e.getMessage());
        }finally {
            super.close();
        }
    }
    @FXML
    void sair(ActionEvent event){
        stage.close();
    }
}
