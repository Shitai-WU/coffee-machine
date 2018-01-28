package main.java.pojo;

public class Command {
    private final DrinkType drinkType;
    private final SweetnessLevel sweetnessLevel;
    private final boolean hasStick;
    private final boolean isExtraHot;
    private final Amount amount;
    private Amount paidAmount;
    private String message;

    public Command(String drinkTypeCode, int sugarNumber) {
        this.drinkType = DrinkType.findByCode(drinkTypeCode);
        //this machine allows to add sugar to orange juice so far !!!
        //TODO to implement the function to prevent the machine to add sugar to orange juice
        this.sweetnessLevel = SweetnessLevel.findBySugarNumber(sugarNumber);
        this.hasStick = sweetnessLevel != SweetnessLevel.SUGAR_FREE;
        this.amount = Amount.fromString(drinkType.getPrice());
        this.isExtraHot = DrinkType.TemperatureLevel.EXTRA_HOT.equals(drinkType.getTemperatureLevel());
        this.paidAmount = Amount.fromString("0");
        this.message = "";
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

    public boolean isExtraHot() {
        return isExtraHot;
    }

    public Amount getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Amount paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Amount getAmount() {
        return amount;
    }
}
