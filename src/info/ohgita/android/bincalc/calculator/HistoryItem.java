package info.ohgita.android.bincalc.calculator;

import java.util.HashMap;

/**
 * Container for the history of the calculator
 */
public class HistoryItem {
	private HashMap<String, String> logger;
	private HashMap<Integer, String> values;
	/* Main(selected) base-type */
	private int baseType;
	
	/**
	 * Constructor
	 */
	public HistoryItem() {
		logger = new HashMap<String, String>();
		values = new HashMap<Integer, String>();
	}
	
	/**
	 * Get the log
	 * @return 
	 */
	public String getLog(int base_type) {
		return logger.get("convert_" + base_type);
	}
	
	/**
	 * Put a string into the log
	 * @param base_type
	 * @param str
	 */
	public void putLog(int base_type, String str) {
		logger.put("convert_"+base_type, str);
	}
	
	/**
	 * Get a base-type
	 * @return Base-type
	 */
	public int getBaseType() {
		return baseType;
	}
	
	/**
	 * Set a base-type
	 * @param base_type_	Base-type
	 */
	public void setBaseType(int base_type_) {
		baseType = base_type_;
	}
	
	
	/**
	 * Get a string of the a main base-type number
	 */
	public String getNumberString() {
		return values.get(baseType);
	}
	
	/**
	 * Get a string of a number
	 */
	public String getNumberString(int base_type) {
		return values.get(base_type);
	}
	
	/**
	 * Set a string of the a main base-type number
	 */
	public void setNumberString(String number_str) {
		values.put(baseType, number_str);
	}
	
	/**
	 * Set a string of a number
	 */
	public void setNumberString(int base_type, String number_str) {
		values.put(base_type, number_str);
	}
}
