package calculator.operators;

import calculator.evaluator.Operand;

public class AddOperator extends Operator {
    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int result = operandOne.getValue() + operandTwo.getValue();
        return new Operand(result);
    }
}
