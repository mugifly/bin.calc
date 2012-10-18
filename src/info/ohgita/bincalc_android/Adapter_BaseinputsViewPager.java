package info.ohgita.bincalc_android;

import com.actionbarsherlock.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Adapter_BaseinputsViewPager extends PagerAdapter {
	public LayoutInflater inflater;
	public Context context;
	public MainFragment mainFragment;
	
	public Adapter_BaseinputsViewPager(Context c, MainFragment fragment) {
		super();
		context = c;
		mainFragment = fragment;
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@SuppressLint("NewApi")
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		int[] pages = {R.layout.page_baseinputs, R.layout.page_baseinputs};
		
		/* TableLayout inflating */
		TableLayout tv = (TableLayout)inflater.inflate(pages[0], null);
		
		/* TableLayout into container */
		container.addView(tv);
				
		/* Set event-handler to Base-input EditText */
		EditText et_bin = (EditText) tv.findViewById(R.id.editText_baseinput_bin);
		EditText et_dec = (EditText) tv.findViewById(R.id.editText_baseinput_dec);
		EditText et_hex = (EditText) tv.findViewById(R.id.editText_baseinput_hex);
		et_bin.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus)
		        	mainFragment.switchBasetype(MainFragment.ID_BASETYPE_BIN);
		    }
		});
		et_dec.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus)
		        	mainFragment.switchBasetype(MainFragment.ID_BASETYPE_DEC);
		    }
		});
		et_hex.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
		    public void onFocusChange(View v, boolean isFocus) {
		        if(isFocus)
		        	mainFragment.switchBasetype(MainFragment.ID_BASETYPE_HEX);
		    }
		});
		
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
		
		return tv;
    }
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }
	
	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view.equals(obj);
	}

}