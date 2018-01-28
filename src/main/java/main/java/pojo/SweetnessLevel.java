package main.java.pojo;

public enum SweetnessLevel {
    REGULAR_SUGAR(2),
    HALF_SUGAR(1),
    SUGAR_FREE(0);

    private final int sugarNumber;

    SweetnessLevel(int sugarNumber) {
        this.sugarNumber = sugarNumber;
    }

    public static SweetnessLevel findBySugarNumber(int sugarNumber) {
        for (SweetnessLevel sweetnessLevel : values()) {
            if(sweetnessLevel.getSugarNumber() == sugarNumber) {
                return sweetnessLevel;
            }
        }

        throw new IllegalArgumentException("Number of sugar asked is not allowed !");
    }

    public int getSugarNumber() {
        return sugarNumber;
    }
}
