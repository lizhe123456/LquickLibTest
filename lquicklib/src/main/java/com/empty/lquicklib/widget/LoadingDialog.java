package com.empty.lquicklib.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.empty.lquicklib.R;

/**
 * Created by lizhe on 2019/3/21.
 *
 */

public class LoadingDialog extends Dialog {
    public TextView msgText;


    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dialog_loading,null);
        setContentView(view);
        msgText = (TextView) view.findViewById(R.id.tipTextView);
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     * @param message 给用户展示的提示信息
     *
     */
    public LoadingDialog setMessage(String message) {
        msgText.setText(message);
        return this;
    }


    public void showDialog() {
        if (isShowing()){
            cancel();
        }
        show();
    }
}
