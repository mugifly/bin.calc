package info.ohgita.bincalc_android;

import info.ohgita.bincalc_android.calc.BaseConverter;
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
	BaseConverter baseConverter;
	
	String EXP_SYMBOLS[] = {"(",")","*","/","+","-"};
	
	/**
	 * Constructor
	 */
	public Calculator(){
		expParser = new ExpParser();
		baOperator = new BasicArithOperator();
		baseConverter = new BaseConverter();
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
		LinkedList<String> list = parseToList(exp);
		return baOperator.calculation(list);
	}
	
	public LinkedList<String> parseToList(String exp){
		return expParser.parseToList(exp);
	}
	
	public String listBaseConv(LinkedList<String> list, int fromNAdic, int toNAdic ){
		Iterator<String> iter = list.iterator();
		StringBuilder resultExp = new StringBuilder();
		while(iter.hasNext()){
			String str = iter.next();
			if(Arrays.binarySearch(EXP_SYMBOLS, str) < 0){// if number
				if(fromNAdic == 10){
					resultExp.append(baseConverter.decToN(Integer.parseInt(str), toNAdic));
				}else{// if symbols(ex: operator)
					resultExp.append(str);
				}
			}else{
				resultExp.append(str);
			}
		}
		return resultExp.toString();
	}
	
}