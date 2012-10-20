package info.ohgita.bincalc_android.calc;

import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

/**
 * Basic arithmetic operation class for bin.Calc
 * @author masanori
 * 
 */

public class BasicArithOperator {
	
	protected LinkedList<String> list;
	
	public BasicArithOperator(){
		
	}
	
	/**
	 * calculation
	 * @param list LinkedList 
	 * @return resultant value String
	 */
	public String calculation(LinkedList<String> linkedList){
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
		int i = 0;
		double result = 0;
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
				result += Double.parseDouble(str);
			}else if(beforeStr.contentEquals("-")){
				Log.i("BasicArithOperator","    * "+result+" -= "+str);
				result -= Double.parseDouble(str);
			}else if(beforeStr.contentEquals("*")){
				Log.i("BasicArithOperator","    * "+result+" *= "+str);
				result *= Double.parseDouble(str);
			}else if(beforeStr.contentEquals("/")){
				Log.i("BasicArithOperator","    * "+result+" /= "+str);
				result /= Double.parseDouble(str);
			}else if(beforeStr.length() == 0){
				Log.i("BasicArithOperator","    * "+result+" = "+str);
				result = Double.parseDouble(str);
			}
			iter.remove();
			beforeStr = str;
			i++;
		}
		Log.i("BasicArithOperator", " * return("+result+")");
		return Double.toString(result);
	}
}
