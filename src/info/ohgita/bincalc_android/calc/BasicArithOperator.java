package info.ohgita.bincalc_android.calc;

import java.util.Iterator;
import java.util.Stack;

/**
 * Basic arithmetic operation class
 * @author masanori
 * 
 */

public class BasicArithOperator {
	
	public BasicArithOperator(){
		
	}
	
	public void calculation(Stack<String> stack){

	}
	
	public void eval(Stack<String> stack){
		Iterator<String> iter = stack.iterator();
		int i=0;
		while(iter.hasNext()){
			String str = iter.next();
			if(str.contentEquals("(")){
				//eval(iter.);
			}
			i++;
		}
	}
	
	public Stack<String> clipRange(Stack<String> stack, Iterator<String> iter){
		while(iter.hasNext()){
			String str = iter.next();
			if(str.contentEquals("(")){
				//eval(iter.);
			}
		}
		return null;//DEBUG
	}
}
