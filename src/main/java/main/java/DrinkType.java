package main.java;

public enum DrinkType {
    COFFEE("C", "coffee", "0.6", TemperatureLevel.HOT),
    TEA("T", "tea", "0.4", TemperatureLevel.HOT),
    CHOCOLATE("H", "chocolate", "0.5", TemperatureLevel.HOT),
    EXTRA_HOT_COFFEE("Ch", "extra_hot_coffee", "0.6", TemperatureLevel.EXTRA_HOT),
    EXTRA_HOT_TEA("Th", "extra_hot_tea", "0.4", TemperatureLevel.EXTRA_HOT),
    EXTRA_HOT_CHOCOLATE("Hh", "extra_hot_chocolate", "0.5", TemperatureLevel.EXTRA_HOT),
    ORANGE_JUICE("O", "orange juice", "0.6", TemperatureLevel.COLD);

    private final String code;
    private final String name;
    private final String price;
    private final TemperatureLevel temperatureLevel;

    enum TemperatureLevel {
        COLD, HOT, EXTRA_HOT;
    }

    DrinkType(String code, String name, String price, TemperatureLevel temperatureLevel) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.temperatureLevel = temperatureLevel;
    }

    public static DrinkType findByCode(String code) {
        for (DrinkType drinkType : values()) {
            if (drinkType.getCode().equals(code)) {
                return drinkType;
            }
        }

        throw new IllegalArgumentException("No such drink type for code : " + code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public TemperatureLevel getTemperatureLevel() {
        return temperatureLevel;
    }
}
