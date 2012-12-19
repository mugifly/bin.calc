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
	public String decToN(double dec, int n_adic){
		Log.d("binCalc", "BaseConverter.decToN("+dec+", "+n_adic+")");
		logClear();
		String ret = "";
		Integer value;
		
		int dec_int = (int) dec; // dec integer-part
		
		do{
			value =  (dec_int % n_adic);
			if(n_adic == 16 && value >= 10){ // if hex A-F...
				log(dec_int + " / " + n_adic + " ... " + value + " (" + (Character.toString((char) ('A'+ (value-10)))) + ")");
				ret = (Character.toString((char) ('A'+ (value-10)))) + ret;
			}else{ // other number...
				log(dec_int + " / " + n_adic + " ... " + value.toString());
				ret = value.toString() + ret;
			}
			dec_int /= n_adic;
		}while(dec_int >= n_adic-1);
		
		if(dec_int != 0){
			log("... "+dec_int);
			ret = dec_int + ret;
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
	 * Binary to decimal number
	 * @param bin	Source binary number (String)
	 * @return converted number
	 */
	public Double binToDec(String bin){
		Log.d("binCalc", "BaseConverter.binToDec("+bin+")");
		logClear();
		Double ret = 0.0;
		int digit = 0;
		for(int i=bin.length()-1;0<=i;i--){
			int d = Integer.parseInt(""+bin.charAt(i));
			double value = d* Math.pow(2, digit);
			log(d+"*2^"+digit + " = "+value);
			ret = value + ret;
			digit++;
		}
		
		return ret;
	}
	
	/**
	 * Hexadecimal to decimal number
	 * @param hex	Source hexadecimal number (String)
	 * @return converted number
	 */
	public Double hexToDec(String hex){
		Log.d("binCalc", "BaseConverter.hexToDec("+hex+")");
		logClear();
		Double ret = 0.0;
		int digit = 0;
		for(int i=hex.length()-1;0<=i;i--){
			int d;
			char c = hex.charAt(i);
			switch(c){
				case 'A':
					d = 10;
					break;
				case 'B':
					d = 11;
					break;
				case 'C':
					d = 12;
					break;
				case 'D':
					d = 13;
					break;
				case 'E':
					d = 14;
					break;
				case 'F':
					d = 15;
					break;
				default:
					d = Integer.parseInt(""+c);
			};
			double value = d* Math.pow(16, digit);
			log(d+"*16^"+digit + " = "+value);
			ret = value + ret;
			digit++;
		}
		
		return ret;
	}
	
	protected void log(String log){
		logs.add(log);
	}
	
	protected void logClear(){
		logs = new ArrayList<String>();
		logs.clear();
	}
}