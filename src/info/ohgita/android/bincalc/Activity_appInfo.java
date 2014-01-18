package info.ohgita.android.bincalc;

import info.ohgita.bincalc_android.R;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class Activity_appInfo extends SherlockActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.activity_appinfo);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		/* Load version */
		int version_code = 0;
		String version_name = "";
		try {
			PackageManager packageManager = this.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
			version_code = packageInfo.versionCode;
			version_name = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		TextView tv_version = (TextView)this.findViewById(R.id.textView_binCalcVersion);
		tv_version.setText(version_name);
		
		/* load license-page to webView */
		WebView wv = (WebView)findViewById(R.id.webView_license);
		wv.loadUrl("file:///android_asset/license.html");
	}
	
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch(item.getItemId()) {  
			case android.R.id.home:  
				finish();  
				return true;  
		}
		return super.onOptionsItemSelected(item);  
	}  
	
}
