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
