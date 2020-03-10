import java.util.Stack;

public class Evaluate {
  public static double eval(String expression) {
    char[] inputs = expression.toCharArray();

    Stack<Double> numbers = new Stack<Double>();
    Stack<Character> operators = new Stack<Character>();

    for (int i = 0; i < inputs.length; i++) {
      if (inputs[i] == ' ') {
        continue;
      }

      if (inputs[i] >= '0' && inputs[i] <= '9') {
        StringBuilder stringBuilder = new StringBuilder();
        while (i < inputs.length && ((inputs[i] >= '0' && inputs[i] <= '9') || inputs[i] == '.')) {
          stringBuilder.append(inputs[i]);
          i = i + 1;
        }
        numbers.push(Double.parseDouble(stringBuilder.toString()));
      }

      else if (inputs[i] == '(')
        operators.push(inputs[i]);

      else if (inputs[i] == ')') {
        while (operators.peek() != '(')
          numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
        operators.pop();
      }

      else if (inputs[i] == '+' || inputs[i] == '-' ||
          inputs[i] == '*' || inputs[i] == '/') {
        while (!operators.empty() && checkPrecedence(inputs[i], operators.peek()))
          numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));

        operators.push(inputs[i]);
      }
    }

    while (!operators.empty())
      numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));

    return numbers.pop();
  }

  public static boolean checkPrecedence(char op1, char op2) {
    if (op2 == '(' || op2 == ')')
      return false;
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
      return false;
    else
      return true;
  }

  public static double calculate(char op, double b, double a) {
    switch (op) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        if (b == 0)
          throw new
              ArithmeticException("Divide by Zero Error.");
        return a / b;
    }
    return 0;
  }
}