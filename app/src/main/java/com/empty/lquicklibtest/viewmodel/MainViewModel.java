package com.empty.lquicklibtest.viewmodel;


import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.empty.lquicklib.base.BaseViewModel;
import com.empty.lquicklib.binding.command.BindingAction;
import com.empty.lquicklib.binding.command.BindingCommand;
import com.empty.lquicklib.http.HttpRequest;
import com.empty.lquicklib.intermediary.CommonSubscriber;
import com.empty.lquicklib.intermediary.RxSchedulersHelper;
import com.empty.lquicklibtest.api.ApiSerice;
import com.empty.lquicklibtest.bean.MainBean;
import org.reactivestreams.Subscription;
import io.reactivex.functions.Consumer;


/**
 * Created by lizhe on 2019/4/24.
 *
 */

public class MainViewModel extends BaseViewModel {

    public ObservableField<MainBean> entity = new ObservableField<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setEntity(MainBean entity) {
        this.entity.set(entity);
    }

    public BindingCommand test = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            test();
        }
    });

    private void test() {
        HttpRequest.getInstance().create(ApiSerice.class)
                .get()
                .compose(RxSchedulersHelper.bindToLifecycle(getLifecycleProvider()))
                .compose(RxSchedulersHelper.schedulersTransformer())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        showDialog();
                    }
                })
                .subscribeWith(new CommonSubscriber<MainBean>() {
                    @Override
                    public void onNext(MainBean o) {
//                        mainBeanMutableLiveData.postValue(o);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void registerRxBus() {
        super.registerRxBus();
    }


    public void req() {

    }


}
