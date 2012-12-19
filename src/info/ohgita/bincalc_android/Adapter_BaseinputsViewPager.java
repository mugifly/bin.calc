package info.ohgita.bincalc_android;

import com.actionbarsherlock.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;

public class Adapter_BaseinputsViewPager extends PagerAdapter implements OnClickListener, OnTouchListener {
	public LayoutInflater inflater;
	public Context context;
	public MainFragment mainFragment;
	private TableLayout tv;
	
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
		tv = (TableLayout)inflater.inflate(pages[0], null);
		
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
		
		/* Set event-handler to Base-inputs backspace button (ImageButton) */
		ImageView bs_bin = (ImageView) tv.findViewById(R.id.ImageButton_baseinput_bs_bin);
		ImageView bs_dec = (ImageView) tv.findViewById(R.id.ImageButton_baseinput_bs_dec);
		ImageView bs_hex = (ImageView) tv.findViewById(R.id.ImageButton_baseinput_bs_hex);
		bs_bin.setOnClickListener(this);
		bs_dec.setOnClickListener(this);
		bs_hex.setOnClickListener(this);
		bs_bin.setOnTouchListener(this);
		bs_dec.setOnTouchListener(this);
		bs_hex.setOnTouchListener(this);
		
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
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.ImageButton_baseinput_bs_dec:
				inputBackspace();
				break;
			case R.id.ImageButton_baseinput_bs_bin:
				inputBackspace();
				break;
			case R.id.ImageButton_baseinput_bs_hex:
				inputBackspace();
				break;
		}
	}
	
	public void inputBackspace(){
		mainFragment.inputBackspace();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageButton bs_bin = (ImageButton) mainFragment.getView().findViewById(R.id.ImageButton_baseinput_bs_bin);
		ImageButton bs_dec = (ImageButton) mainFragment.getView().findViewById(R.id.ImageButton_baseinput_bs_dec);
		ImageButton bs_hex = (ImageButton) mainFragment.getView().findViewById(R.id.ImageButton_baseinput_bs_hex);
		if(v.getId() == R.id.ImageButton_baseinput_bs_dec ||
				v.getId() == R.id.ImageButton_baseinput_bs_bin ||
				v.getId() == R.id.ImageButton_baseinput_bs_hex
			){
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					bs_dec.setPressed(true);
					bs_bin.setPressed(true);
					bs_hex.setPressed(true);
				}else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL){
					bs_dec.setPressed(false);
					bs_bin.setPressed(false);
					bs_hex.setPressed(false);
				}
		}
		return false;
	}

}
