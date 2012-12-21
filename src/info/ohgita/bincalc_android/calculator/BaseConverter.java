package info.ohgita.bincalc_android.calculator;

import java.util.ArrayList;
import android.util.Log;

/**
 * Base number converter class
 * 
 * Base number convert from LinkedList (Parsed decimal numerical formula).
 * 
 * @author Masanori Ohgita
 * 
 */
public class BaseConverter {
	public ArrayList<String> logs;
	
	/**
	 * Decimal number to N-adic number
	 * @param num	Source decimal number
	 * @param n_adic N-adic number
	 * @return converted number
	 */
	public String decToN(double num, int n_adic){
		Log.d("binCalc", "BaseConverter.decToN("+num+", "+n_adic+")");
		logClear();
		
		String ret_int = ""; // converted n-Adic number integer-part
		String ret_dec = "";// converted n-Adic number decimal-places-part (binary/hexadecimal fraction part)
		
		/* Separate fraction part */
		int num_int = (int) num; // base-10 number integer-part
		double num_dec = num % 1.0; // base-10 number decimal-places-part
		
		/* Process for integer part */
		Integer value;
		do{
			value =  num_int % n_adic;
			if(n_adic == 16 && value >= 10){ // if hex A-F...
				log(num_int + " / " + n_adic + " ... " + value + " (" + (Character.toString((char) ('A'+ (value-10)))) + ")");
				ret_int = (Character.toString((char) ('A'+ (value-10)))) + ret_int;
			}else{ // other number...
				log(num_int + " / " + n_adic + " ... " + value.toString());
				ret_int = value.toString() + ret_int;
			}
			num_int /= n_adic;
		}while(num_int >= n_adic-1);
		
		if(num_int != 0){
			log("... "+num_int);
			ret_int = num_int + ret_int;
		}
		
		/* Process for decimal places part */
		if(num_dec != 0.0){
			ret_dec += ".";
		}
		while(num_dec != 0.0){
			value = (int) (num_dec * (double)n_adic);
			if(n_adic == 16 && value >= 10){ // if hex A-F...
				log(num_dec + " * " + n_adic + "  =  " + value + " (" + (Character.toString((char) ('A'+ (value-10)))) + ")");
				ret_dec += (Character.toString((char) ('A'+ (value-10))));
			}else{ // other number...
				log(num_dec + " * " + n_adic + " = " + value.toString());
				ret_dec += value.toString();
			}
			num_dec = (num_dec * (double)n_adic) % 1.0;
		}
		
		/* Zero padding */
		if(n_adic == 2){
			ret_int = binZeroPadding(ret_int);
		}
		
		/* Make return value */
		String ret = ret_int;
		if(ret_dec.length() != 0){
			ret += ret_dec;
		}

		log("=> "+ret+"("+n_adic+")");
		return ret.toString();
	}
	
	/**
	 * Zero-padding for Binary number 
	 * @param num Binary number
	 * @return Zero-padding number
	 */
	public String binZeroPadding(String num) {
		while(num.length() %4 != 0){
			num = "0" + num;
		}
		while(num.indexOf("0000") == 0){
			num = num.substring(4);
		}
		if(num.length() == 0){
			num = "0000";
		}
		return num;
	}

	/**
	 * Binary to decimal number
	 * @param bin	Source binary number (String)
	 * @return converted number
	 */
	public Double binToDec(String bin){
		Log.d("binCalc", "BaseConverter.binToDec("+bin+")");
		logClear();

		double ret = 0; // converted base-10 number
		
		/* Separate fraction part */
		String num_bin = bin; // binary part
		String num_dec = ""; // binary fraction part
		if(bin.indexOf('.') != -1){
			num_bin = bin.substring(0, bin.indexOf('.'));
			num_dec = bin.substring(bin.indexOf('.') + 1);
			Log.d("binCalc", "  num_bin = " + num_bin + ", num_dec = " + num_dec);
		}
		
		/* Process for binary part */
		int digit = 0;
		for(int i=num_bin.length()-1;0<=i;i--){
			int d = Integer.parseInt(""+num_bin.charAt(i));
			double value = d* Math.pow(2, digit);
			log(d+"*2^"+digit + " = "+value);
			ret = (int)value + ret;
			digit++;
		}
		
		/* Process for binary fraction part */
		digit = 1;
		for(int i=0;i<num_dec.length();i++){
			int d = Integer.parseInt(""+num_dec.charAt(i));
			double value = d* Math.pow(2, -digit);
			log(d+"*2^"+-digit + " = "+value);
			ret += value;
			digit++;
		}
		
		log("=> "+ret+"(2)");
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

		double ret = 0.0;
		
		/* Separate fraction part */
		String num_hex = hex; // hexadecimal part
		String num_dec = ""; // hexadecimal fraction part
		if(hex.indexOf('.') != -1){
			num_hex = hex.substring(0, hex.indexOf('.'));
			num_dec = hex.substring(hex.indexOf('.') + 1);
			Log.d("binCalc", "  num_hex = " + num_hex + ", num_dec = " + num_dec);
		}

		/* Process for hexadecimal part */
		int digit = 0;
		for(int i=num_hex.length()-1;0<=i;i--){
			int d = hexCharToInt(num_hex.charAt(i));
			double value = d* Math.pow(16, digit);
			log(d+"*16^"+digit + " = "+value);
			ret = value + ret;
			digit++;
		}
		
		/* Process for hexadecimal fraction part */
		digit = 1;
		for(int i=0;i<num_dec.length();i++){
			int d = hexCharToInt(num_dec.charAt(i));
			double value = d* Math.pow(16, -digit);
			log(d+"*16^"+-digit + " = "+value);
			ret += value;
			digit++;
		}

		log("=> "+ret+"(10)");
		return ret;
	}
	
	protected int hexCharToInt(char hex_char){
		int d = 0;
		switch(hex_char){
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
				d = Integer.parseInt("" + hex_char);
		};
		return d;
	}
	
	protected void log(String log){
		logs.add(log);
	}
	
	protected void logClear(){
		logs = new ArrayList<String>();
		logs.clear();
	}
}