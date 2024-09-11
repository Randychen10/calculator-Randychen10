package calculator.evaluator;

import calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private final Stack<Operand> operandStack;
  private final Stack<Operator> operatorStack;


  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

    public int evaluateExpression(String expression ) throws InvalidTokenException {
    String expressionToken;
    StringTokenizer expressionTokenizer;
    String delimiters = " +/*-^()";

    // 3 arguments tells the tokenizer to return delimiters as tokens
    expressionTokenizer = new StringTokenizer( expression, delimiters, true );

    while ( expressionTokenizer.hasMoreTokens() ) {

      expressionToken = expressionTokenizer.nextToken().trim();

      // filter out spaces
      if (!expressionToken.isEmpty()) {
        // check if token is an operand
        if (Operand.check(expressionToken)) {
          operandStack.push(new Operand(expressionToken));
        } else {
          if (!Operator.check(expressionToken)) {
            throw new InvalidTokenException(expressionToken);
          }

          // TODO fix this line of code.
          Operator newOperator = Operator.getOperator(expressionToken);

          if (newOperator instanceof parenthesisOperator) {
            parenthesisOperator leftParenthesis = (parenthesisOperator) newOperator;
            if (leftParenthesis.left()) {
              operatorStack.push(leftParenthesis);
            } else {
              while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof parenthesisOperator)) {
                Operator operatorFromStack = operatorStack.pop();
                Operand operandTwo = operandStack.pop();
                Operand operandOne = operandStack.pop();
                Operand result = operatorFromStack.execute(operandOne, operandTwo);
                operandStack.push(result);
              }
              if (!operatorStack.isEmpty() && operatorStack.peek() instanceof parenthesisOperator) {
                operatorStack.pop();
              }
            }
          } else {
            while (!operatorStack.isEmpty() && operatorStack.peek().priority() >= newOperator.priority()) {
              Operator operatorFromStack = operatorStack.pop();
              Operand operandTwo = operandStack.pop();
              Operand operandOne = operandStack.pop();
              Operand result = operatorFromStack.execute(operandOne, operandTwo);
              operandStack.push(result);
            }
            operatorStack.push(newOperator);
          }
        }
      }
    }

      while (!operatorStack.isEmpty()) {
        Operator operatorFromStack = operatorStack.pop();
        Operand operandTwo = operandStack.pop();
        Operand operandOne = operandStack.pop();
        Operand result = operatorFromStack.execute(operandOne, operandTwo);
        operandStack.push(result);


      }

      return operandStack.pop().getValue();
  }

  public static void main(String[] args) throws InvalidTokenException {
     if(args.length == 1){
      Evaluator e = new Evaluator();
      System.out.println(e.evaluateExpression(args[0]));
     }else{
      System.err.println("Invalid arguments or No expression given");
     }
  }
}
