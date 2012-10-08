package info.ohgita.bincalc_android;

/**
 * Bin.Calc - MainActivity
 * @author Masanori Ohgita
 */

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends SherlockFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* SubMenu button */
		SubMenu sub_menu = menu.addSubMenu("Overflow Item");
		sub_menu.getItem().setIcon(R.drawable.ic_action_overflow);
		sub_menu.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		sub_menu.add("Log");
		
		/* All clear button*/
		menu.add("AllClear")
			.setIcon(R.drawable.button_allclear)
			.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
}