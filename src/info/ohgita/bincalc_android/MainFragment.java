package info.ohgita.bincalc_android;

import com.actionbarsherlock.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainFragment extends Fragment implements OnClickListener {
	int selectedBasetypeId = -1; 
	static int ID_BASETYPE_BIN =	100;
	static int ID_BASETYPE_DEC =	200;
	static int ID_BASETYPE_HEX =	300;
	
	View v = null;
	
	@SuppressLint("NewApi")
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_main_portrait, container);
		
		/* Event handler for Base-type ToggleButtons */
		final ToggleButton tb_bin = (ToggleButton) v.findViewById(R.id.toggle_basetype_bin);
		final ToggleButton tb_dec = (ToggleButton) v.findViewById(R.id.toggle_basetype_dec);
		final ToggleButton tb_hex = (ToggleButton) v.findViewById(R.id.toggle_basetype_hex);
		tb_bin.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked == true){
					switchBasetype(ID_BASETYPE_BIN);
				}
			}
		});
		tb_dec.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked == true){
					switchBasetype(ID_BASETYPE_DEC);
				}
			}
		});
		tb_hex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked == true){
					switchBasetype(ID_BASETYPE_HEX);
				}
			}
		});
		
		/* Event handler for Base-number EditTexts */
		final EditText et_bin = (EditText) v.findViewById(R.id.editText_basetype_bin);
		final EditText et_dec = (EditText) v.findViewById(R.id.editText_basetype_dec);
		final EditText et_hex = (EditText) v.findViewById(R.id.editText_basetype_hex);
		et_bin.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus)
		        	switchBasetype(ID_BASETYPE_BIN);
		    }
		});
		et_dec.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus)
		        	switchBasetype(ID_BASETYPE_DEC);
		    }
		});
		et_hex.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus)
		        	switchBasetype(ID_BASETYPE_HEX);
		    }
		});
		
		/* Hide a on-screen keyboard */
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	    	et_bin.setTextIsSelectable(true);
	    	et_dec.setTextIsSelectable(true);
	    	et_hex.setTextIsSelectable(true);
		} else {
		    et_bin.setInputType(0);
		    et_dec.setInputType(0);
		    et_hex.setInputType(0);
		}
		
		/* set Event-handler for key-buttons */
		v.findViewById(R.id.keyButton0).setOnClickListener(this);
		v.findViewById(R.id.keyButton1).setOnClickListener(this);
		v.findViewById(R.id.keyButton2).setOnClickListener(this);
		v.findViewById(R.id.keyButton3).setOnClickListener(this);
		v.findViewById(R.id.keyButton4).setOnClickListener(this);
		v.findViewById(R.id.keyButton5).setOnClickListener(this);
		v.findViewById(R.id.keyButton6).setOnClickListener(this);
		v.findViewById(R.id.keyButton7).setOnClickListener(this);
		v.findViewById(R.id.keyButton8).setOnClickListener(this);
		v.findViewById(R.id.keyButton9).setOnClickListener(this);
		
		/* return inflated view */
		return v;
	}
	
	/**
	 * calculate base-number
	 */
	public void calculate( ){
		if(selectedBasetypeId == ID_BASETYPE_BIN){
			
		}else if(selectedBasetypeId == ID_BASETYPE_DEC){
			
		}else if(selectedBasetypeId == ID_BASETYPE_HEX){
			
		}
	}
	
	/** All-Clear calculator
	 */
	public void inputAllClear(){
		EditText et = getCurrent_Basenumber_EditText();
		et.setText("0");
		calculate();
	}
	
	/**
	 * input base-number key
	 * @param str input-Key
	 */
	public void inputBasenumber(String str){
		EditText et = getCurrent_Basenumber_EditText();
		et.setText(et.getText() + str);
		calculate();
	}
	
	/**
	 * get current base-number(EditText) object
	 */
	public EditText getCurrent_Basenumber_EditText(){
		if(selectedBasetypeId == ID_BASETYPE_BIN){
			return (EditText) v.findViewById(R.id.editText_basetype_bin);
		}else if(selectedBasetypeId == ID_BASETYPE_DEC){
			return (EditText) v.findViewById(R.id.editText_basetype_dec);
		}else if(selectedBasetypeId == ID_BASETYPE_HEX){
			return (EditText) v.findViewById(R.id.editText_basetype_hex);
		}
		return null;
	}
	
	/**
	 * get current base-type ToggleButton object
	 */
	public ToggleButton getCurrent_Basetype_ToggleButton(){
		if(selectedBasetypeId == ID_BASETYPE_BIN){
			return (ToggleButton) v.findViewById(R.id.toggle_basetype_bin);
		}else if(selectedBasetypeId == ID_BASETYPE_DEC){
			return (ToggleButton) v.findViewById(R.id.toggle_basetype_dec);
		}else if(selectedBasetypeId == ID_BASETYPE_HEX){
			return (ToggleButton) v.findViewById(R.id.toggle_basetype_hex);
		}
		return null;
	}
	
	/**
	 * get current base-type(container) TableRow object
	 */
	public TableRow getCurrent_Basetype_TableRow(){
		if(selectedBasetypeId == ID_BASETYPE_BIN){
			return (TableRow) v.findViewById(R.id.tableRow_basetype_bin);
		}else if(selectedBasetypeId == ID_BASETYPE_DEC){
			return (TableRow) v.findViewById(R.id.tableRow_basetype_dec);
		}else if(selectedBasetypeId == ID_BASETYPE_HEX){
			return (TableRow) v.findViewById(R.id.tableRow_basetype_hex);
		}
		return null;
	}
	
	/**
	 * switch base-type
	 * @param basetypeId	Base-type ID number
	 */
	public void switchBasetype(int basetypeId){
		selectedBasetypeId = basetypeId;
		TableRow tr_bin = (TableRow) v.findViewById(R.id.tableRow_basetype_bin);
		TableRow tr_dec = (TableRow) v.findViewById(R.id.tableRow_basetype_dec);
		TableRow tr_hex = (TableRow) v.findViewById(R.id.tableRow_basetype_hex);
		ToggleButton tb_bin = (ToggleButton) v.findViewById(R.id.toggle_basetype_bin);
		ToggleButton tb_dec = (ToggleButton) v.findViewById(R.id.toggle_basetype_dec);
		ToggleButton tb_hex = (ToggleButton) v.findViewById(R.id.toggle_basetype_hex);
		
		/* reset */
		tr_bin.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.basetype_line_default));
		tr_dec.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.basetype_line_default));
		tr_hex.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.basetype_line_default));
		tb_bin.setChecked(false);
		tb_dec.setChecked(false);
		tb_hex.setChecked(false);
		
		/* activate Base-type */
		getCurrent_Basetype_ToggleButton().setChecked(true);
		getCurrent_Basetype_TableRow().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.basetype_line_active));
	}

	/* Event-handler for buttons */
	@Override
	public void onClick(View v) {
		/* Key-buttons (0-9) */
		switch(v.getId()){
			case R.id.keyButton0:
				inputBasenumber("0");
				break;
			case R.id.keyButton1:
				inputBasenumber("1");
				break;
			case R.id.keyButton2:
				inputBasenumber("2");
				break;
			case R.id.keyButton3:
				inputBasenumber("3");
				break;
			case R.id.keyButton4:
				inputBasenumber("4");
				break;
			case R.id.keyButton5:
				inputBasenumber("5");
				break;
			case R.id.keyButton6:
				inputBasenumber("6");
				break;
			case R.id.keyButton7:
				inputBasenumber("7");
				break;
			case R.id.keyButton8:
				inputBasenumber("8");
				break;
			case R.id.keyButton9:
				inputBasenumber("9");
				break;
		};
	}
}