package info.ohgita.bincalc_android;

import com.actionbarsherlock.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainFragment extends Fragment {
	int selectedBasetypeId = -1; 
	static int ID_BASETYPE_BIN =	100;
	static int ID_BASETYPE_DEC =	200;
	static int ID_BASETYPE_HEX =	300;
	
	View v = null;
	
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_portrait, container);
		
		/* Event handler for Basetype toggles */
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
		
		/* return inflated view */
		return v;
	}
	
	/**
	 * switch base-type
	 * @param basetypeId	Base-type ID number
	 */
	public void switchBasetype(int basetypeId){
		selectedBasetypeId = basetypeId;
		
		ToggleButton tb_bin = (ToggleButton) v.findViewById(R.id.toggle_basetype_bin);
		ToggleButton tb_dec = (ToggleButton) v.findViewById(R.id.toggle_basetype_dec);
		ToggleButton tb_hex = (ToggleButton) v.findViewById(R.id.toggle_basetype_hex);
		tb_bin.setChecked(false);
		tb_dec.setChecked(false);
		tb_hex.setChecked(false);
		if(basetypeId == ID_BASETYPE_BIN){
			tb_bin.setChecked(true);
		}else if(basetypeId == ID_BASETYPE_DEC){
			tb_dec.setChecked(true);
		}else{
			tb_hex.setChecked(true);
		}
	}
}