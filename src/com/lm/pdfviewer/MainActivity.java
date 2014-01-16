package com.lm.pdfviewer;

import java.io.File;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File file = new File("/sdcard/aa.pdf");
		Uri uri = Uri.fromFile(file);
//		Intent intent = new Intent(MainActivity.this, PdfViewerActivity.class);
//		intent.setData(uri);
//		startActivity(intent);
		String filename = uri.getLastPathSegment();
		Fragment fragment = new PdfViewerFragment();
		Bundle bundle = new Bundle();
		bundle.putString("filepath", "/sdcard/aa.pdf");
		fragment.setArguments(bundle);
		
		FragmentManager fm = getFragmentManager();
		fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
	}

}
