package com.empty.lquicklib.error;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import io.reactivex.exceptions.CompositeException;
import retrofit2.HttpException;

/**
 * Created by lizhe on 2019/3/21.
 * Http错误
 */

public class HttpError {

    //HTTP错误
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    //解析错误
    private static final int PARSE_ERROR = 1000;
    //网络错误
    private static final int NETWORK_ERROR = 1001;
    //协议出错
    private static final int HTTP_ERROR = 1002;
    //服务器出错
    private static final int RESULT_ERROR = 1003;
    //未知错误
    public static final int UNKNOWN = 1004;
    //緩存错误
    private static final int CACHE_ERROR = 1005;

    public static ApiException handleThrowable(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                    ex.setCode(REQUEST_TIMEOUT);
                    ex.setDisplayMessage("请求超时");
                    break;
                case GATEWAY_TIMEOUT:
                    ex.setCode(NETWORK_ERROR);
                    ex.setDisplayMessage("无网络连接");
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.setCode(RESULT_ERROR);
                    ex.setDisplayMessage("服务器连接失败");
                    break;
                case BAD_GATEWAY:
                    break;
                case SERVICE_UNAVAILABLE:
                    break;
                default:
                    ex.setDisplayMessage("服务器正在抢修中");
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, PARSE_ERROR);
            ex.setDisplayMessage("数据解析失败");
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, HTTP_ERROR);
            ex.setDisplayMessage("服务器连接失败");
        } else if (e instanceof ExecutionException
                || e instanceof InterruptedException) {
            ex = new ApiException(e, UNKNOWN);
            ex.setDisplayMessage("未知错误");
        } else if (e instanceof CompositeException) {
            CompositeException compositeE = (CompositeException) e;
            ex = new ApiException(e);
            for (Throwable throwable : compositeE.getExceptions()) {
                if (throwable instanceof SocketTimeoutException) {
                    ex.setCode(NETWORK_ERROR);
                    ex.setDisplayMessage("无网络连接");
                } else if (throwable instanceof ConnectException) {
                    ex.setCode(NETWORK_ERROR);
                    ex.setDisplayMessage("无网络连接");
                } else if (throwable instanceof UnknownHostException) {
                    ex.setCode(NETWORK_ERROR);
                    ex.setDisplayMessage("无网络连接");
                }else if (throwable instanceof MalformedJsonException) {
                    ex.setCode(PARSE_ERROR);
                    ex.setDisplayMessage("数据解析失败");
                }
            }
        }else if (e instanceof SysException){
            ex = new ApiException(e, 966);
            ex.setDisplayMessage("服务器内部错误");
        }else {
            ex = new ApiException(e, UNKNOWN);
            ex.setDisplayMessage("位置错误");
//            LogUtil.e("http",e);
        }
        return ex;

    }

}
