package info.ohgita.bincalc_android.calc;

import java.util.Iterator;
import java.util.LinkedList;

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
	public String calculation(LinkedList<String> l){
		list = l;
		eval(list.iterator());
		return list.get(0).toString();
	}
	
	protected String eval(Iterator<String> iter){
		int i = 0, result = 0;
		while(iter.hasNext()){
			String str = iter.next();
			if(str.contentEquals("(")){
				eval(iter);
			} else {
				
			}
			i++;
		}
		return null;
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
