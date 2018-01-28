import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {
    @Test
    public void shouldReturnTrueWhenThereIsSugar() {
        Command command = new Command(Command.DrinkType.COFFEE, Command.SweetnessLevel.HALF_SUGAR);
        assertTrue(command.hasStick());
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoSugar() {
        Command command = new Command(Command.DrinkType.TEA, Command.SweetnessLevel.SUGAR_FREE);
        assertFalse(command.hasStick());
    }
}