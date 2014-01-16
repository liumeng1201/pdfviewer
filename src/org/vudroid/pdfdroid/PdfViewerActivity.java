package org.vudroid.pdfdroid;

import com.lm.pdfviewer.BaseViewerActivity;
import com.lm.pdfviewer.DecodeService;
import com.lm.pdfviewer.DecodeServiceBase;

import org.vudroid.pdfdroid.codec.PdfContext;

public class PdfViewerActivity extends BaseViewerActivity {
	@Override
	protected DecodeService createDecodeService() {
		return new DecodeServiceBase(new PdfContext());
	}
}
