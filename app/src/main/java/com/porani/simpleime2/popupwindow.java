package com.porani.simpleime2;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by porani on 3/13/2016.
 */
public class popupwindow extends Activity{
    private PopupWindow popupWindow;
    private View popUpView;
    int keyboardHeight = 1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        popUpView = getLayoutInflater().inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(popUpView, LinearLayout.LayoutParams.MATCH_PARENT,
                (int) keyboardHeight, false);
        popupWindow.setContentView(popUpView);
        int h  = this.keyboardHeight;
        popupWindow.setHeight(h);
        //WebView webView = (WebView) popUpView;
        //  webView.loadUrl("http://www.google.com");
        // webView.getSettings().setJavaScriptEnabled(true);
       // popupWindow.showAtLocation(kv, Gravity.BOTTOM, 0, 0);



    }
}
