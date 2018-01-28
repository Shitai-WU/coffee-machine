package main.java;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private final DrinkType drinkType;
    private final SweetnessLevel sweetnessLevel;
    private final boolean hasStick;
    private final boolean isExtraHot;
    private final Amount amount;
    private Amount paidAmount;
    private String message;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    private static List<Command> successfulCommandsHistory = new ArrayList<Command>();

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

    public static int getSucessfulCommandsNumber() {
        return successfulCommandsHistory.size();
    }

    public static Amount getTotalEarnedAmount() {
        Amount totalAmount = Amount.fromString("0");
        for (Command command : successfulCommandsHistory) {
            totalAmount = totalAmount.addAmount(command.getPaidAmount());
        }
        return totalAmount;
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
        //this machine doesn't refund the change so far !!!
        //TODO implement the refund fonction
        if(paidAmount.isAmountInsuffient(amount)) {
            message = "Amount insufficient : " + amount.calculateDifference(paidAmount).toString() + " missing";
        } else {
            this.paidAmount = paidAmount;
            successfulCommandsHistory.add(this);
        };
    }

    public void checkQuantity() {
        if(beverageQuantityChecker.isEmpty(drinkType.getCode())) {
            this.message = drinkType.getName() + " is on shortage !";
        }
    }

    public void sendEmailForShortage() {
        if(beverageQuantityChecker.isEmpty(drinkType.getCode())) {
            emailNotifier.notifyMissingDrink(drinkType.getCode());
        }
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

    public static void resetCommandsHistory() {
        successfulCommandsHistory = new ArrayList<>();
    }

    public BeverageQuantityChecker getBeverageQuantityChecker() {
        return beverageQuantityChecker;
    }

    public void setBeverageQuantityChecker(BeverageQuantityChecker beverageQuantityChecker) {
        this.beverageQuantityChecker = beverageQuantityChecker;
    }

    public EmailNotifier getEmailNotifier() {
        return emailNotifier;
    }

    public void setEmailNotifier(EmailNotifier emailNotifier) {
        this.emailNotifier = emailNotifier;
    }
}
