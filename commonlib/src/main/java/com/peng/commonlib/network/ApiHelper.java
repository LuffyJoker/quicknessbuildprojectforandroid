package com.peng.commonlib.network;


import com.peng.commonlib.network.entity.FindDeviceStatusNew;
import com.peng.commonlib.network.entity.Resp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Mr.Q on 2019/2/24.
 * 描述：
 *  http 请求的统一管理处
 */
public interface ApiHelper {

    String HTTP_DEBUG_HEADER = HttpDebugUtils.DEBUT_HEARD + ":" + HttpDebugUtils.DEBUT_HEARD_VALUE;

    /**
     * 根据设备 Id 查询设备的注册、绑定状态
     * 分为未注册、已注册未绑定、已注册已绑定三种情况
     * [deviceNo] 厂商设备编号  (driverId)
     */
//    @Headers(value = HTTP_DEBUG_HEADER)
    @GET("user/find_device_status/{deviceNo}")
    Single<Resp<FindDeviceStatusNew>> fetchBindingState(@Path("deviceNo") String deviceNo);
}
