package info.ohgita.bincalc_android.calc;

/**
 * Numeric expression parser class
 * @author masanori
 * 
 */

import java.util.Iterator;
import java.util.Stack;

import android.util.Log;

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
			
			if(buf.contentEquals(")")){
				_bufPush();
			}

			if (c == '(') {
				flagInBracket = 0;
			}
			if (c == ')') {
				if (flagInBracket == 1) {// if bracket has been already opening, its closed now.
					_bufPush();
					stack.push(")");
					flagInBracket = -1;
				}
				_bufPush();
			}

			if (c == '*' || c == '/'){ // multiplication and division is into bracket
				if(_returnStackLastChar() == ')'){
					_bufPush();
					_chunkBeforeInsert("(");
				}else{
					Log.i("binCalc","Ins (");
					stack.push("(");
					_bufPush();
				}
				
				if (flagInBracket == 1) {// if bracket has been already opening, its closed now.
					_bufPush();
					stack.push(")");
				}
				flagInBracket = 1;
			}
			if (c == '+' || c == '-') {
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
					flagInBracket = 0;
				} else if (flagInBracket == 1) {// if bracket has been already opening, its closed now.
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
		if(buf.length() != 0){
			stack.push(buf);
			buf = "";
		}
	}
	
	protected void _chunkBeforeInsert(String insertStr){
		int bracket_num = 0, insert_position = 0;
		
		// find before the last chunk
		for(int i=stack.size()-1; i>=0; i--){
			if(stack.get(i).contentEquals(")")){
				bracket_num++;
			}else if(stack.get(i).contentEquals("(")){
				bracket_num--;
				if(bracket_num == 0){// if found before position...
					insert_position = i;
					break;
				}
			}
		}
		
		// Making the new Stack, and insert the insertStr in found position.
		Stack<String> newStack = new Stack<String>();
		Iterator<String> iter = stack.iterator();
		int i = 0;
		
		while(iter.hasNext()){
			String str = iter.next();
			if(i == insert_position){
				newStack.push(insertStr);
			}
			newStack.push(str);
			i++;
		}
		
		// replace old Stack with new Stack
		Log.i("binCalc","_chunkBeforeInsert(...)");
		Log.i("binCalc","  * old = "+stack.toString());
		Log.i("binCalc","  * new = "+newStack.toString());
		stack = newStack;
	}
}
