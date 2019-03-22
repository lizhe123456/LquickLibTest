package com.empty.lquicklibtest.ui;

import com.empty.lquicklib.themvp.databind.DataBindActivity;
import com.empty.lquicklib.themvp.databind.DataBinder;

/**
 * Created by lizhe on 2019/3/22.
 *
 */

public class TextActivity extends DataBindActivity<TextDelegate>{

    @Override
    public DataBinder getDataBinder() {
        return new TextDataBinder();
    }

    @Override
    protected Class<TextDelegate> getDelegateClass() {
        return TextDelegate.class;
    }

    public void getUserList(){

    }

}
