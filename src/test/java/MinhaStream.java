import java.io.Serializable;
import java.math.BigDecimal;

public class MinhaStream implements Serializable{
    private int id;
    private BigDecimal decimal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
