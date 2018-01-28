public class Command {
    private final DrinkType drinkType;
    private final SweetnessLevel sweetnessLevel;
    private final boolean hasStick;
    private Amount amount;
    private String message = "";

    public Command(DrinkType drinkType, SweetnessLevel sweetnessLevel) {
        this.drinkType = drinkType;
        this.sweetnessLevel = sweetnessLevel;
        this.hasStick = sweetnessLevel != SweetnessLevel.SUGAR_FREE;
        this.amount = Amount.fromString(drinkType.getPrice());
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public SweetnessLevel getSweetnessLevel() {
        return sweetnessLevel;
    }

    public boolean hasStick() {
        return hasStick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void pay(Amount paidAmount) {
        if(paidAmount.isAmountInsuffient(amount)) {
            message = "Amount insufficient : " + amount.calculateDifference(paidAmount).toString() + " missing";
        };
    }
}
