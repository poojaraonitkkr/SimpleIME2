package com.porani.simpleime2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by porani on 2/28/2016.
 */
public class SimpleIME2 extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener{

    private KeyboardView kv;
    private Keyboard keyboard;
       smiley mSmiley;
    private boolean caps = false;
    private RelativeLayout parentLayout;
    private PopupWindow popupWindow;
    private View popUpView;
    int keyboardHeight = 1000 ;
    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case 0:
            {
                 popUpView = getLayoutInflater().inflate(R.layout.popup, null);

                WebView webView = (WebView)popUpView;
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("http://www.google.com");
                webView.setWebViewClient(new WebViewClient()
                {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url)
                    {
                        view.loadUrl(url);

                        return true;
                    }
                });
       // popUpView = getLayoutInflater().inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(popUpView, LinearLayout.LayoutParams.MATCH_PARENT,
                (int) keyboardHeight, false);
        popupWindow.setContentView(popUpView);
        int h  = this.keyboardHeight;
                WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                int height = display.getHeight();
        popupWindow.setHeight(height-h);
        popupWindow.showAtLocation(kv, Gravity.TOP, 0, 0);

/*                Context context = this;
              /*  Intent intent = new Intent(context, popupwindow.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); */
            }
            break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown()  {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }


    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;


            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
}
