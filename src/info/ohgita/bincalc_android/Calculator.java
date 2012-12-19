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
			String chunk = iter.next();
			Log.d("binCalc", "  chunk = "+chunk);
			
			String conv = null;
			
			if(Arrays.binarySearch(EXP_SYMBOLS, chunk) < 0){// if number
				if(fromNAdic == 10){
					/* Convert DEC(10) -> NADIC( 2 or 16 ) */
					conv = baseConverter.decToN(Double.parseDouble(chunk), toNAdic);
					
				}else if(fromNAdic == 2){
					if(toNAdic == 10){
						/* Convert BIN(2) -> DEC(10) */
						conv = baseConverter.binToDec(chunk).toString();
					}else if(toNAdic == 16){
						/* Convert BIN(2) -> DEC(16) */
						conv = baseConverter.decToN( baseConverter.binToDec(chunk), 16);
					}
					
				}else if(fromNAdic == 16){
					if(toNAdic == 2){
						/* Convert HEX(16) -> BIN(2) */
						conv = baseConverter.decToN( baseConverter.hexToDec(chunk), 2);
					}else if(toNAdic == 10){
						/* Convert HEX(16) -> DEC(10) */
						conv = baseConverter.hexToDec(chunk).toString();
					}
					
				}else{
					// if symbols(ex: operator)
					conv = chunk;
				}
			}else{
				conv = chunk;
			}
			
			resultExp.append(conv);
		}
		return resultExp.toString();
	}
	
}