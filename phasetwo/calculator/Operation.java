package phasetwo.calculator;

public enum Operation {
    MULTIPLY("*"),
    DIVIDE("/"),
    PLUS("+"),
    MINUS("-"),
    EXPONENTIATION("^");

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    Operation(String symbol) {
        this.symbol = symbol;
    }
}
