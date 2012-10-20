package info.ohgita.bincalc_android;

import info.ohgita.bincalc_android.calc.BasicArithOperator;
import info.ohgita.bincalc_android.calc.ExpParser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import android.util.Log;

/**
 * Calculator class
 * @author masanori ohgita
 */
public class Calculator {
	
	ExpParser expParser;
	BasicArithOperator baOperator;
	
	/**
	 * Constructor
	 */
	public Calculator(){
		expParser = new ExpParser();
		baOperator = new BasicArithOperator();
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
		Stack<String> stack = expParser.parseToStack(exp);
		LinkedList<String> list = new LinkedList<String>();
		Iterator<String> it = stack.iterator();
		while(it.hasNext()){
			list.addLast(it.next());
		}
		return baOperator.calculation(list);
	}
	
}