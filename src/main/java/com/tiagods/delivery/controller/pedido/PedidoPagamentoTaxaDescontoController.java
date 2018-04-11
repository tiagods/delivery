package com.tiagods.delivery.controller.pedido;

import com.tiagods.delivery.controller.UtilsController;
import com.tiagods.delivery.model.Pedido;
import com.tiagods.delivery.model.pedido.PedidoCaixa;
import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.repository.helper.PedidosCaixaImpl;
import com.tiagods.delivery.repository.helper.PedidosDeliveryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


public class PedidoPagamentoTaxaDescontoController extends UtilsController implements Initializable {
    @FXML
    private MaskTextField txDesconto;

    @FXML
    private MaskTextField txServico;

    @FXML
    private MaskTextField txTaxaEntrega;

    private Stage stage;
    private Pedido pedido;

    public PedidoPagamentoTaxaDescontoController(Pedido pedido,Stage stage){
        this.stage = stage;
        this.pedido = pedido;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txDesconto.setText(pedido.getDesconto().toString().replace(".",","));
        txServico.setText(pedido.getServico().toString().replace(".",","));
        if(pedido instanceof PedidoDelivery)
            txTaxaEntrega.setText(((PedidoDelivery) pedido).getValorTaxa().toString().replace(".",","));
        else
            txTaxaEntrega.setDisable(true);
    }
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    @FXML
    void salvar(ActionEvent event) {
        if (!validar(txDesconto.getText())) {
            super.alert(Alert.AlertType.ERROR,"Erro", null, "Desconto incorreto!", null, false);
            return;
        }
        if (!validar(txServico.getText())){
            super.alert(Alert.AlertType.ERROR,"Erro", null, "Serviço incorreto!", null, false);
            return;
        }
        try{
            super.loadFactory();

            double desconto = Double.parseDouble(txDesconto.getText().replace(",",".").trim());
            double servico = Double.parseDouble(txServico.getText().replace(",",".").trim());
            double taxa = Double.parseDouble(txTaxaEntrega.getText().replace(",",".").trim());

            pedido.setDesconto(new BigDecimal(desconto));
            pedido.setServico(new BigDecimal(servico));
            double subTotal = pedido.getSubTotal().doubleValue();

            double total = subTotal + servico - (desconto);
            pedido.setTotal(new BigDecimal(total));

            if(pedido instanceof PedidoDelivery) {
                PedidosDeliveryImpl pedidosDelivery = new PedidosDeliveryImpl(super.getManager());
                ((PedidoDelivery) pedido).setValorTaxa(new BigDecimal(taxa));
                total = total + taxa;
                if(taxa!=0.00) ((PedidoDelivery) pedido).setTaxa(null);
                pedido.setTotal(new BigDecimal(total));
                pedidosDelivery.save((PedidoDelivery)pedido);
            }
            else{
                PedidosCaixaImpl pedidosCaixa = new PedidosCaixaImpl(super.getManager());
                pedidosCaixa.save((PedidoCaixa)pedido);
            }
            stage.close();
        }catch (Exception e){
            super.alert(Alert.AlertType.ERROR,"Erro",null,
                    "Erro ao salvar o registro", e, true);
        }finally {
            super.close();
        }

    }
    boolean validar(String valor){
        return !valor.replace(",","").trim().equals("");
    }
}
