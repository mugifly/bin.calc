package info.ohgita.bincalc_android;

import info.ohgita.bincalc_android.calculator.BaseConverter;
import info.ohgita.bincalc_android.calculator.BasicArithOperator;
import info.ohgita.bincalc_android.calculator.ExpParser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import android.util.Log;

/**
 * bin.Calc Internal calculator class
 * @author Masanori Ohgita
 */
public class Calculator {
	protected ExpParser expParser;	// Numerical-expression (Numerical formula) parser object
	protected BasicArithOperator basicArithOperator;	// Basic arithmetic operator object
	protected BaseConverter baseConverter;	// Base converter object
	
	protected String EXP_SYMBOLS[] = {"(",")","*","/","+","-"};
	
	protected String memory;
	
	/**
	 * Constructor
	 */
	public Calculator(){
		/* Initialize objects */
		expParser = new ExpParser();
		basicArithOperator = new BasicArithOperator();
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
	
	/**
	 * Calculate a numerical formula
	 * @param exp Numerical formula (Decimal numbers)
	 * @return Calculated result
	 */
	public String calc(String exp){
		Log.d("binCalc", "calc("+exp+")");
		
		/* Parse a numerical formula */
		LinkedList<String> list = parseToList(exp);
		
		/* Calculate formula */
		return basicArithOperator.calculate(list);
	}
	
	/**
	 * Parse a numerical formula, and convert to a LinkedList.
	 * @param exp Numerical formula (Decimal numbers)
	 * @return Parsed LinkedList
	 */
	public LinkedList<String> parseToList(String exp){
		Log.d("binCalc", "parseToList("+exp+")");
		return expParser.parseToList(exp);
	}
	
	/**
	 * Base number convert from LinkedList
	 * @param list LinkedList (Parsed numerical formula)
	 * @param fromNBase Source base
	 * @param destNBase Destination base
	 * @return Converted result
	 */
	public String listBaseConv(LinkedList<String> list, int fromNBase, int destNBase ){
		Log.d("binCalc", "Calculator.listBaseConv(list, "+fromNBase+", "+destNBase+")");
		Iterator<String> iter = list.iterator();
		StringBuilder resultExp = new StringBuilder();
		
		while(iter.hasNext()){
			String chunk = iter.next();
			Log.d("binCalc", "  chunk = "+chunk);
			
			String conv = null;
			
			if(Arrays.binarySearch(EXP_SYMBOLS, chunk) < 0){// if number
				if(fromNBase == 10){
					/* Convert DEC(10) -> NADIC( 2 or 16 ) */
					conv = baseConverter.decToN(Double.parseDouble(chunk), destNBase);
					
				}else if(fromNBase == 2){
					if(destNBase == 10){
						/* Convert BIN(2) -> DEC(10) */
						conv = baseConverter.binToDec(chunk).toString();
					}else if(destNBase == 16){
						/* Convert BIN(2) -> DEC(16) */
						conv = baseConverter.decToN( baseConverter.binToDec(chunk), 16);
					}
					
				}else if(fromNBase == 16){
					if(destNBase == 2){
						/* Convert HEX(16) -> BIN(2) */
						conv = baseConverter.decToN( baseConverter.hexToDec(chunk), 2);
					}else if(destNBase == 10){
						/* Convert HEX(16) -> DEC(10) */
						conv = baseConverter.hexToDec(chunk).toString();
					}
					
				}else{
					conv = chunk;
				}
				
			}else{// if symbols(ex: operator)
				conv = chunk;
			}
			
			resultExp.append(conv);
		}
		return resultExp.toString();
	}
	
}