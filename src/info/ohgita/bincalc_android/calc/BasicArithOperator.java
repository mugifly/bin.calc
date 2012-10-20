package info.ohgita.bincalc_android.calc;

import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

/**
 * Basic arithmetic operation class
 * @author masanori
 * 
 */

public class BasicArithOperator {
	
	protected LinkedList<String> list;
	
	public BasicArithOperator(){
		
	}
	
	/**
	 * calculation
	 * @param (LinkedList list) 
	 * @return (String) resultant value
	 */
	public String calculation(LinkedList<String> linkedList){
		list = linkedList;
		Log.i("binCalc","calculation(...) "+list.toString());
		return eval(list.iterator());
	}
	
	protected String eval(Iterator<String> iter){
		Log.i("binCalc", " eval(...)");
		int i = 0;
		double result = 0;
		String beforeStr = "";
		while(iter.hasNext()){
			String str = iter.next();
			Log.i("binCalc","  * str: "+str + " - result = "+result);
			if(str.contentEquals("(")){
				Iterator<String> it = iter;
				str = eval(it);
			}else if(str.contentEquals(")")){
				Log.i("binCalc","  * break");
				break;
			}
			
			if(beforeStr.contentEquals("+")){
				Log.i("binCalc","    * "+result+" += "+str);
				result += Double.parseDouble(str);
			}else if(beforeStr.contentEquals("-")){
				Log.i("binCalc","    * "+result+" -= "+str);
				result -= Double.parseDouble(str);
			}else if(beforeStr.contentEquals("*")){
				Log.i("binCalc","    * "+result+" *= "+str);
				result *= Double.parseDouble(str);
			}else if(beforeStr.contentEquals("/")){
				Log.i("binCalc","    * "+result+" /= "+str);
				result /= Double.parseDouble(str);
			}else if(beforeStr.length() == 0){
				Log.i("binCalc","    * "+result+" = "+str);
				result = Double.parseDouble(str);
			}
			iter.remove();
			beforeStr = str;
			i++;
		}
		Log.i("binCalc", " * return("+result+")");
		return Double.toString(result);
	}
	
	/**
	 * Replace after part of iterator of the LinkedList, with character string
	 * @param queue
	 * @param iter
	 * @param replaceStr
	 * @return replaced queue
	 * 
	 */
	protected LinkedList<String> replaceRange(LinkedList<String> queue, Iterator<String> iter, String replaceStr){
		LinkedList<String> newLinkedList = new LinkedList<String>();
		while(iter.hasNext()){
			String str = iter.next();
			if(str.contentEquals(")")){
				break;
			}
		}
		return queue;
	}
	
	/**
	 * Clip out LinkedList, only part after iterator.
	 * @param queue
	 * @param iter
	 * @return clipped queue
	 */
	protected LinkedList<String> clipRange(LinkedList<String> queue, Iterator<String> iter){
		LinkedList<String> newList = new LinkedList<String>();
		while(iter.hasNext()){
			String str = iter.next();
			newList.addLast(str);
		}
		return newList;
	}
	
}
