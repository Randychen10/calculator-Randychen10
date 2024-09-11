package calculator.operators;


import calculator.evaluator.Operand;

public class DivideOperator extends Operator {
    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int result = operandOne.getValue() / operandTwo.getValue();
        return new Operand(result);
    }
}
