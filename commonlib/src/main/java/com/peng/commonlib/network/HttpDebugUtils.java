package com.peng.commonlib.network;

import android.util.Log;

import com.facebook.stetho.server.http.HttpStatus;
import com.peng.commonlib.BaseApplication;
import com.peng.commonlib.BuildConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Mr.Q on 2019/2/24.
 * 描述：
 * 获取 http 网络请求的 debug 数据，所有数据将从本地进行读取，不进行网络请求
 */
public class HttpDebugUtils {
    /**
     * 如果是debug请求。那么请求体的 header 中应该存在这个标记，如果存该标记，则表示是一个 debug 请求，会从本地拉取数据
     * 注意：并不会关心这个 header 的值。只要带了这个标签即可
     */
    public static final String DEBUT_HEARD = "DEBUG_HEADER";

    /**
     * [DEBUT_HEARD] 对应的 value 信息。注意：头部是允许出现中文的
     */
    public static final String DEBUT_HEARD_VALUE = "Single interface debug mode is turned on";

    /**
     * 是否开启调试模式,T：开启，F：关闭
     */
    private static Boolean debug = null;

    /**
     * 初始化，注意：这个初始化只会在 [BuildConfig.DEBUG] 为 true 的情况下生效，线上版本是强制不生效的
     */
    public static void init(boolean debug) {
        if (HttpDebugUtils.debug != null) {
            try {
                throw new IllegalAccessException("HttpDebugUtils 调试工具只能初始化一次");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        HttpDebugUtils.debug = BuildConfig.DEBUG && debug;
        if (HttpDebugUtils.debug) {
            Log.e("HTTP_DEBUG", "http调试模式状态【打开】");
        } else {
            Log.e("HTTP_DEBUG", "http调试模式状态【关闭】");
        }
    }

    /**
     * 判断是否为调试模式
     */
    public static boolean isDebug() {
        if (debug == null) {
            try {
                throw new IllegalAccessException("请先初始化${HttpDebugUtils::class.java.name}");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return debug.booleanValue();
    }

    /**
     * 检查单个请求的 debug 模式，检查请求是否为 debug 模式，只要请求中带了[DEBUT_HEARD] 这个 header，那么认为是一个调试模式的数据
     * yes:如果是 debug 请求创建返回实体
     * no:返回 null
     */
    public static Response checkSingleRequestDebug(Request request) {
        if (!BuildConfig.DEBUG) {
            return null; //如果非调试模式，那么调试将不生效
        }
        for (String name : request.headers().names()) {
            if (DEBUT_HEARD == name) {
                return createResponse(request);
            }
        }
        return null;
    }

    /**
     * 创建一个debug的返回实体
     */
    public static Response createResponse(Request request) {
        if (!BuildConfig.DEBUG) {
            return null; //如果非调试模式，那么调试将不生效
        }
        String result = getDebugData(request);
        if (result == null) {
            return null;
        } else {
            return new Response.Builder()
                    .request(request)
                    .addHeader(DEBUT_HEARD, DEBUT_HEARD)
                    .addHeader("Content-Type", "application/json;charset=utf-8")
                    .body(ResponseBody.create(MediaType.parse("application/json"), result))
                    .protocol(Protocol.HTTP_2)
                    .code(HttpStatus.HTTP_OK)
                    .message("")
                    .build();
        }
    }

    /**
     * 全请求获取网络请求，这个方式适用于在开启调试模式的时候，所有请求都会进行 debug 获取数据
     * 只要开启了debug 模式，那么所有都是走 debug 请求，不会走网络请求的
     */
    private static String getDebugData(Request request) {
        StringBuffer strBuf = new StringBuffer();
        String tempStr;
        String urlPath;  //url的完整路径
        String[] urlPathArray = null; //url的“/”拆分后的路径
        String urlString = request.url().url().toString().replace(BuildConfig.HOST, "");

        urlPath = urlString.split("'?'")[0];
        urlPathArray = urlPath.split("/");

        InputStream inp = null;
        BufferedReader stringBuf = null;
        try {
            String dir = "debug_json";
            String[] paths = BaseApplication.getAppResources().getAssets().list(dir);
            String jsonFile = null; //json文件名称
            int isFindOk = 0; //用于判断是否成功匹配
            for (String path : paths) {
                String[] pathSplit = path.split("\\.")[0].split("\\$");
                isFindOk = 0;
                if (pathSplit.length == urlPathArray.length) {

                    for (int i = 0; i < pathSplit.length; i++) {
                        if (pathSplit[i].equals(urlPathArray[i]) || "%".equals(pathSplit[i])) {
                            isFindOk++; //每次匹配一项就会自增1
                        }
                    }

                    if (isFindOk == urlPathArray.length) {
                        //将asset文件转为绝对路径
                        jsonFile = dir + "/" + path;
                    }
                }
            }

            if (jsonFile == null) {
                throw new IllegalAccessException("HttpDebugUtils 没有找到指定接口的本地调试数据");
            }
            inp = BaseApplication.getAppResources().getAssets().open(jsonFile);
            stringBuf = new BufferedReader(new InputStreamReader(inp, "UTF-8"));
            while ((tempStr = stringBuf.readLine()) != null) {
                strBuf.append(tempStr);
            }
        } catch (Exception e) {
            try {
                throw new IllegalAccessException("HttpDebugUtils 读取本地调试数据异常");
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                inp.close();
            } catch (Exception e) {
            }
            try {
                stringBuf.close();
            } catch (Exception e) {
            }
        }
        return strBuf.toString();
    }

}
