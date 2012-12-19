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
		Log.d("binCalc", "calc("+exp+")");
		LinkedList<String> list = parseToList(exp);
		return baOperator.calculation(list);
	}
	
	public LinkedList<String> parseToList(String exp){
		Log.d("binCalc", "parseToList("+exp+")");
		return expParser.parseToList(exp);
	}
	
	public String listBaseConv(LinkedList<String> list, int fromNAdic, int toNAdic ){
		Log.d("binCalc", "Calculator.listBaseConv(list, "+fromNAdic+", "+toNAdic+")");
		Iterator<String> iter = list.iterator();
		StringBuilder resultExp = new StringBuilder();
		while(iter.hasNext()){
			String str = iter.next();
			Log.d("binCalc", "  str = "+str);
			if(Arrays.binarySearch(EXP_SYMBOLS, str) < 0){// if number
				if(fromNAdic == 10){
					/* Convert decimal to NADIC */
					resultExp.append(baseConverter.decToN(Integer.parseInt(str), toNAdic));
				}else if(fromNAdic == 2){
					/* Convert binary to decimal */
					resultExp.append(baseConverter.binToDec(str));
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