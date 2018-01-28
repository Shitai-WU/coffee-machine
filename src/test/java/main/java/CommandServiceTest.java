package main.java;

import main.java.pojo.Amount;
import main.java.pojo.Command;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandServiceTest {
    private static final Amount SUFFICIENT_AMOUNT = Amount.fromString("1");
    private static final Amount INSUFFICIENT_AMOUNT = Amount.fromString("0.2");

    private Command command;

    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;

    @Mock
    private EmailNotifier emailNotifier;

    @InjectMocks
    private CommandService commandService;

    @Before
    public void resetCommandsHistory() {
        command = new Command("C", 1);
        CommandService.resetCommandsHistory();
    }

    @Test
    public void shouldSendMessageWithInsufficientAmountWhenNotEnoughMoneyProvided() {
        Amount paidAmount = Amount.fromString("0.5");
        String expectedDifference = "0.10";

        commandService.pay(command, paidAmount);

        assertFalse(command.getMessage().isEmpty());
        assertTrue(command.getMessage().contains(expectedDifference));
    }

    @Test
    public void shouldNotSendMessageWithSufficientAmountWhenEnoughMoneyProvided() {
        Amount paidAmount = Amount.fromString("0.6");

        commandService.pay(command, paidAmount);

        assertTrue(command.getMessage().isEmpty());
    }


    @Test
    public void shouldNotSendMessageWithSufficientAmountWhenMoreMoneyProvided() {
        Amount paidAmount = Amount.fromString("0.8");

        commandService.pay(command, paidAmount);

        assertTrue(command.getMessage().isEmpty());
    }


    @Test
    public void shouldNotSendMessageWithSufficientAmountProvidedForOrangejuice() {
        Amount paidAmount = Amount.fromString("0.6");

        commandService.pay(command, paidAmount);

        assertTrue(command.getMessage().isEmpty());
    }

    @Test
    public void shouldReturnRightCommandsNumberWhenAllBillsAreCheckedWithSuccess() {
        int expectSucessfulCommandsNumber = 4;

        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);

        Assert.assertEquals(expectSucessfulCommandsNumber, CommandService.getSucessfulCommandsNumber());
    }

    @Test
    public void shouldReturnRightCommandsNumberWhenPartielBillsAreCheckedWithSuccess() {
        int expectSucessfulCommandsNumber = 2;

        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, INSUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, INSUFFICIENT_AMOUNT);

        Assert.assertEquals(expectSucessfulCommandsNumber, CommandService.getSucessfulCommandsNumber());
    }

    @Test
    public void shouldReturnRightTotalAmountEarnedWhenAllBillsAreCheckedWithSuccess() {
        Amount expectTotalEarnedAmount = Amount.fromString("4");

        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);

        Assert.assertEquals(expectTotalEarnedAmount, CommandService.getTotalEarnedAmount());
    }

    @Test
    public void shouldReturnRightTotalAmountEarnedWhenPartielBillsAreCheckedWithSuccess() {
        Amount expectTotalEarnedAmount = Amount.fromString("2");

        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, INSUFFICIENT_AMOUNT);
        commandService.pay(command, SUFFICIENT_AMOUNT);
        commandService.pay(command, INSUFFICIENT_AMOUNT);

        Assert.assertEquals(expectTotalEarnedAmount, CommandService.getTotalEarnedAmount());
    }

    @Test
    public void shouldSendMessageForShortageOfDrink() {
        String expectedMessage = "coffee is on shortage !";
        when(beverageQuantityChecker.isEmpty(command.getDrinkType().getCode())).thenReturn(true);

        commandService.checkQuantity(command);

        Assert.assertEquals(expectedMessage, command.getMessage());
    }

    @Test
    public void shouldNotSendMessageForSufficiency() {
        String expectedMessage = "";
        when(beverageQuantityChecker.isEmpty(command.getDrinkType().getCode())).thenReturn(false);

        commandService.checkQuantity(command);

        Assert.assertEquals(expectedMessage, command.getMessage());
    }

    @Test
    public void shouldSendEmailForShortage() {
        when(beverageQuantityChecker.isEmpty(command.getDrinkType().getCode())).thenReturn(true);

        commandService.sendEmailForShortage(command);

        Mockito.verify(emailNotifier).notifyMissingDrink(command.getDrinkType().getCode());
    }

    @Test
    public void shouldNotSendEmailForSufficiency() {
        when(beverageQuantityChecker.isEmpty(command.getDrinkType().getCode())).thenReturn(false);

        commandService.sendEmailForShortage(command);

        Mockito.verify(emailNotifier, never()).notifyMissingDrink(command.getDrinkType().getCode());
    }

}