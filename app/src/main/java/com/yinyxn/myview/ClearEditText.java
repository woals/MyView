package com.yinyxn.myview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yinyxn on 2016/3/14.
 */
public class ClearEditText extends AppCompatEditText {

    // 清除文本内容的图标
    private Drawable clearIcon;

    /**
     * 构造方法【必需】
     *
     * @param context
     * @param attrs
     */
    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        // 加载图标
        clearIcon = ContextCompat.getDrawable(context, R.drawable.ic_clear_24dp);

        // 设置图标边框
        clearIcon.setBounds(
                0,
                0,
                clearIcon.getIntrinsicWidth(),
                clearIcon.getIntrinsicHeight());

        // 焦点改变监听器
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                setCompoundDrawables(
                        null,
                        null,
                        (hasFocus && (getText().length() > 0)) ? clearIcon : null,
                        null);
            }
        });

        // 点击监听器
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                GestureDetector detector = new GestureDetector(
//                        new GestureDetector.SimpleOnGestureListener() {
//
//                            @Override
//                            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                                return super.onFling(e1, e2, velocityX, velocityY);
//                            }
//
//                            @Override
//                            public void onLongPress(MotionEvent e) {
//                                super.onLongPress(e);
//                            }
//                        }
//                );
//
//                return detector.onTouchEvent(event);

                // 处理点击
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    // 点击图片区域
                    // TODO 关于内边距
                    if (event.getX() > getWidth() - clearIcon.getIntrinsicWidth()) {
                        setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        // 文本内容改变
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // 是否显示图标
                setCompoundDrawables(
                        null,
                        null,
                        s.length() > 0 ? clearIcon : null,
                        null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
