public enum DrinkType {
    COFFEE("C", "coffee", 0.6f),
    TEA("T", "tea", 0.4f),
    CHOCOLATE("H", "chocolate", 0.5f);

    private final String code;
    private final String name;
    private final float price;

    DrinkType(String code, String name, float price) {
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

    public float getPrice() {
        return price;
    }
}
