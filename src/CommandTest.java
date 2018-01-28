import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {
    @Test
    public void shouldReturnOneWhenThereIsSugar() {
        Command command = new Command("Coffee", 1);
        assertEquals(1, command.getStickNumber());
    }

    @Test
    public void shouldReturnZeroWhenThereIsNoSugar() {
        Command command = new Command("Coffee", 0);
        assertEquals(0, command.getStickNumber());
    }
}