package info.ohgita.bincalc_android;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

public class Adapter_BaseinputsViewPager extends PagerAdapter {
	public Context context;
	public Adapter_BaseinputsViewPager(Context c) {
		this.context = c;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		int[] pages = {R.layout.page_baseinputs, R.layout.page_baseinputs};
		
		/* TableLayout inflating */
		LayoutInflater inflater = (LayoutInflater)container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TableLayout tv = (TableLayout)inflater.inflate(pages[0], null);
		
		/* Event handler */
		
		/* TableLayout into container */
		container.addView(tv);
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
