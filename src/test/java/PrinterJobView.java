import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class PrinterJobView extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane stackPane = new StackPane();

        imprimir();
    }

    public static void imprimir(){
        //ESCREVER TXT
        try{
            File arquivo = new File("teste.txt");
            if(arquivo.exists()){
                //se existir
                FileWriter arquivoTxt = new FileWriter(arquivo, true);
                PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
                //ACREDITO QUE SO PODE TER 42 CARACTERES
                arquivoTxt.write("==========================================");
                arquivoTxt.write("\n");
                arquivoTxt.write("              Nome da empresa              ");
                arquivoTxt.write("\n");
                arquivoTxt.write("===========================================");
                arquivoTxt.write("\n");
                arquivoTxt.write("********** NAO E DOCUMENTO FISCAL *********");
                arquivoTxt.write("\n");
                arquivoTxt.write("===========================================");
                arquivoTxt.write("\n");
                arquivoTxt.write("PRODUTO      QTDE      VALOR UN.      VALOR");
                arquivoTxt.write("\n");
                arquivoTxt.write(String.format("%7s  %3s  %9s  %6s",
                        "Pizza Calabresa",
                        "4",
                        "R$30,00",
                        "R$120,00"));
                arquivoTxt.write("\n");
                //dados da tabela
                /*for(int x = 0; x < tabelaConsumo.getRowCount(); x++){
                    linhasTxt.print(String.format("%-10.10s",tabelaConsumo.getModel().getValueAt(x, 1)));
                    linhasTxt.print(String.format("%7s     ",tabelaConsumo.getModel().getValueAt(x, 5)));
                    linhasTxt.print(String.format("%10s    ",tabelaConsumo.getModel().getValueAt(x, 4)));
                    linhasTxt.print(String.format("%7s    ",tabelaConsumo.getModel().getValueAt(x, 6)));
                    linhasTxt.println();
                }*/
                arquivoTxt.write("===========================================");
                arquivoTxt.write("\n");
                arquivoTxt.write("   INFORMACOES PARA FECHAMENTO DE CONTA    ");
                arquivoTxt.write("\n");
                arquivoTxt.write("===========================================");
                arquivoTxt.write("\n");
                arquivoTxt.write(" GARCOM  CONTA.DIV.  VAL.PESS. COVER  DESC.");
                arquivoTxt.write("\n");
                arquivoTxt.write(String.format("%7s  %9s  %9s  %5s   %s",
                        "Fabio",
                        "",
                        "R$12,00",
                        "R$10,00",
                        "R$2,00"
                        //garcom.getText(),
                        //dividirConta.getText(),
                        //valorPorPessoa.getText(),
                        //cover.getText(),
                        //descontoConta.getText()
                ));
                arquivoTxt.write("\n");
                arquivoTxt.write("===========================================");
                arquivoTxt.write("\n");
                //linhasTxt.println("SubTotal                            " + valorBruto.getText());
                arquivoTxt.write("SubTotal                            " + "120,00");
                arquivoTxt.write("\n");
                arquivoTxt.write("                   ------------------------");
                arquivoTxt.write("\n");
                //linhasTxt.println("Total                                " + valorBruto.getText());
                arquivoTxt.write("Total                                " + "140,00");
                arquivoTxt.write("\n");
                arquivoTxt.write("===========================================");
                arquivoTxt.write("\n");
                arquivoTxt.write("       MENSAGEM DA EMPRESA VAI AQUI        ");
                arquivoTxt.write("\n");
                arquivoTxt.write("   INFORMACOES PARA FECHAMENTO DE CONTA    ");
                arquivoTxt.write("\n");
                int i = 0;
                while(i < 10){
                    i++;
                    arquivoTxt.write("\n");
                }
                arquivoTxt.close();
                //emiteComanda();
            }else{
                //se naum existir
                arquivo.createNewFile();
                //criaTxt();
            }
        }catch(IOException error){
            System.out.println("nao encontrei arquivo");
        }
        // imprime arquivo
//        try {
//            java.io.InputStream is = new FileInputStream("teste.txt");
//            Scanner sc = new Scanner(is);
//            FileOutputStream fs = new FileOutputStream("LPT1:");
//            PrintStream ps = new PrintStream(fs);
//            while(sc.hasNextLine()){
//                String linhas = sc.nextLine();
//                ps.println(linhas);
//            }
//            fs.close();
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "Erro encontrado ao imprimir comanda.");
//        }

    }
    public static void main(String[] args){
        launch(args);
    }
}
