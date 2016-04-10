package com.porani.simpleime2;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by porani on 3/6/2016.
 */

public class smiley extends FragmentActivity implements KeyboardView.OnKeyboardActionListener{

        private PopupWindow popupWindow;
    int keyboardHeight = 230 ;
    private View popUpView;
    private smiley mSmiley;
    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);
    }

    public void smiley()
        {
            mSmiley= new smiley();
        }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {


    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
