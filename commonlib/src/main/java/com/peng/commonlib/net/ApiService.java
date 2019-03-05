package com.peng.commonlib.net;

import com.peng.commonlib.net.entity.FindDeviceStatusNew;
import com.peng.commonlib.net.entity.Resp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public interface ApiService {

    /**
     * 根据设备 Id 查询设备的注册、绑定状态
     * 分为未注册、已注册未绑定、已注册已绑定三种情况
     * [deviceNo] 厂商设备编号  (driverId)
     */
    @GET("device/find_device_status/{deviceNo}")
    Single<Resp<FindDeviceStatusNew>> findTerminalStatusNew(@Path("deviceNo")String deviceNo);
}
