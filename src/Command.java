public class Command {
    private final DrinkType drinkType;
    private final SweetnessLevel sweetnessLevel;
    private final boolean hasStick;
    private String message = "";
    private float paidAmount;

    public Command(DrinkType drinkType, SweetnessLevel sweetnessLevel) {
        this.drinkType = drinkType;
        this.sweetnessLevel = sweetnessLevel;
        this.hasStick = sweetnessLevel != SweetnessLevel.SUGAR_FREE;
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

    public float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(float paidAmount) {
        this.paidAmount = paidAmount;
        if(this.paidAmount < drinkType.getPrice()) {
            float missingAmount = drinkType.getPrice() - this.paidAmount;
            message = "Amount insufficient : " + missingAmount + "missing";
        }
    }
}
