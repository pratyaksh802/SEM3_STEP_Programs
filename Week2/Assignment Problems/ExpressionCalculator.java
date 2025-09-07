import java.util.*;

public class ExpressionCalculator {

    // Convert infix to postfix using Shunting Yard
    static List<String> infixToPostfix(String expr) {
        Stack<Character> stack = new Stack<>();
        List<String> output = new ArrayList<>();

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    output.add(number.toString());
                    number.setLength(0);
                }

                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(')
                        output.add(Character.toString(stack.pop()));
                    stack.pop();
                } else if ("+-*/^".indexOf(c) != -1) {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c))
                        output.add(Character.toString(stack.pop()));
                    stack.push(c);
                }
            }
        }

        if (number.length() > 0) output.add(number.toString());

        while (!stack.isEmpty()) output.add(Character.toString(stack.pop()));

        return output;
    }

    // Precedence
    static int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        if (c == '^') return 3;
        return -1;
    }

    // Evaluate postfix
    static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            if ("+-*/^".contains(token)) {
                double b = stack.pop();
                double a = stack.pop();
                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                    case '^': stack.push(Math.pow(a, b)); break;
                }
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a mathematical expression: ");
        String expr = sc.nextLine().replaceAll("\\s+", "");

        List<String> postfix = infixToPostfix(expr);
        double result = evaluatePostfix(postfix);

        System.out.println("Postfix: " + postfix);
        System.out.println("Result: " + result);
    }
}
