package com.mj.shishicai.iwebview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.mj.shishicai.tools.LogUtil;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * author: Rea.X
 * date: 2017/8/8.
 */

public class IWebChromeClient extends WebChromeClient {
    private Context context;
    private WebView webView;
    private IWebviewFragment fragment;
    private FrameLayout fullFramlayout;
    private ProgressBar lineProgressbar;
    private ProgressBar circleProgressbar;
    private WebConfig webConfig;
    private IX5WebChromeClient.CustomViewCallback callback;
    private boolean isShowLineProgress, isShowCircleProgress;


    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;


    public IWebChromeClient(IWebviewFragment fragment) {
        this.fragment = fragment;
        this.context = fragment.getWebView().getContext();
        this.webView = fragment.getWebView();
        this.fullFramlayout = fragment.getfullFramlayout();
        this.lineProgressbar = fragment.getlineProgressbar();
        this.circleProgressbar = fragment.getlineProgressbar();
        this.webConfig = IWebviewApplication.getWebConfig();
        this.isShowLineProgress = this.webConfig.showLineLoading();
        this.isShowCircleProgress = this.webConfig.showCircleLoading();
    }


    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        super.onProgressChanged(webView, newProgress);
        if (isShowLineProgress) {
            this.lineProgressbar.setVisibility(View.VISIBLE);
            this.lineProgressbar.setProgress(newProgress);
        }
        if (newProgress >= 80) {
            this.circleProgressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        LogUtil.e("onConsoleMessage message->" + consoleMessage.message());
        LogUtil.e("onConsoleMessage sourceId->" + consoleMessage.sourceId());
        LogUtil.e("onConsoleMessage lineNumber->" + consoleMessage.lineNumber());
        LogUtil.e("onConsoleMessage messageLevel->" + consoleMessage.messageLevel());
        return super.onConsoleMessage(consoleMessage);
    }

    @Override
    public void onHideCustomView() {
        if (callback != null) {
            callback.onCustomViewHidden();
        }
        webView.setVisibility(View.VISIBLE);
        fullFramlayout.removeAllViews();
        fullFramlayout.setVisibility(View.GONE);
        super.onHideCustomView();
    }


    @Override
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        webView.setVisibility(View.GONE);
        fullFramlayout.setVisibility(View.VISIBLE);
        fullFramlayout.removeAllViews();
        fullFramlayout.addView(view);
        callback = customViewCallback;
        super.onShowCustomView(view, customViewCallback);
    }

    @Override
    public void onShowCustomView(View view, int i, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        webView.setVisibility(View.GONE);
        fullFramlayout.setVisibility(View.VISIBLE);
        fullFramlayout.removeAllViews();
        fullFramlayout.addView(view);
        callback = customViewCallback;
        super.onShowCustomView(view, i, customViewCallback);
    }

    private File createImageFile() throws IOException {
        return new File(Environment.getExternalStorageDirectory(), new Date().getTime() + ".jpg");
    }

    public static final int INPUT_FILE_REQUEST_CODE = 1;
    public final static int FILECHOOSER_RESULTCODE = 2;

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//        if (listener != null)
//            listener.fileChooser();
        if (mFilePathCallback != null) {
            mFilePathCallback.onReceiveValue(null);
        }
        mFilePathCallback = filePathCallback;

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("WebViewSetting", "Unable to create Image File", ex);
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            } else {
                takePictureIntent = null;
            }
        }

        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent[] intentArray;
        if (takePictureIntent != null) {
            intentArray = new Intent[]{takePictureIntent};
        } else {
            intentArray = new Intent[0];
        }

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
        fragment.startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
        return true;
    }

    //The undocumented magic method override
    //Eclipse will swear at you if you try to put @Override here
    // For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        fragment.startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILECHOOSER_RESULTCODE);

    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        fragment.startActivityForResult(
                Intent.createChooser(i, "Image Chooser"),
                FILECHOOSER_RESULTCODE);
    }

    //For Android 4.1
    @Override
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        fragment.startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILECHOOSER_RESULTCODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage) return;
            Uri result = data == null || resultCode != RESULT_OK ? null
                    : data.getData();
            if (result != null) {
                String imagePath = ImageFilePath.getPath(fragment.getContext(), result);
                if (!TextUtils.isEmpty(imagePath)) {
                    result = Uri.parse("file:///" + imagePath);
                }
            }
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else if (requestCode == INPUT_FILE_REQUEST_CODE && mFilePathCallback != null) {
            // 5.0的回调
            Uri[] results = null;

            // Check that the response is a good one
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }

            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;
        }
    }

}
