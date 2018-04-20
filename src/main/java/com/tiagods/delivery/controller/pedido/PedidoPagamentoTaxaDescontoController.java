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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fxutils.maskedtextfield.MaskTextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


public class PedidoPagamentoTaxaDescontoController extends UtilsController implements Initializable {
    @FXML
    private TextField txDesconto;

    @FXML
    private TextField txServico;

    @FXML
    private TextField txTaxaEntrega;

    private Stage stage;
    private Pedido pedido;

    public PedidoPagamentoTaxaDescontoController(Pedido pedido,Stage stage){
        this.stage = stage;
        this.pedido = pedido;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txDesconto.setText(String.valueOf(pedido.getDesconto().doubleValue()).replace(".",","));
        txServico.setText(String.valueOf(pedido.getServico().doubleValue()).replace(".",","));
        txTaxaEntrega.setText("0,00");
        if(pedido instanceof PedidoDelivery)
            txTaxaEntrega.setText(
                    String.valueOf(((PedidoDelivery) pedido).getValorTaxa().doubleValue()).replace(".",","));
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
        double desconto = 0;
        double servico = 0;
        double taxa = 0;

        try {
            desconto = Double.parseDouble(txDesconto.getText().replace(",",".").trim());
            servico = Double.parseDouble(txServico.getText().replace(",",".").trim());
            taxa = Double.parseDouble(txTaxaEntrega.getText().replace(",",".").trim());
        } catch (NumberFormatException e) {
            super.alert(Alert.AlertType.ERROR,"Erro",null,
                    "Verifique os valores informados", null, false);
            return;
        }
        try{
            super.loadFactory();
            pedido.setDesconto(new BigDecimal(desconto));
            pedido.setServico(new BigDecimal(servico));
            double subTotal = pedido.getSubTotal().doubleValue();

            double total = subTotal + servico - (desconto);
            pedido.setTotal(new BigDecimal(total));

            if(pedido instanceof PedidoDelivery) {
                PedidosDeliveryImpl pedidosDelivery = new PedidosDeliveryImpl(super.getManager());
                if(!txTaxaEntrega.isDisable())
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
