package info.ohgita.bincalc_android.calculator;

import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

/**
 * Numerical expression(formula) parser class
 * 
 * Parse the decimal numerical formula, and convert to LinkedList.
 * 
 * @author masanori
 * 
 */
public class ExpParser {
	protected LinkedList<String> list;
	protected String buf;
	
	/**
	 * Parse a decimal numerical formula, and convert to a LinkedList.
	 * @param exp Numerical formula (Decimal numbers)
	 * @return Parsed LinkedList
	 */
	public LinkedList<String> parseToList(String exp) {
		Log.d("binCalc", "ExpParser.parseToList("+exp+")");
		// TODO I will rewrite it completely :p
		
		/* initialize list and buffer */
		list = new LinkedList<String>();
		buf = new String();
		int flagInBracket = 0;
		
		/* processing every one character from mathematical-expression  */
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			
			if(c == ','){
				// if space ...
				continue;
			}
			
			if (c == ')') {
				// if close-bracket...
				
				_bufPush();
				
				while (flagInBracket > 0) {
					// if bracket has been already opening... its all closed now.
					
					list.addLast(")");
					flagInBracket--;
				}
				
			} else if (c == '*' || c == '/'){
				// if multiplication or division operator... it into bracket
				
				if(_returnListLastChar() == ')'){
					_bufPush();
					_chunkBeforeInsert("(");
					flagInBracket++;
				}else{
					list.addLast("(");
					_bufPush();
					flagInBracket++;
				}
				
			}else if (c == '+' || c == '-') {
				// if plus or minus operator...
				
				if (buf.contentEquals("+") || buf.contentEquals("-")	|| buf.contentEquals("*") || buf.contentEquals("/")) {
					_bufPush();
					// if operators continued...  it into bracket (for negative-number)
					buf += "(";
					_bufPush();
					flagInBracket++;
				}
				_bufPush();
				
			} else {
				// if not operator...
				
				if (buf.contentEquals("(")) {
					// if open-bracket has been already in the buffer...
					_bufPush();
				}
				
				if (_returnListLastChar() != '('
						&& (buf.contentEquals("+") || buf.contentEquals("-")
							|| buf.contentEquals("*") || buf.contentEquals("/"))
					) {
					_bufPush();
				}
			}
			
			/* Add char to buf */
			buf += c;
			
			if(c == ')'){
				// if close-bracket... push to List, immediately.  
				_bufPush();
			}
			
		}

		/* Push a remaining buffer to list */
		if (buf.length() > 0) {
			while (flagInBracket > 0) {
				_bufPush();
				buf += ")";
				flagInBracket--;
			}
			_bufPush();
		}
		
		Log.d("binCalc", "parseToList done.");
		return list;
	}
	
	protected char _returnListLastChar() {
		if (list.isEmpty() == true) {
			return '\n';
		}
		String last = list.getLast().toString();
		return last.charAt(last.length() - 1);

	}

	protected void _bufPush() {
		if(buf.length() != 0){
			list.addLast(buf);
			buf = "";
		}
	}
	
	protected void _chunkBeforeInsert(String insertStr){
		int bracket_num = 0, insert_position = 0;
		
		/* find before the last chunk */
		for(int i=list.size()-1; i>=0; i--){
			if(list.get(i).contentEquals(")")){
				bracket_num++;
			}else if(list.get(i).contentEquals("(")){
				bracket_num--;
				if(bracket_num == 0){
					// if found before position...
					insert_position = i;
					break;
				}
			}
		}
		
		/* Making the new List, and insert the insertStr in found position. */
		LinkedList<String> newList = new LinkedList<String>();
		Iterator<String> iter = list.iterator();
		int i = 0;
		
		while(iter.hasNext()){
			String str = iter.next();
			if(i == insert_position){
				newList.addLast(insertStr);
			}
			newList.addLast(str);
			i++;
		}
		
		/* replace old List with new List */
		list = newList;
	}
}
