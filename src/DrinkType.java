public enum DrinkType {
    COFFEE("C", "coffee", "0.6"),
    TEA("T", "tea", "0.4"),
    CHOCOLATE("H", "chocolate", "0.5");

    private final String code;
    private final String name;
    private final String price;

    DrinkType(String code, String name, String price) {
        this.code = code;
        this.name = name;
        this.price = price;
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
}
