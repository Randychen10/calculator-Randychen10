package calculator.operators;

import calculator.evaluator.Operand;

public class parenthesisOperator extends Operator{
    private String value;

    public parenthesisOperator(String value) {
        this.value = value;
    }

    @Override
    public int priority() {
        return -1;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return null;
    }

    public String getValue() {
        return value;
    }

    public boolean left() {
        return "(".equals(value);
    }
}
