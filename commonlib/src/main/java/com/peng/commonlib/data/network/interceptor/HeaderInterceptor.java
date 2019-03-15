package com.peng.commonlib.data.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      请求头拦截器，用于给http请求添加 header 参数
 *      注释掉的部分，是根据具体 app 的所需参数进行添加，其目的就只有一个，就是为了给请求头 header 添加内容
 */
public class HeaderInterceptor implements Interceptor {

//    private EnvironmentRepo mEnvironmentRepo;
//    private JSON mJson;

    public HeaderInterceptor(
//            EnvironmentRepo environmentRepo
//            ,JSON json
    ){
//        mEnvironmentRepo = environmentRepo;
//        mJson = json;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        val originalRequest = chain.request()
//        val environmentInfo = environmentRepo.load().blockingGet()
//        val value = UserReq()
//        var userinfo = ""
//        environmentInfo?.run {
//            device?.run {
//                value.deviceId = deviceId
//            }
//            enterprise?.run {
//                value.enterpriseGuid = enterpriseGuid
//                enterpriseName?.run { value.enterpriseName = this }
//            }
//            store?.run {
//                value.storeGuid = storeGuid
//                storeName?.run { value.storeName = this }
//            }
//            user?.run {
//                value.userGuid = userGuid
//                userName?.run { value.userName = this }
//            }
//        }

//        value.userGuid = "123"
//        value.userGuid = "6456402437890380801"
//        value.userName = "123"
//        value.deviceType = 4
//        value.deviceId = "T0123456"
//        value.enterpriseGuid = "test"
//        value.enterpriseGuid = "6456402426814715905"
//        value.enterpriseName = "1"
//        value.storeName = "1"
//        value.storeGuid = "1234567897"
//        try {
//            userinfo = json.stringify(value)
//        }catch (e:Throwable){}
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .headers(originalRequest.headers())
                .header("Accept", "application/json;charset=utf-8")
                .header("Content-Type", "application/json;charset=utf-8")
//            .header("userInfo", userinfo) // todo 删除 mock 逻辑
                .method(originalRequest.method(), originalRequest.body());

        // TODO 使用正确 Token
        // 设置 token，用于登录
//        environmentInfo?.token?.let { requestBuilder.header(HeaderConstant.HEADER_TOKEN, it) }
//        requestBuilder.header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1NTI0NDM1MzEsImVudGVycHJpc2VHdWlkIjoiNjUwNjQzMTE5NTY1MTk4MjMzNyIsInVzZXJHdWlkIjoiNjUwODIyNTEyNDMzMDYzNTI2NSIsInN0b3JlTm8iOiI2MTQ4MTM5IiwiaXNzIjoiSG9sZGVyLmNvbSJ9.jYiwSWFapElWEI3qyDBqpAHsf4YukuuSxzFd3WCIhV0");
        requestBuilder.header("resourceLinkGuid", "");
        //设置设备的类型(source)
        requestBuilder.header("source", "6");
        // TODO 使用正确 EnterpriseGuid
        // 设置enterpriseGuid
//        environmentInfo?.enterprise?.enterpriseGuid?.let {
//            requestBuilder.header("EnterpriseGuid", it)
//        }
        requestBuilder.header("EnterpriseGuid", "506431195651982337");

        return chain.proceed(requestBuilder.build());
    }
}
