package com.empty.apppay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import static com.empty.apppay.Constant.PAY_WX;
import static com.empty.apppay.Constant.PAY_ZFB;


/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/06/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PayUtils {

    public static final String EVENT_WECHAT_PAY_SUCCESS = "wechat_pay_success";

    public static final String EVENT_WECHAT_PAY_FAILURE = "wechat_pay_failure";

    public static final String EVENT_WECHAT_PAY_CANCLE = "wechat_pay_cancel";

    private static PayCallback payCallback;

    public PayUtils() {
        EventBus.getDefault().register(this);
    }

    private void payWeChat(Context context, final String packageValue, final String sign, final String partnerId, final String prepayId, final String nonceStr, final String timeStamp) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context,Constant.WX_APP_ID);
        api.registerApp(Constant.WX_APP_ID);
        PayReq req = new PayReq();
        req.appId = Constant.WX_APP_ID;
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.packageValue = packageValue;
        req.nonceStr = nonceStr;
        req.timeStamp = timeStamp;
        req.sign = sign;
        api.sendReq(req);

    }


    /**
     * @param payType        1-支付宝，2-微信
     * @param sign 后台给的签名文件
     */
    @SuppressLint("WrongConstant")
    public void playPay(Context context, final int payType, final String sign, final PayCallback objectCallback) {

        if (payType == PAY_WX && !WxPayUtils.isWeixinAvilible(context)) {
            Toast.makeText(context,"微信未安装，无法支付",Toast.LENGTH_SHORT).show();
            return;
        }

        switch (payType) {
            //支付宝
            case PAY_ZFB:
                zfb(context, sign, objectCallback);
                break;
            //微信
            case PAY_WX:
                wx(context, sign, objectCallback);
                break;
            default:
                break;
        }
    }

    /**
     * 微信的支付
     * @param sign 后台给的签名文件
     */
    private void wx(Context context, String sign, PayCallback payCallback) {
        WeCharPayBean baseBean1 = JSON.parseObject(sign, WeCharPayBean.class);
        if (baseBean1 == null) {
            return;
        }

        WeCharPayBean.DataBean data = baseBean1.getData();
        payWeChat(context, data.getPackageValue(), data.getSign(), data.getPartnerId(),
                data.getPrepayId(), data.getNonceStr(), data.getTimeStamp());
        PayUtils.payCallback = payCallback;
    }

    /**
     * 支付宝的支付
     *
     * @param sign 后台给的签名文件
     */
    private void zfb(Context context, String sign, final PayCallback objectCallback) {
        Observable.create(new AliPayObserver(sign, (Activity) context))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String stringStringMap) {

                        PayResult payResult = new PayResult(stringStringMap);
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            objectCallback.onSuccess("支付成功");
                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            objectCallback.onFailure(0, payResult.getMemo());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 微信支付回调
     *
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onWechatPayEvent(String event) {
        switch (event) {
            case PayUtils.EVENT_WECHAT_PAY_SUCCESS:
                if (payCallback != null) {
                    payCallback.onSuccess("支付成功");
                }
                break;
            case PayUtils.EVENT_WECHAT_PAY_FAILURE:
                if (payCallback != null) {
                    payCallback.onFailure(0, "支付失败");
                }
                break;
            case PayUtils.EVENT_WECHAT_PAY_CANCLE:
                if (payCallback != null) {
                    payCallback.onFailure(0, "支付取消");
                }
                break;
            default:
                if (payCallback != null) {
                    payCallback.onFailure(0, "支付失败");
                }
                break;
        }

        EventBus.getDefault().unregister(this);
        payCallback = null;
    }

}
