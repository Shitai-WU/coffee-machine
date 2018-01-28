package main.java;

import main.java.pojo.Amount;
import main.java.pojo.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandService {
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    private static List<Command> successfulCommandsHistory = new ArrayList<>();

    public static Amount getTotalEarnedAmount() {
        Amount totalAmount = Amount.fromString("0");
        for (Command command : successfulCommandsHistory) {
            totalAmount = totalAmount.addAmount(command.getPaidAmount());
        }
        return totalAmount;
    }

    public void pay(Command command, Amount paidAmount) {
        //this machine doesn't refund the change so far !!!
        //TODO implement the refund fonction
        if(paidAmount.isAmountInsuffient(command.getAmount())) {
            command.setMessage("Amount insufficient : " + command.getAmount().calculateDifference(paidAmount).toString() + " missing");
        } else {
            command.setPaidAmount(paidAmount);
            successfulCommandsHistory.add(command);
        }
    }

    public static int getSucessfulCommandsNumber() {
        return successfulCommandsHistory.size();
    }

    public void checkQuantity(Command command) {
        if(beverageQuantityChecker.isEmpty(command.getDrinkType().getCode())) {
            command.setMessage(command.getDrinkType().getName() + " is on shortage !");
        }
    }

    public void sendEmailForShortage(Command command) {
        if(beverageQuantityChecker.isEmpty(command.getDrinkType().getCode())) {
            emailNotifier.notifyMissingDrink(command.getDrinkType().getCode());
        }
    }

    public static void resetCommandsHistory() {
        successfulCommandsHistory = new ArrayList<>();
    }
}
