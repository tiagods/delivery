import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TestConversion {
    public static void main(String[] args){
        String amount = "200,9";
        double d = Double.parseDouble(amount.replace(",","."));
        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        System.out.println(currencyFormatter.format(d));
        System.out.println(String.format("%.2f",d));
        BigDecimal bigDecimal = new BigDecimal(String.format("%.2f",d).replace(",","."));
        System.out.println(bigDecimal.toPlainString());


    }
}
