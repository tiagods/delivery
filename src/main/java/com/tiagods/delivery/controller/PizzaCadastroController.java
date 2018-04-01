package com.tiagods.delivery.controller;

import com.jfoenix.controls.*;
import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.model.ProdutoUnidade;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.repository.helper.PizzasImpl;
import com.tiagods.delivery.repository.helper.ProdutosCategoriasImpl;
import com.tiagods.delivery.repository.helper.ProdutosGenericosImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;

import java.net.URL;
import java.util.*;

public class PizzaCadastroController extends UtilsController implements Initializable{


    @FXML
    private AnchorPane pnCadastro;

    @FXML
    private JFXTextField txCodigo;

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txPersonalizado;

    @FXML
    private JFXTextArea txDescricao;

    @FXML
    private JFXTextArea txReceita;

    @FXML
    private Pane pnPizza;

    @FXML
    private MaskTextField txCurstoF;

    @FXML
    private MaskTextField txMargemF;

    @FXML
    private MaskTextField txVendaF;

    @FXML
    private JFXToggleButton tbFatia;

    @FXML
    private MaskTextField txCurstoP;

    @FXML
    private MaskTextField txMargemP;

    @FXML
    private MaskTextField txVendaP;

    @FXML
    private JFXToggleButton tgPequena;

    @FXML
    private MaskTextField txCustoM;

    @FXML
    private MaskTextField txMargemM;

    @FXML
    private MaskTextField txVendaM;

    @FXML
    private JFXToggleButton tgMedia;

    @FXML
    private JFXToggleButton tgGrande;

    @FXML
    private MaskTextField txCustoG;

    @FXML
    private MaskTextField txMargemG;

    @FXML
    private MaskTextField txVendaG;

    @FXML
    private Pane pnComum;

    @FXML
    private MaskTextField txCusto;

    @FXML
    private MaskTextField txMargem;

    @FXML
    private MaskTextField txVenda;

    @FXML
    private TableView<?> tbEstoque;

    @FXML
    private JFXComboBox<ProdutoCategoria> cbCategoria;

    @FXML
    private JFXComboBox<ProdutoUnidade> cbUnidade;

    @FXML
    private ImageView photoView;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnSair;

    private Stage stage;
    private Pizza pizza;
    private PizzasImpl pizzas;
    private ProdutosGenericosImpl genericos;
    private ProdutosCategoriasImpl categorias;

    public PizzaCadastroController(Pizza pizza, Stage stage) {
        this.stage = stage;
        this.pizza = pizza;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.Initializer(new JFXButton(), new JFXButton(), btnSalvar, new JFXButton(), new JFXButton(),btnSair);
        combos();
        if(pizza!=null)
            preencherFormulario(pizza);
    }

    @FXML
    void anexarFoto(ActionEvent event) {

    }
    private void combos(){
        try{
            super.loadFactory();
            categorias = new ProdutosCategoriasImpl(super.getManager());
            List<ProdutoCategoria> categoriaList = categorias.getAll();
            cbCategoria.getItems().addAll(categoriaList);
            cbUnidade.getItems().addAll(ProdutoUnidade.values());
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro","Erro ao preencher combos","");
        }finally {
            super.close();
        }
    }
    void preencherFormulario(Pizza pizza) {
        txNome.setText("");
        //txCodigo.setText(produto.getId()!=null?String.valueOf(produto.getId()):"");
        //txNome.setText(produto.getNome());
        this.pizza = pizza;
    }
    @FXML
    void removerFoto(ActionEvent event) {

    }
    @FXML
    void salvar(ActionEvent event) {
        try {
            super.loadFactory();

            stage.close();
            //super.desbloquear(false, pnCadastro.getChildren());
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
