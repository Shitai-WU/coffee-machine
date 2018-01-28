import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {
    @Test
    public void shouldReturnTrueWhenThereIsSugar() {
        Command command = new Command(DrinkType.COFFEE, SweetnessLevel.HALF_SUGAR);

        assertTrue(command.hasStick());
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoSugar() {
        Command command = new Command(DrinkType.TEA, SweetnessLevel.SUGAR_FREE);

        assertFalse(command.hasStick());
    }

    @Test
    public void shouldSendMessageWithInsufficientAmountWhenNotEnoughMoneyProvided() {
        Amount paidAmount = Amount.fromString("0.5");
        String expectedDifference = "0.10";
        Command command = new Command(DrinkType.COFFEE, SweetnessLevel.SUGAR_FREE);

        command.pay(paidAmount);

        assertFalse(command.getMessage().isEmpty());
        assertTrue(command.getMessage().contains(expectedDifference));
    }

    @Test
    public void shouldNotSendMessageWithSufficientAmountWhenEnoughMoneyProvided() {
        Amount paidAmount = Amount.fromString("0.6");
        Command command = new Command(DrinkType.COFFEE, SweetnessLevel.SUGAR_FREE);

        command.pay(paidAmount);

        assertTrue(command.getMessage().isEmpty());
    }

    @Test
    public void shouldNotSendMessageWithSufficientAmountWhenMoreMoneyProvided() {
        Amount paidAmount = Amount.fromString("0.8");
        Command command = new Command(DrinkType.COFFEE, SweetnessLevel.SUGAR_FREE);

        command.pay(paidAmount);

        assertTrue(command.getMessage().isEmpty());
    }
}