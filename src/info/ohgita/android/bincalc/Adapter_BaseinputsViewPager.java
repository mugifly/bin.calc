package info.ohgita.android.bincalc;

import info.ohgita.android.bincalc.calculator.HistoryItem;
import info.ohgita.bincalc_android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class Adapter_BaseinputsViewPager extends PagerAdapter {
	public LayoutInflater inflater;
	public Context context;
	public Fragment_main mainFragment;
	
	final static int ID_BASETYPE_BIN =	100;
	final static int ID_BASETYPE_DEC =	200;
	final static int ID_BASETYPE_HEX =	300;
	
	public Adapter_BaseinputsViewPager(Context c, Fragment_main fragment) {
		super();
		context = c;
		mainFragment = fragment;
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@SuppressLint("NewApi")
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Log.d("binCalc","BaseinputsViewPager - instantiateItem - pos = " + position);
		
		/* Inflate the LinearLayout */
		LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.page_baseinputs, null);
		ll.setTag(position);
		
		/* Add a layout into the ViewPager */
		container.addView(ll);
		
		TableLayout tv = (TableLayout) ll.findViewById(R.id.tableLayout_baseinputs);
		
		EditText et_bin = (EditText) tv.findViewById(R.id.editText_baseinput_bin);
		EditText et_dec = (EditText) tv.findViewById(R.id.editText_baseinput_dec);
		EditText et_hex = (EditText) tv.findViewById(R.id.editText_baseinput_hex);
		
		/* Hide a on-screen keyboard on Base-input EditText */
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	    	et_bin.setTextIsSelectable(true);
	    	et_dec.setTextIsSelectable(true);
	    	et_hex.setTextIsSelectable(true);
		} else {
		    et_bin.setInputType(0);
		    et_dec.setInputType(0);
		    et_hex.setInputType(0);
		}

		/* Set event-handler to Base-input EditText */
		et_bin.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus){
		        	Log.d("binCalc","BaseinputsViewPager - EditText onFocus = true ... BIN");
		        	mainFragment.switchBasetype(Fragment_main.ID_BASETYPE_BIN);
		        }
		    }
		});
		et_dec.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus){
		        	Log.d("binCalc","BaseinputsViewPager - EditText onFocus = true ... DEC");
		        	mainFragment.switchBasetype(Fragment_main.ID_BASETYPE_DEC);
		        }
		    }
		});
		et_hex.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus){
		        	Log.d("binCalc","BaseinputsViewPager - EditText onFocus = true ... HEX");
		        	mainFragment.switchBasetype(Fragment_main.ID_BASETYPE_HEX);
		        }
		    }
		});
		
		/* Load history */
		Log.d("binCalc","BaseinputsViewPager - instantiateItem - Load history = " + position);
		if(position < mainFragment.calc.histories.size()){
			HistoryItem history = (HistoryItem) mainFragment.calc.histories.get(position);
			//mainFragment.selectedBasetypeId = history.basetype;
			switch (history.getBaseType()){
				case Adapter_BaseinputsViewPager.ID_BASETYPE_BIN:
					et_bin.setText(history.getNumberString());
					break;
				case Adapter_BaseinputsViewPager.ID_BASETYPE_DEC:
					et_dec.setText(history.getNumberString());
					break;
				case Adapter_BaseinputsViewPager.ID_BASETYPE_HEX:
					et_hex.setText(history.getNumberString());
					break;
			};
			mainFragment.baseConvert();
		}else{
			Log.d("binCalc","BaseinputsViewPager - instantiateItem - Initialize");
			Log.d("binCalc","BaseinputsViewPager - instantiateItem - selectedBasetypeId = " + mainFragment.selectedBasetypeId);
			// Set focus
			switch (mainFragment.selectedBasetypeId){
				case Adapter_BaseinputsViewPager.ID_BASETYPE_BIN:
					et_bin.requestFocus();
					break;
				case Adapter_BaseinputsViewPager.ID_BASETYPE_DEC:
					et_dec.requestFocus();
					break;
				case Adapter_BaseinputsViewPager.ID_BASETYPE_HEX:
					et_hex.requestFocus();
					break;
			};
			
			mainFragment.baseinputsViewPager_LinearLayout = ll;
			mainFragment.init();
		}
		
		Log.i("binCalc", "BaseinputsViewPager - instantiateItem - Done.");
		return ll;
    }
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
		Log.d("binCalc","BaseinputsViewPager - destroyItem - pos = " + position);
        ((ViewPager)container).removeView((View)object);
    }
	
	@Override
	public int getCount() {
		int n = mainFragment.calc.histories.size();
		if (n == 0) {
			n = 1;
		}
		Log.d("binCalc","BaseinputsViewPager - getCount - " + n);
		return n;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view.equals(obj);
	}
	
}
