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
		int flagInBracket = 0;
		
		/* processing every one character from mathematical-expression  */
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			
			if (c == ')') {
				// if close-bracket...
				
				_bufPush();
				
				while (flagInBracket > 0) {
					// if bracket has been already opening... its all closed now.
					
					stack.push(")");
					flagInBracket--;
				}
				
			} else if (c == '*' || c == '/'){
				// if multiplication or division operator... it into bracket
				
				if(_returnStackLastChar() == ')'){
					_bufPush();
					_chunkBeforeInsert("(");
					flagInBracket++;
				}else{
					stack.push("(");
					_bufPush();
					flagInBracket++;
				}
				
			}else if (c == '+' || c == '-') {
				// if plus or minus operator...
				
				_bufPush();
				
				if (buf.contentEquals("+") || buf.contentEquals("-")	|| buf.contentEquals("*") || buf.contentEquals("/")) {
					// if operators continued...  it into bracket (for negative-number)
					buf += "(";
					_bufPush();
					flagInBracket++;
				}
				
			} else {
				// if not operator...
				
				if (buf.contentEquals("(")) {
					// if open-bracket has been already in the buffer...
					_bufPush();
				}
				
				if (_returnStackLastChar() != '('
						&& (buf.contentEquals("+") || buf.contentEquals("-")
							|| buf.contentEquals("*") || buf.contentEquals("/"))
					) {
					_bufPush();
				}
			}
			
			/* Add char to buf */
			buf += c;
			
			if(c == ')'){
				// if close-bracket... push to Stack, immediately.  
				_bufPush();
			}
			
		}

		/* Push a remaining buffer to stack */
		if (buf.length() > 0) {
			while (flagInBracket > 0) {
				_bufPush();
				buf += ")";
				flagInBracket--;
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
		
		/* find before the last chunk */
		for(int i=stack.size()-1; i>=0; i--){
			if(stack.get(i).contentEquals(")")){
				bracket_num++;
			}else if(stack.get(i).contentEquals("(")){
				bracket_num--;
				if(bracket_num == 0){
					// if found before position...
					insert_position = i;
					break;
				}
			}
		}
		
		/* Making the new Stack, and insert the insertStr in found position. */
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
		
		/* replace old Stack with new Stack */
		stack = newStack;
	}
}
