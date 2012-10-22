package info.ohgita.bincalc_android.calc;

import android.util.Log;

/**
 * Base number converter class
 * @author masanori
 */
public class BaseConverter {
	public String log;
	
	/**
	 * Decimal to N-adic number
	 * @param dec	Source decimal number
	 * @param n_adic N-adic number
	 * @return converted number
	 */
	public String decToN(int dec, int n_adic){
		String ret = "";
		Integer value;
		do{
			value = (dec % n_adic);
			if(n_adic == 16 && value >= 10){ // if hex A-F...
				ret = (Character.toString((char) ('A'+ (value-10)))) + ret;
				Log.i("binCalc","ret16: "+ret);
			}else{ // other number...
				ret = value.toString() + ret;
				Log.i("binCalc","ret: "+ret);
			}
			dec /= n_adic;
		}while(dec >= n_adic-1);
		
		/*zero padding */
		if(n_adic == 2){
			while(ret.length() %4 != 0){
				ret = "0"+ret;
			}
		}
		
		return ret.toString();
	}
}