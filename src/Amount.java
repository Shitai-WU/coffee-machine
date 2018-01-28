import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {
    private final BigDecimal value;

    public static Amount fromString(String valueString) {
        BigDecimal value = new BigDecimal(valueString).setScale(2, RoundingMode.CEILING);
        return new Amount(value);
    }

    private Amount(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Amount calculateDifference(Amount another) {
        BigDecimal difference = value.subtract(another.getValue());
        return new Amount(difference);
    }

    public boolean isAmountInsuffient(Amount another) {
        return value.compareTo(another.getValue()) < 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
