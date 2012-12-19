package info.ohgita.bincalc_android.calc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

/**
 * Base number converter class
 * @author masanori
 */
public class BaseConverter {
	public ArrayList<String> logs;
	
	/**
	 * Decimal to N-adic number
	 * @param dec	Source decimal number
	 * @param n_adic N-adic number
	 * @return converted number
	 */
	public String decToN(int dec, int n_adic){
		Log.d("binCalc", "BaseConverter.decToN("+dec+", "+n_adic+")");
		logClear();
		String ret = "";
		Integer value;
		do{
			value = (dec % n_adic);
			if(n_adic == 16 && value >= 10){ // if hex A-F...
				log(dec + " / " + n_adic + " ... " + value + " (" + (Character.toString((char) ('A'+ (value-10)))) + ")");
				ret = (Character.toString((char) ('A'+ (value-10)))) + ret;
			}else{ // other number...
				log(dec + " / " + n_adic + " ... " + value.toString());
				ret = value.toString() + ret;
			}
			dec /= n_adic;
		}while(dec >= n_adic-1);
		
		if(dec != 0){
			log("... "+dec);
			ret = dec + ret;
		}
		
		/*zero padding */
		if(n_adic == 2){
			while(ret.length() %4 != 0){
				ret = "0"+ret;
			}
		}
		
		return ret.toString();
	}
	
	/**
	 * Binary to N-adic number
	 * @param bin	Source binary number (String)
	 * @param n_adic N-adic number
	 * @return converted number
	 */
	public String binToDec(String bin){
		Log.d("binCalc", "BaseConverter.binToDec("+bin+")");
		logClear();
		Double ret = 0.0;
		int digit = 0;
		for(int i=bin.length()-1;0<=i;i--){
			int d = Integer.parseInt(""+bin.charAt(i));
			log("bin % 10 = " + d);
			double value = d* Math.pow(2, digit);
			log(d+"*2^"+digit + " = "+value);
			ret = value + ret;
			digit++;
		}
		
		return ret.toString();
	}
	
	protected void log(String log){
		logs.add(log);
	}
	
	protected void logClear(){
		logs = new ArrayList<String>();
		logs.clear();
	}
}