package info.ohgita.bincalc_android;

import java.util.Iterator;
import java.util.Stack;

/**
 * Calculator class
 * @author masanori ohgita
 */
public class Calculator {
	
	/**
	 * Constructor
	 */
	public Calculator(){
		
	}
	
	/**
	 * MemoryIn method
	 */
	public void MemoryIn(){
		
	}
	
	/**
	 * MemoryOut (Read) method
	 */
	public void MemoryOut(){
		
	}
	
	public void equall(){
		
	}
	
	public void calc(String exp){
		Stack<String> stack = new Stack<String>();
		String buf = new String();
		for(int i=0;i<exp.length();i++){
			char c = exp.charAt(i);
			if(c == '+' || c == '-' || c == '*' || c == '/'){
				stack.push(buf);
				buf = "";
			}
			buf += c;
		}
		Iterator<String> iter = stack.iterator();
		
		String before = "";
		while(iter.hasNext()){
			String str = iter.next();
			
			before = str;
			// 10+200*10
		}
	}
	
}