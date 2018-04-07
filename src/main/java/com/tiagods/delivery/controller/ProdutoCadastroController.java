package com.tiagods.delivery.controller;

import com.jfoenix.controls.*;
import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.model.ProdutoUnidade;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.model.produto.pizza.PizzaFatia;
import com.tiagods.delivery.model.produto.pizza.PizzaGrande;
import com.tiagods.delivery.model.produto.pizza.PizzaMedia;
import com.tiagods.delivery.model.produto.pizza.PizzaPequena;
import com.tiagods.delivery.repository.helper.PizzasImpl;
import com.tiagods.delivery.repository.helper.ProdutosCategoriasImpl;
import com.tiagods.delivery.repository.helper.ProdutosGenericosImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProdutoCadastroController extends UtilsController implements Initializable{
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
    private MaskTextField txCustoF;

    @FXML
    private MaskTextField txMargemF;

    @FXML
    private MaskTextField txVendaF;

    @FXML
    private JFXToggleButton tgFatia;

    @FXML
    private MaskTextField txCustoP;

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
    Locale locale = new Locale("pt", "BR");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    private Stage stage;
    private Produto produto;
    private PizzasImpl pizzas;
    private ProdutosGenericosImpl genericos;
    private ProdutosCategoriasImpl categorias;

    public ProdutoCadastroController(Produto produto, Stage stage) {
        this.stage = stage;
        this.produto = produto;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.Initializer(new JFXButton(), new JFXButton(), btnSalvar, new JFXButton(), new JFXButton(),btnSair);
        combos();
        if(produto.getId()!=null)
            preencherFormulario(produto);
    }

    @FXML
    void anexarFoto(ActionEvent event) {

    }
    private void combos(){
        txMargemF.setDisable(true);
        txMargemP.setDisable(true);
        txMargemM.setDisable(true);
        txMargemG.setDisable(true);

        ChangeListener<Boolean> changeListener = (observable, oldValue, newValue) -> {
            txCustoF.setDisable(!tgFatia.isSelected());
//            txMargemF.setDisable(!tgFatia.isSelected());
            txVendaF.setDisable(!tgFatia.isSelected());

            txCustoP.setDisable(!tgPequena.isSelected());
//            txMargemP.setDisable(!tgPequena.isSelected());
            txVendaP.setDisable(!tgPequena.isSelected());

            txCustoM.setDisable(!tgMedia.isSelected());
//            txMargemM.setDisable(!tgMedia.isSelected());
            txVendaM.setDisable(!tgMedia.isSelected());

            txCustoG.setDisable(!tgGrande.isSelected());
//            txMargemG.setDisable(!tgGrande.isSelected());
            txVendaG.setDisable(!tgGrande.isSelected());
        };
        tgFatia.selectedProperty().addListener(changeListener);
        tgPequena.selectedProperty().addListener(changeListener);
        tgMedia.selectedProperty().addListener(changeListener);
        tgGrande.selectedProperty().addListener(changeListener);
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
    private void preencherFormulario(Produto produto) {
        txCodigo.setText(String.valueOf(produto.getId()));
        txNome.setText(produto.getNome());
        txPersonalizado.setText(produto.getPersonalizado());
        cbCategoria.setValue(produto.getCategoria());
        cbUnidade.setValue(produto.getUnidade());
        txDescricao.setText(produto.getDescricao());
        txReceita.setText(produto.getReceita());
        if(produto instanceof Pizza) {
            pnPizza.setVisible(true);
            pnComum.setVisible(false);

            tgFatia.setSelected(((Pizza) produto).isFatiaHabilitada());
            tgPequena.setSelected(((Pizza) produto).isPequenaHabilitada());
            tgMedia.setSelected(((Pizza) produto).isMediaHabilitada());
            tgGrande.setSelected(((Pizza) produto).isGrandeHabilitada());

            if(((Pizza) produto).isFatiaHabilitada()){
                txCustoF.setText(((Pizza) produto).getFatia().getCustoFatia().toPlainString().replace(".",","));
                txMargemF.setText(((Pizza) produto).getFatia().getMargemFatia()+"");
                txVendaF.setText(((Pizza) produto).getFatia().getVendaFatia().toPlainString().replace(".",","));
            }
            if(((Pizza) produto).isPequenaHabilitada()){
                txCustoP.setText(((Pizza) produto).getPequena().getCustoPequeno().toPlainString().replace(".",","));
                txMargemP.setText(((Pizza) produto).getPequena().getMargemPequeno()+"");
                txVendaP.setText(((Pizza) produto).getPequena().getVendaPequeno().toPlainString().replace(".",","));
            }
            if(((Pizza) produto).isMediaHabilitada()){
                txCustoM.setText(((Pizza) produto).getMedia().getCustoMedia().toPlainString().replace(".",","));
                txMargemM.setText(((Pizza) produto).getMedia().getMargemMedia()+"");
                txVendaM.setText(((Pizza) produto).getMedia().getVendaMedia().toPlainString().replace(".",","));
            }
            if(((Pizza) produto).isGrandeHabilitada()){
                txCustoG.setText(((Pizza) produto).getGrande().getCustoGrande().toPlainString().replace(".",","));
                txMargemG.setText(((Pizza) produto).getGrande().getMargemGrande()+"");
                txVendaG.setText(((Pizza) produto).getGrande().getVendaGrande().toPlainString().replace(".",","));
            }
        }
        else if(produto instanceof ProdutoGenerico){
            pnPizza.setVisible(false);
            pnComum.setVisible(true);
            txCusto.setText(((ProdutoGenerico) produto).getCusto().toPlainString().replace(".",","));
            txMargem.setText(""+((ProdutoGenerico) produto).getMargem());
            txVenda.setText(((ProdutoGenerico) produto).getVenda().toPlainString().replace(".",","));
        }
        this.produto = produto;
    }
    @FXML
    void removerFoto(ActionEvent event) {

    }
    private BigDecimal[] prePersistValores(MaskTextField txCusto, MaskTextField txMargem, MaskTextField txVenda){
        double custo=txCusto.getText().replace(",","").trim().equals("")?
                0.00:Double.parseDouble(txCusto.getText().replace(",",".").trim());
        double margem=txMargem.getText().equals("")?0.00:Double.parseDouble(txMargem.getText());
        double venda=txVenda.getText().replace(",","").trim().equals("")?
                0.00:Double.parseDouble(txVenda.getText().replace(",",".").trim());
        BigDecimal[] bigDecimal = new BigDecimal[3];
        bigDecimal[0]=new BigDecimal(String.format("%.2f",custo).replace(",","."));
        bigDecimal[1]=new BigDecimal(String.format("%.2f",margem).replace(",","."));
        bigDecimal[2]=new BigDecimal(String.format("%.2f",venda).replace(",","."));
        return bigDecimal;
    }

    @FXML
    void salvar(ActionEvent event) {
        try {
            super.loadFactory();
            txCodigo.setText(String.valueOf(produto.getId()));
            txNome.setText(produto.getNome());
            txPersonalizado.setText(produto.getPersonalizado());
            cbCategoria.setValue(produto.getCategoria());
            cbUnidade.setValue(produto.getUnidade());
            txDescricao.setText(produto.getDescricao());
            txReceita.setText(produto.getReceita());
            BigDecimal[] bigDecimal = null;
            if(produto instanceof ProdutoGenerico){
                if(!validarDigitacao(txCusto,txVenda)) return;
                bigDecimal = prePersistValores(txCusto,txMargem,txVenda);
                ((ProdutoGenerico) produto).setCusto(bigDecimal[0]);
                ((ProdutoGenerico) produto).setMargem(bigDecimal[1]);
                ((ProdutoGenerico) produto).setVenda(bigDecimal[2]);
                genericos = new ProdutosGenericosImpl(super.getManager());
                genericos.save((ProdutoGenerico)produto);
            }
            else if(produto instanceof Pizza){
                PizzaFatia pizzaFatia = new PizzaFatia();
                PizzaPequena pizzaPequena = new PizzaPequena();
                PizzaMedia pizzaMedia = new PizzaMedia();
                PizzaGrande pizzaGrande = new PizzaGrande();

                if(tgFatia.isSelected()){
                    if(!validarDigitacao(txCustoF,txVendaF)) return;
                    bigDecimal = prePersistValores(txCustoF,txMargemF,txVendaF);
                    pizzaFatia.setCustoFatia(bigDecimal[0]);
                    pizzaFatia.setMargemFatia(bigDecimal[1]);
                    pizzaFatia.setVendaFatia(bigDecimal[2]);
                }
                if (tgPequena.isSelected()) {
                    if(!validarDigitacao(txCustoP,txVendaP)) return;
                    bigDecimal = prePersistValores(txCustoP,txMargemP,txVendaP);
                    pizzaPequena.setCustoPequeno(bigDecimal[0]);
                    pizzaPequena.setMargemPequeno(bigDecimal[1]);
                    pizzaPequena.setVendaPequeno(bigDecimal[2]);
                }
                if (tgMedia.isSelected()) {
                    if(!validarDigitacao(txCustoM,txVendaM)) return;
                    bigDecimal = prePersistValores(txCustoM,txMargemM,txVendaM);
                    pizzaMedia.setCustoMedia(bigDecimal[0]);
                    pizzaMedia.setMargemMedia(bigDecimal[1]);
                    pizzaMedia.setVendaMedia(bigDecimal[2]);
                }

                if (tgGrande.isSelected()) {
                    if(!validarDigitacao(txCustoG,txVendaG)) return;
                    bigDecimal = prePersistValores(txCustoG,txMargemG,txVendaG);
                    pizzaGrande.setCustoGrande(bigDecimal[0]);
                    pizzaGrande.setMargemGrande(bigDecimal[1]);
                    pizzaGrande.setVendaGrande(bigDecimal[2]);
                }
                ((Pizza) produto).setFatiaHabilitada(tgFatia.isSelected());
                ((Pizza) produto).setPequenaHabilitada(tgPequena.isSelected());
                ((Pizza) produto).setMediaHabilitada(tgMedia.isSelected());
                ((Pizza) produto).setGrandeHabilitada(tgGrande.isSelected());

                ((Pizza) produto).setFatia(pizzaFatia);
                ((Pizza) produto).setPequena(pizzaPequena);
                ((Pizza) produto).setMedia(pizzaMedia);
                ((Pizza) produto).setGrande(pizzaGrande);

                pizzas = new PizzasImpl(super.getManager());
                pizzas.save((Pizza)produto);
            }
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

    boolean validarDigitacao(MaskTextField custo, MaskTextField venda){
        if(venda.getText().replace(",","").trim().equals("")){
            super.alert(Alert.AlertType.WARNING,"Informação incorreta","Valor da venda é obrigatorio","O valor da venda deve ser informado!");
            return false;
        }
        if(custo.getText().replace(",","").trim().equals("")){
            super.alert(Alert.AlertType.WARNING,"Informação incorreta","Valor do custo é obrigatorio","O valor do custo esta incorreto!");
            return false;
        }
        return true;

    }
}
