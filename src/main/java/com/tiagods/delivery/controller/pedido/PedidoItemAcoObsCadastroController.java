package com.tiagods.delivery.controller.pedido;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.model.pedido.PedidoProduto;
import com.tiagods.delivery.repository.helper.ComplementosImpl;
import com.tiagods.delivery.repository.helper.ObservacaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PedidoItemAcoObsCadastroController extends UtilsController implements Initializable {
    @FXML
    private ListView<JFXCheckBox> lvComplemento;
    @FXML
    private ListView<JFXCheckBox> lvObservacao;

    private Stage stage;
    private PedidoProduto pedidoProduto;
    private ComplementosImpl complementos;
    private ObservacaoImpl observacao;

    public PedidoItemAcoObsCadastroController(PedidoProduto pedidoProduto,Stage stage){
        this.pedidoProduto=pedidoProduto;
        this.stage=stage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            super.loadFactory();
            complementos = new ComplementosImpl(super.getManager());
            observacao = new ObservacaoImpl(super.getManager());
            ProdutoCategoria categoria = pedidoProduto.getProduto().getCategoria();

            List<Complemento> complementoList = complementos.findByCategoria(categoria);
            List<Observacao> observacaoList = observacao.findByCategoria(categoria);

            complementoList.forEach(c->{
                JFXCheckBox ck = new JFXCheckBox(c.getNome());
                ck.setId(""+c.getId());
                lvComplemento.getItems().add(ck);
            });
            observacaoList.forEach(c->{
                JFXCheckBox ck = new JFXCheckBox(c.getNome());
                ck.setId(""+c.getId());
                lvObservacao.getItems().add(ck);
            });

        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,
                    "Erro ao preencher listas Complemento e Observacao",e,true);
        }finally {
            super.close();
        }

    }
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
}
