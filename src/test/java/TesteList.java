import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXComboBoxListViewSkin;
import com.jfoenix.skins.JFXRadioButtonSkin;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class TesteList extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        JFXButton button = new JFXButton("Press Me");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog dialog = new Dialog();
                dialog.setTitle("Alteração de Status");
                dialog.setHeaderText("Selecione um status");

                VBox stackPane = new VBox();
                JFXRadioButton rd = new JFXRadioButton("This Radio");
                rd.setSelected(true);
                JFXRadioButton rd2 = new JFXRadioButton("This Radio");
                rd2.setSelected(true);
                stackPane.getChildren().addAll(rd,rd2);

                ButtonType ok = new ButtonType("Alterar");
                ButtonType cancelar = new ButtonType("Cancelar");
                dialog.getDialogPane().getButtonTypes().addAll(ok,cancelar);
                dialog.getDialogPane().setContent(stackPane);
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> result = dialog.showAndWait();
                if(result.get() == ButtonType.OK){

                }
            }
        });

        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

    }
}
