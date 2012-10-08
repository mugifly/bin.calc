package info.ohgita.bincalc_android;

/**
 * Bin.Calc - MainActivity
 * @author Masanori Ohgita
 */

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
	}
}