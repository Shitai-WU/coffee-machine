public class Command {
    private final DrinkType drinkType;
    private final SweetnessLevel sweetnessLevel;
    private final boolean hasStick;
    private Amount amount;
    private boolean isExtraHot;
    private String message = "";

    public Command(String drinkTypeCode, int sugarNumber) {
        this.drinkType = DrinkType.findByCode(drinkTypeCode);
        this.sweetnessLevel = SweetnessLevel.findBySugarNumber(sugarNumber);
        this.hasStick = sweetnessLevel != SweetnessLevel.SUGAR_FREE;
        this.amount = Amount.fromString(drinkType.getPrice());
        this.isExtraHot = DrinkType.TemperatureLevel.EXTRA_HOT.equals(drinkType.getTemperatureLevel());
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

    public boolean isExtraHot() {
        return isExtraHot;
    }
}
