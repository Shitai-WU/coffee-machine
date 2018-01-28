package main.java;

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

    public Amount addAmount(Amount another) {
        BigDecimal sum = value.add(another.getValue());
        return new Amount(sum);
    }

    public boolean isAmountInsuffient(Amount another) {
        return value.compareTo(another.getValue()) < 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Amount)) {
            return false;
        }

        Amount amount = (Amount) obj;

        return value.compareTo(amount.getValue()) == 0;
    }
}
