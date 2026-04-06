public class Tester {

    private static int prec(char c, boolean onStack) {
        if (onStack) {
            switch (c) {
                case '(':
                    return 0;
                case '^':
                    return 5;
                case '*':
                    return 4;
                case '/':
                    return 4;
                case '%':
                    return 4;
                case '-':
                    return 2;
                case '+':
                    return 2;
            }
        } else {
            switch (c) {
                case '(':
                    return 100;
                case ')':
                    return 0;
                case '^':
                    return 6;
                case '*':
                    return 3;
                case '/':
                    return 3;
                case '%':
                    return 3;
                case '-':
                    return 1;
                case '+':
                    return 1;
            }
        }
        throw new AssertionError();
    }

    public static String infixToPostfix(String infix) {
        infix = infix.replace(" ", "");
        Stack stack = new Stack();
        String postfix = "";
        char eval;

        for (int i = 0; i < infix.length(); i++) {
            eval = infix.charAt(i);

            if (Character.isDigit(eval)) { // If eval is an operand add it to the postfix
                postfix += eval + " ";
            } else if (eval == '(') { // If eval is '(' add it to the Stack
                stack.push(eval);
            } else if (eval == ')') { // If eval is ')' pop the Stack to the postfix until '('
                while ((char)stack.peek() != '(') {
                    postfix += stack.pop() + " ";
                }
                stack.pop(); // remove '('
            } else {
                while (!stack.isEmpty() && prec((char)stack.peek(), true) > prec(eval, false)) {
                    postfix += stack.pop() + " ";
                }
                stack.push(eval);
            }
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop() + " ";
        }
        return postfix;
    }

    public static int evaluatePostfix(String postfix) {
        postfix = postfix.replace(" ", "");
        Stack stack = new Stack();
        char eval;

        for (int i = 0; i < postfix.length(); i++) {
            eval = postfix.charAt(i);
            //System.out.println("Eval is: " + eval);

            if (Character.isDigit(eval)) {
                stack.push(Character.getNumericValue(eval));
            } else {
                int left, right;
                right = (int)stack.pop();
                left = (int)stack.pop();

                switch (eval) {
                    case '^':
                        stack.push((int)Math.pow(left, right));
                        break;
                    case '*':
                        stack.push(left * right);
                        break;
                    case '/':
                        stack.push(left / right);
                        break;
                    case '%':
                        stack.push(left % right);
                        break;
                    case '+':
                        stack.push(left + right);
                        break;
                    case '-':
                        stack.push(left - right);
                        break;
                }
            }
            //System.out.println("Top of Stack is: " + stack.peek() + "\n");
        }

        if (stack.size() == 1) {
            return (int)stack.pop();
        }

        return -999;
    }

    public static void tester(String infix) {
        System.out.println("Infix Expression: " + infix);

        String postfix = infixToPostfix(infix);
        System.out.println("Postfix Expression: " + postfix);

        int result = evaluatePostfix(postfix);
        System.out.println("Result: " + result + "\n");
    }

    public static void main(String[] args) {
        tester("(((1+2)-(3-4))/(6-5))");

        tester("2 * 4 - 2 ^ 2 ^ 1");
    }
}
