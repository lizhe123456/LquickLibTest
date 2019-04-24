package com.empty.lquicklib.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.empty.lquicklib.model.IModel;

/**
 * Created by lizhe on 2019/4/2.
 *
 */

public class BaseViewModel<M extends IModel> extends AndroidViewModel{

    private M mModel;

    public BaseViewModel(@NonNull Application application) {
        super(application);

    }


}
