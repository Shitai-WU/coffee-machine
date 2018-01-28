package main.java.pojo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldReturnRightBeverageForRightCodeEntered() {
        String code = "C";
        Command command = new Command(code, 1);

        Assert.assertEquals(DrinkType.findByCode(code), command.getDrinkType());
    }

    @Test
    public void shouldReturnThrowExceptionWhenUnknownCodeEntered() throws IllegalArgumentException {
        String code = "XXX";

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("No such drink type for code : " + code);

        Command command = new Command(code, 1);
    }

    @Test
    public void shouldReturnRightNumbersOfSugarForRightNumberEntered() {
        int sugarNumber = 2;
        Command command = new Command("C", sugarNumber);

        Assert.assertEquals(sugarNumber, command.getSweetnessLevel().getSugarNumber());
    }

    @Test
    public void shouldThrowExceptionWhenEnteredNumberOfSugarIsNotAllowed() throws IllegalArgumentException{
        int sugarNumber = -8;

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number of sugar asked is not allowed !");

        Command command = new Command("C", sugarNumber);
    }

    @Test
    public void shouldReturnStickWhenThereIsSugar() {
        Command command = new Command("C", 1);

        assertTrue(command.hasStick());
    }

    @Test
    public void shouldNotStickWhenThereIsNoSugar() {
        Command command = new Command("T", 0);

        assertFalse(command.hasStick());
    }

    @Test
    public void shouldBeExtraHotWhenExtraHotCoffeeCommanded() {
        Command command = new Command("Ch", 0);

        assertTrue(command.isExtraHot());
    }

    @Test
    public void shouldNotBeExtraHotWhenNormalCoffeeCommanded() {
        Command command = new Command("C", 0);

        assertFalse(command.isExtraHot());
    }

    @Test
    public void shouldNotBeExtraHotWhenOrangeJuiceCommanded() {
        Command command = new Command("O", 0);

        assertFalse(command.isExtraHot());
    }
}