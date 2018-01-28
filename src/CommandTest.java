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
        Command command = new Command(DrinkType.COFFEE, SweetnessLevel.SUGAR_FREE);

        command.setPaidAmount(0.5f);

        assertTrue(command.getMessage().contains("0.1"));
    }

    @Test
    public void shouldNotSendMessageWithSufficientAmountWhenEnoughMoneyProvided() {
        Command command = new Command(DrinkType.COFFEE, SweetnessLevel.SUGAR_FREE);

        command.setPaidAmount(0.6f);

        assertEquals("", command.getMessage());
    }
}