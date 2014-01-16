package com.lm.pdfviewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lm.pdfviewer.models.CurrentPageModel;
import com.lm.pdfviewer.models.DecodingProgressModel;
import com.lm.pdfviewer.models.ZoomModel;

public abstract class BaseViewerActivity extends Activity {
	private DecodeService decodeService;
	private DocumentView documentView;
	private CurrentPageModel currentPageModel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initDecodeService();
		final ZoomModel zoomModel = new ZoomModel();
		final DecodingProgressModel progressModel = new DecodingProgressModel();
		currentPageModel = new CurrentPageModel();
		documentView = new DocumentView(this, zoomModel, progressModel,
				currentPageModel);
		zoomModel.addEventListener(documentView);
		documentView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		decodeService.setContentResolver(getContentResolver());
		decodeService.setContainerView(documentView);
		documentView.setDecodeService(decodeService);
		decodeService.open(getIntent().getData());

		final FrameLayout frameLayout = createMainContainer();
		frameLayout.addView(documentView);
		setContentView(frameLayout);

		documentView.showDocument();
	}

	private void setWindowTitle() {
		final String name = getIntent().getData().getLastPathSegment();
		getWindow().setTitle(name);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setWindowTitle();
	}

	private FrameLayout createMainContainer() {
		return new FrameLayout(this);
	}

	private void initDecodeService() {
		if (decodeService == null) {
			decodeService = createDecodeService();
		}
	}

	protected abstract DecodeService createDecodeService();

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		decodeService.recycle();
		decodeService = null;
		super.onDestroy();
	}
}
