package info.ohgita.bincalc_android;

import info.ohgita.bincalc_android.calc.ExpParser;

import java.util.Iterator;
import java.util.Stack;

import android.util.Log;

/**
 * Calculator class
 * @author masanori ohgita
 */
public class Calculator {
	
	ExpParser expParser;
	
	/**
	 * Constructor
	 */
	public Calculator(){
		expParser = new ExpParser();
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
	
	public String calc(String exp){
		
		return expParser.parseToStack(exp).toString();
		
		/*Iterator<String> iter = stack.iterator();
		
		String before = "";
		while(iter.hasNext()){
			String str = iter.next();
			
			before = str;
			
		}*/
	}
	
}