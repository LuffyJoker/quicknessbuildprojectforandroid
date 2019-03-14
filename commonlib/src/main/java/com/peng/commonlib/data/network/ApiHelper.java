package com.peng.commonlib.data.network;


import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;

import retrofit2.Call;
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
    @Headers(value = HTTP_DEBUG_HEADER)
    @GET("device/find_device_status/{deviceNo}")
    Call<Resp<DemoEntity>> fetchBindingState(@Path("deviceNo") String deviceNo);
}
