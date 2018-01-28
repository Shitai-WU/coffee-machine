public class Command {
    private final DrinkType drinkType;
    private final SweetnessLevel sweetnessLevel;
    private final boolean hasStick;
    private String message;

    public Command(DrinkType drinkType, SweetnessLevel sweetnessLevel) {
        this.drinkType = drinkType;
        this.sweetnessLevel = sweetnessLevel;
        this.hasStick = sweetnessLevel != SweetnessLevel.SUGAR_FREE;
    }

    public enum DrinkType {
        COFFEE("C", "coffee"),
        TEA("T", "tea"),
        CHOCOLATE("H", "chocolate");

        private final String code;
        private final String name;


        DrinkType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public enum SweetnessLevel {
        REGULAR_SUGAR(2),
        HALF_SUGAR(1),
        SUGAR_FREE(0);

        private final int sugarNumber;

        SweetnessLevel(int sugarNumber) {
            this.sugarNumber = sugarNumber;
        }

        public int getSugarNumber() {
            return sugarNumber;
        }
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
}
