package info.ohgita.bincalc_android.calc;

/**
 * Numeric expression parser class
 * @author masanori
 * 
 */

import java.util.Stack;

public class ExpParser {

	Stack<String> stack;
	String buf;

	public ExpParser() {

	}

	public Stack<String> parseToStack(String exp) {
		// TODO I will rewrite it completely :p

		/* initialize stack and buffer */
		stack = new Stack<String>();
		buf = new String();
		int flagInBracket = -1;

		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);

			if (c == '(') {
				flagInBracket = 2;
			}
			if (c == ')') {
				_bufPush();
			}

			if (c == '+' || c == '-' || c == '*' || c == '/') {
				// if operator..
				if (buf.contentEquals("+") || buf.contentEquals("-")
						|| buf.contentEquals("*") || buf.contentEquals("/")) {
					// if operators continued... (processing for
					// negative-number)
					_bufPush();
					buf = buf + "(";
					_bufPush();
					flagInBracket = 1;
				} else if (buf.contentEquals("(")) {
					_bufPush();
					flagInBracket = 2;
				} else if (flagInBracket == 1) {
					_bufPush();
					buf = buf + ")";
					_bufPush();
					flagInBracket = -1;
				} else {
					_bufPush();
					flagInBracket = -1;
				}
			} else {
				// if not operator...
				if (buf.contentEquals("(")) {
					_bufPush();
				}
				if (_returnStackLastChar() != '('
						&& (buf.contentEquals("+") || buf.contentEquals("-")
								|| buf.contentEquals("*") || buf
									.contentEquals("/"))) {
					_bufPush();
				}
			}

			// Add char to buf
			buf += c;
		}

		// Push a remaining buffer to stack
		if (buf.length() > 0) {
			if (flagInBracket == 1) {
				_bufPush();
				buf += ")";
			}
			_bufPush();
		}

		return stack;
	}

	protected char _returnStackLastChar() {
		if (stack.empty() == true) {
			return '\n';
		}
		String last = stack.lastElement().toString();
		return last.charAt(last.length() - 1);

	}

	protected void _bufPush() {
		stack.push(buf);
		buf = "";
	}
}
