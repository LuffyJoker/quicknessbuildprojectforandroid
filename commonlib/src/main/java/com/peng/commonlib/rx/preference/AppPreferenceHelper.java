package com.peng.commonlib.rx.preference;

import com.peng.commonlib.constant.PrefKeyConstant;
import com.peng.commonlib.database.entity.Question;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *  数据层的 SharedPreference 操作默认实现
 */
public class AppPreferenceHelper implements PreferenceHelper {

    @Inject
    public AppPreferenceHelper() {
        @Named(PrefKeyConstant.NETWORK_IS_AVAILABLE) PreferenceAdapter<Boolean> networkIsAvailablePreference;
        @Named(PrefKeyConstant.QUESTION) PreferenceAdapter<Question> questionPreference;
//        @Named(PrefKeyConstant.SNACK_DISH_CACHE_JSON) PreferenceAdapter<ItemAndTypeForAndroidRespDTO> snackDishPreference:,
//        @Named(PrefKeyConstant.SAVE_LOGIN_USER_NAME) PreferenceAdapter<String> loginUserInfoPreference,
//        @Named(PrefKeyConstant.USER_NAME) PreferenceAdapter<String> userNamePreference ,
//        @Named(PrefKeyConstant.USER_PASSWORD) PreferenceAdapter<String> userPasswordPreference ,
//        @Named(PrefKeyConstant.REMEMBER_PASSWORD) PreferenceAdapter<Boolean> rememberPasswordPreference ,
//        @Named(PrefKeyConstant.EMPLOYEE_PERMISSION_DATA) PreferenceAdapter<AioPermission> employeePermissionPreference ,
//        @Named(PrefKeyConstant.SUPER_USER_ACCOUNT) PreferenceAdapter<String> superAdminPreference,
//        @Named(PrefKeyConstant.MEMORY_DELETE_DISH) PreferenceAdapter<Boolean> memoryDeleteDish
    }

    @Override
    public Observable<Question> getQuestion() {
//        return questionPreference.stream;
        return null;
    }

    @Override
    public void saveQuestion(Question question) {
//        questionPreference.value = question;
    }
}
