package com.peng.commonlib.net.entity;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public class FindDeviceStatusNew {

    /**
     * code : 0
     * message : 成功
     * now : 0
     * tdata : {"enterpriseGuid":"6506431195651982337","storeNo":"6148139","storeGuid":"6506453252643487745","storeName":"门店0227_3","deviceNo":"B481EC12F76B7BA663E6D2735E9B45B6","deviceGuid":"1903051041406450008","binding":true,"register":true}
     */

    private int code;
    private String message;
    private int now;
    private TdataBean tdata;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public TdataBean getTdata() {
        return tdata;
    }

    public void setTdata(TdataBean tdata) {
        this.tdata = tdata;
    }

    public static class TdataBean {
        /**
         * enterpriseGuid : 6506431195651982337
         * storeNo : 6148139
         * storeGuid : 6506453252643487745
         * storeName : 门店0227_3
         * deviceNo : B481EC12F76B7BA663E6D2735E9B45B6
         * deviceGuid : 1903051041406450008
         * binding : true
         * register : true
         */

        private String enterpriseGuid;
        private String storeNo;
        private String storeGuid;
        private String storeName;
        private String deviceNo;
        private String deviceGuid;
        private boolean binding;
        private boolean register;

        public String getEnterpriseGuid() {
            return enterpriseGuid;
        }

        public void setEnterpriseGuid(String enterpriseGuid) {
            this.enterpriseGuid = enterpriseGuid;
        }

        public String getStoreNo() {
            return storeNo;
        }

        public void setStoreNo(String storeNo) {
            this.storeNo = storeNo;
        }

        public String getStoreGuid() {
            return storeGuid;
        }

        public void setStoreGuid(String storeGuid) {
            this.storeGuid = storeGuid;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getDeviceGuid() {
            return deviceGuid;
        }

        public void setDeviceGuid(String deviceGuid) {
            this.deviceGuid = deviceGuid;
        }

        public boolean isBinding() {
            return binding;
        }

        public void setBinding(boolean binding) {
            this.binding = binding;
        }

        public boolean isRegister() {
            return register;
        }

        public void setRegister(boolean register) {
            this.register = register;
        }
    }
}
