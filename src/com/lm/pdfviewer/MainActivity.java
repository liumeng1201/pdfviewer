package com.lm.pdfviewer;

import java.io.File;

import org.vudroid.pdfdroid.PdfViewerActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(MainActivity.this, PdfViewerActivity.class);
		File file = new File("/sdcard/aa.pdf");
		Uri uri = Uri.fromFile(file);
		intent.setData(uri);
		startActivity(intent);
	}

}
