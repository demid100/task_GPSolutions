package phasetwo.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static phasetwo.calculator.Operation.*;

public class Calculator {
    private String expression;

    public Calculator(String expression) {
        this.expression = expression;
    }

    public void validExpression() throws InvalidException {
        if (expression.matches(".*(\\d+)\\s+(\\d+).*"))
            throw new InvalidException("Невалидные данные. Число не должно содержать пробелов");
        if (expression.matches(".*(\\d+)[\\.,]+(\\d*)[\\.,]+.*"))
            throw new InvalidException("Невалидные данные. В одном числе несколько разделителей целой и дробной частей");
        if (expression.matches(".*[^\\d\\^\\(\\)\\s\\*\\/\\+\\-\\.,].*"))
            throw new InvalidException("Невалидные данные. Выражение содержит неподдерживаемые символы");
        if (expression.matches(".*[^\\)\\d]") || expression.matches(".*[\\^\\*\\/\\+]{2,}.*"))
            throw new InvalidException("Невалидные данные. Отсутсвует операнд");

        expression = expression.replaceAll(" ", "");
        expression = expression.replaceAll(",", ".");
    }

    public String openBranchesAndCalculate() throws InvalidException {
        List<Integer> indexOpenBranches = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') indexOpenBranches.add(i);
            if (expression.charAt(i) == ')'){
                if (indexOpenBranches.size() == 0) throw new InvalidException("Количество открытых скобок меньше количества закрытых");
                int indexLastOpenBranch = indexOpenBranches.remove(indexOpenBranches.size() -1);
                String expressionInBranches = expression.substring(indexLastOpenBranch, i +1);
                String resultExpressionInBranches = calculate(expressionInBranches);
                expression = expression.replace(expressionInBranches, resultExpressionInBranches);

                i = indexLastOpenBranch;
            }
        }
        if (indexOpenBranches.size() > 0) throw new InvalidException("Количество открытых скобок больше количества закрытых");
        if (expression.matches(".*[^\\^\\(\\)\\*\\/\\+\\-\\.,].*")){
            expression = calculate(expression);
        }
        return expression;
    }

    private String calculate(String expression) throws InvalidException {
        if (expression.contains("(")) expression = expression.substring(1, expression.length() - 1);

        while (true) {
            if (expression.contains("^")){
                String[] replasedResult = moveExpressionAndResult(expression, EXPONENTIATION);
                expression = expression.replace(replasedResult[0], replasedResult[1]);
            }else if (expression.contains("*") || expression.contains("/")) {
                int indexMultiply =  expression.indexOf("*");
                int indexDivide = expression.indexOf("/");
                Operation parametr = (indexMultiply > 0 ? indexMultiply : Integer.MAX_VALUE) < (indexDivide > 0 ? indexDivide : Integer.MAX_VALUE) ? MULTIPLY : DIVIDE;
                String[] replasedResult = moveExpressionAndResult(expression, parametr);
                expression = expression.replace(replasedResult[0], replasedResult[1]);
            }else if (expression.contains("+")) {
                String[] replasedResult = moveExpressionAndResult(expression, PLUS);
                expression = expression.replace(replasedResult[0], replasedResult[1]);
            }else if (expression.contains("-")) {
                if (expression.matches("-(\\d+)\\.*(\\d*)")) return expression;
                String[] replasedResult = moveExpressionAndResult(expression, MINUS);
               expression = expression.replaceFirst(replasedResult[0], replasedResult[1]);
            }
            else break;
        }
        return expression;
    }

    private String[] moveExpressionAndResult(String expression, Operation operation) throws InvalidException {
        String[] result = new String[2];
        int border= expression.indexOf(operation.getSymbol());
        if (border == 0) border = expression.indexOf(operation.getSymbol(), 1);
        int borderEnd= border;
        int borderStart = border;

        while ((borderStart > 0) && (String.valueOf(expression.charAt(borderStart - 1)).matches("[\\d\\.,\\-]"))){
            char positionValue = expression.charAt(borderStart - 1);
            if (positionValue == '-' && ((borderStart - 1) == 0 || String.valueOf(expression.charAt(borderStart - 1)).matches("[\\*\\/\\+\\-]"))){
                borderStart--;
                break;
            }
            borderStart--;
        }
        if (expression.charAt(border+1) == '-') borderEnd ++;
        while ((borderEnd < expression.length() - 1) && (String.valueOf(expression.charAt(borderEnd+1)).matches("[\\d\\.,]"))){
            borderEnd++;
        }

        result[0] = expression.substring(borderStart, borderEnd+1);
        result[1] = operate(result[0], operation, border - borderStart);
        return result;
    }

    private String operate(String expression, Operation operation, int border) throws InvalidException {
        BigDecimal integerOne;
        BigDecimal integerTwo;
        String result = "";

        switch (operation){
            case MULTIPLY:
                integerOne = new BigDecimal(expression.substring(0, border));
                integerTwo = new BigDecimal(expression.substring(border + 1, expression.length()));
                result = integerOne.multiply(integerTwo).toString();
                break;
            case DIVIDE:
                integerOne = new BigDecimal(expression.substring(0, border));
                integerTwo = new BigDecimal(expression.substring(border + 1, expression.length()));
                if (integerTwo.equals(new BigDecimal(0))) throw new InvalidException("Деление на 0");
                result = integerOne.divide(integerTwo).toString();
                break;
            case PLUS:
                integerOne = new BigDecimal(expression.substring(0, border));
                integerTwo = new BigDecimal(expression.substring(border + 1, expression.length()));
                result = integerOne.add(integerTwo).toString();
                break;
            case MINUS:
                integerOne = new BigDecimal(expression.substring(0, border));
                integerTwo = new BigDecimal(expression.substring(border + 1, expression.length()));
                result = integerOne.subtract(integerTwo).toString();
                break;
            case EXPONENTIATION:
                integerOne = new BigDecimal(expression.substring(0, border));
                int exponent = Integer.parseInt(expression.substring(border + 1, expression.length()));
                if (exponent < 0 ){
                    return (new BigDecimal(1).divide(integerOne).pow(Math.abs(exponent))).toString();
                }
                result = integerOne.pow(exponent).toString();
                break;
        }
        return result;
    }
}
