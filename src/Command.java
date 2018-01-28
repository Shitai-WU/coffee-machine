public class Command {
    private String drinkType;
    private int sugarNumber;
    private int stickNumber;
    private String message;

    public Command(String drinkType, int sugarNumber) {
        this.drinkType = drinkType;
        this.sugarNumber = sugarNumber;
        this.stickNumber = sugarNumber > 0 ? 1 : 0;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public int getSugarNumber() {
        return sugarNumber;
    }

    public void setSugarNumber(int sugarNumber) {
        this.sugarNumber = sugarNumber;
    }

    public int getStickNumber() {
        return stickNumber;
    }

    public void setStickNumber(int stickNumber) {
        this.stickNumber = stickNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
