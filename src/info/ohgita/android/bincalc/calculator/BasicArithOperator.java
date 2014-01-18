package info.ohgita.android.bincalc.calculator;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

/**
 * Basic arithmetic operator class
 * 
 * Basic arithmetic operation from LinkedList (Parsed decimal numerical formula).
 * 
 * @author Masanori Ohgita
 * 
 */
public class BasicArithOperator {
	protected LinkedList<String> list;
	protected int roundingScale;
	
	/**
	 * Constructor
	 * @param round_scale Rounding scale
	 */
	public BasicArithOperator(int round_scale) {
		roundingScale = round_scale;
	}
	
	public BasicArithOperator() {
		roundingScale = 2; // second digit
	}
	
	/**
	 * Calculate a numerical formula
	 * @param linkedList LinkedList (Parsed numerical formula)
	 * @return Calculated result
	 */
	public String calculate(LinkedList<String> linkedList){
		list = linkedList;
		Log.i("BasicArithOperator","calculation(...) "+list.toString());
		return eval(list.iterator());
	}
	
	/**
	 * eval (recursive method)
	 * @param iter LinkedList iterator
	 * @return resultant value String
	 */
	protected String eval(Iterator<String> iter){
		Log.i("BasicArithOperator", " eval(...)");
		BigDecimal result = new BigDecimal(0);
		String beforeStr = "";
		while(iter.hasNext()){
			String str = iter.next();
			Log.i("BasicArithOperator","  * str: "+str + " - result = "+result);
			if(str.contentEquals("(")){
				Iterator<String> it = iter;
				str = eval(it);
			}else if(str.contentEquals(")")){
				Log.i("BasicArithOperator","  * break");
				break;
			}
			
			if(beforeStr.contentEquals("+")){
				Log.i("BasicArithOperator","    * "+result+" += "+str);
				result = result.add(new BigDecimal(str));
			}else if(beforeStr.contentEquals("-")){
				Log.i("BasicArithOperator","    * "+result+" -= "+str);
				result = result.subtract(new BigDecimal(str));
			}else if(beforeStr.contentEquals("*")){
				Log.i("BasicArithOperator","    * "+result+" *= "+str);
				result = result.multiply(new BigDecimal(str));
			}else if(beforeStr.contentEquals("/")){
				Log.i("BasicArithOperator","    * "+result+" /= "+str);
				BigDecimal bd = new BigDecimal(str);
				result = result.divide(bd, roundingScale, BigDecimal.ROUND_HALF_UP);
			}else if(beforeStr.length() == 0){
				Log.i("BasicArithOperator","    * "+result+" = "+str);
				result = new BigDecimal(str);
			}
			iter.remove();
			beforeStr = str;
		}
		Log.i("BasicArithOperator", " * return("+result+")");
		return result.toString();
	}
}
