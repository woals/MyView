package com.yinyxn.myview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by yinyxn on 2016/3/14.
 */
public class ShowHideEditText extends AppCompatEditText {

    // 清除文本内容的图标
    private Drawable showeyeIcon;
    private Drawable hideeyeIcon;
    private EditText editText;
    private boolean flag;
    /**
     * 构造方法【必需】
     *
     * @param context
     * @param attrs
     */
    public ShowHideEditText(Context context, AttributeSet attrs) {
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
        showeyeIcon = ContextCompat.getDrawable(context, R.drawable.ic_visibility_24dp);
        hideeyeIcon = ContextCompat.getDrawable(context, R.drawable.ic_visibility_off_24dp);
        editText = (EditText) findViewById(R.id.editText_password);

        // 设置图标边框
        showeyeIcon.setBounds(0, 0, showeyeIcon.getIntrinsicWidth(), showeyeIcon.getIntrinsicHeight());
        hideeyeIcon.setBounds(0, 0, hideeyeIcon.getIntrinsicWidth(), hideeyeIcon.getIntrinsicHeight());
        setCompoundDrawables(null,null,showeyeIcon,null);
        flag = true;
        // 点击监听器
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 处理点击
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    // 点击图片区域
                    // TODO 关于内边距
                    if (event.getX() > getWidth() - showeyeIcon.getIntrinsicWidth()) {
                        if (flag){
                            setCompoundDrawables(null, null, hideeyeIcon, null);
                            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            flag = false;
                            return true;
                        }else {
                            setCompoundDrawables(null, null, showeyeIcon, null);
                            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            flag = true;
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }
}
