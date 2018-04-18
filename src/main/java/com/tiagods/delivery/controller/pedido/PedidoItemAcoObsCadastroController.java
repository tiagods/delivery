package com.tiagods.delivery.controller.pedido;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.model.pedido.PedidoProduto;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoProdutoItemAdicional;
import com.tiagods.delivery.repository.helper.ComplementosImpl;
import com.tiagods.delivery.repository.helper.ObservacaoImpl;
import com.tiagods.delivery.repository.helper.PedidosProdutosItensAddImpl;
import com.tiagods.delivery.repository.helper.PedidosProdutosItensImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class PedidoItemAcoObsCadastroController extends UtilsController implements Initializable {
    @FXML
    private ListView<Object> lvComplemento;
    @FXML
    private ListView<Object> lvObservacao;

    Locale locale = new Locale("pt", "BR");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private Stage stage;
    private PedidoProduto pedidoProduto;
    private ComplementosImpl complementos;
    private ObservacaoImpl observacao;
    private PedidosProdutosItensImpl items;

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

            Set<Complemento> complementoSet= new HashSet<>();
            Set<Observacao> observacaosSet = new HashSet<>();

            if(pedidoProduto instanceof PedidoProdutoItem){
                complementoSet= ((PedidoProdutoItem) pedidoProduto).getComplementos();
                observacaosSet = ((PedidoProdutoItem) pedidoProduto).getObservacoes();
            }
            else if(pedidoProduto instanceof PedidoProdutoItemAdicional){
                complementoSet= ((PedidoProdutoItemAdicional)pedidoProduto).getComplementos();
                observacaosSet= ((PedidoProdutoItemAdicional) pedidoProduto).getObservacoes();
            }

            ProdutoCategoria categoria = pedidoProduto.getProduto().getCategoria();
            List<Complemento> complementoList = complementos.findByCategoria(categoria);
            List<Observacao> observacaoList = observacao.findByCategoria(categoria);
            for(Complemento c : complementoList){
                JFXCheckBox ck = new JFXCheckBox(c.getNome()+" - ("+currencyFormatter.format(c.getValor())+")");
                ck.setId(""+c.getId());
                if(complementoSet.contains(c)) ck.setSelected(true);
                ck.selectedProperty().addListener(new CliqueGeral());
                lvComplemento.getItems().add(ck);
            }
            for(Observacao c : observacaoList){
                JFXCheckBox ck = new JFXCheckBox(c.getNome());
                ck.setId(""+c.getId());
                if(observacaosSet.contains(c)) ck.setSelected(true);
                ck.selectedProperty().addListener(new CliqueGeral());
                lvObservacao.getItems().add(ck);
            }
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            if(lvComplemento.getItems().size()==0){
                label.setText("Nenhum complemento");
                lvComplemento.getItems().add(label);
            }
            if(lvObservacao.getItems().size()==0){
                label.setText("Nenhuma observação");
                lvObservacao.getItems().add(label);
            }

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

    private class CliqueGeral implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            Set<Complemento> comList = new HashSet<>();
            lvComplemento.getItems().forEach(c->{
                if(c instanceof JFXCheckBox && ((JFXCheckBox)c).isSelected()){
                    Complemento com = new Complemento();
                    com.setId(Long.parseLong(((JFXCheckBox)c).getId()));
                    comList.add(com);
                }
            });
            Set<Observacao> obsList = new HashSet<>();
            lvObservacao.getItems().forEach(c->{
                if(c instanceof JFXCheckBox && ((JFXCheckBox)c).isSelected()){
                    Observacao obs = new Observacao();
                    obs.setId(Long.parseLong(((JFXCheckBox)c).getId()));
                    obsList.add(obs);
                }
            });
            try{
                loadFactory();
                if(pedidoProduto instanceof PedidoProdutoItem) {
                    items = new PedidosProdutosItensImpl(getManager());
                    ((PedidoProdutoItem) pedidoProduto).setComplementos(comList);
                    ((PedidoProdutoItem) pedidoProduto).setObservacoes(obsList);

                    items.save((PedidoProdutoItem) pedidoProduto);
                }
            }catch (Exception e){
                alert(Alert.AlertType.ERROR,"Erro",null,
                        "Erro ao salvar",e,true);
            }finally {
                close();
            }
        }
    }
}
