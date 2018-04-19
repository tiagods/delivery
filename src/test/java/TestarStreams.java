import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TestarStreams {
    public static void main(String[] args){

        BigDecimal bigDecimal = new BigDecimal(10.00);
        BigDecimal bigDecimal1 = new BigDecimal(3.00);

        PaiStream paiStream = new PaiStream();
        paiStream.setMinhaStreams(getStream());

        PaiStream paiStream2 = new PaiStream();
        paiStream2.setMinhaStreams(getStream());

        Set<PaiStream> paiStreams = new HashSet<>();
        paiStreams.add(paiStream);
        paiStreams.add(paiStream2);

        Set<Double> result = paiStreams.stream()
                .map(c->c.getMinhaStreams().stream()
                        .map(MinhaStream::getDecimal)
                        .mapToDouble(BigDecimal::doubleValue).sum())
                .collect(Collectors.toSet());

        double resultado = result.stream().mapToDouble(Double::doubleValue).sum();

        System.out.println(resultado);
    }
    public static Set<MinhaStream> getStream(){
        int quant = 10;
        Set<MinhaStream> minhaStreams=new HashSet<>();
        for(int i=1; i<=10; i++){
            Random r = new Random();
            MinhaStream stream = new MinhaStream();
            stream.setDecimal(new BigDecimal(r.nextInt(100)));
            stream.setId(i);
            minhaStreams.add(stream);

            System.out.println(stream.getDecimal());
        }
        return minhaStreams;

    }
}
