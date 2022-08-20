package com.kurly.logickurly.data.model


import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Preferences private constructor(context: Context) {
    init {
        // 앱 설정 정보
        preferences = context.getSharedPreferences("InBodyTR_APP", Activity.MODE_PRIVATE)
        editor = preferences?.edit()

        // 데이터 접근권한 사용처
        dataAccessPref = context.getSharedPreferences("Data_Access_Place", Activity.MODE_PRIVATE)
        dataAccessEditor = dataAccessPref?.edit()

        // 사용자 정보
        dataUserInfoPref = context.getSharedPreferences("Data_User_Info", Activity.MODE_PRIVATE)
        dataUserInfoEditor = dataUserInfoPref?.edit()

    }


    /**
     * getDataUserInfoBooleanItem</br>
     *
     * @param key
     * @param defaultValue boolean 값을 가져온다.</br>
     */
    fun getUserInfoStringItem(key: String, defaultValue: String): String? {
        if (dataUserInfoPref == null)
            return defaultValue

        return dataUserInfoPref!!.getString(key, defaultValue)
    }

    /**
     * 내부 저장소(데이터 접근권한 사용처)에 해당 key에 value를 저장한다.
     *
     * @param key
     * @param value
     */
    fun putDataAccessBooleanItem(key: String, value: Boolean) {
        dataAccessEditor?.run {
            putBoolean(key, value)
            commit()
        }
    }

    /**
     * getDataAccessBooleanItem</br>
     *
     * @param key
     * @param defaultValue boolean 값을 가져온다.</br>
     */
    fun getDataAccessBooleanItem(key: String, defaultValue: Boolean): Boolean {
        if (dataAccessPref == null)
            return defaultValue

        return dataAccessPref!!.getBoolean(key, defaultValue)
    }

    /**
     * 내부 저장소에 해당 key에 value를 저장한다.
     *
     * @param key
     * @param value
     */
    fun putStringItem(key: String, value: String) {
        editor?.run {
            putString(key, value)
            commit()
        }
    }

    /**
     * String 값을 넣는다.</br>
     *
     * @param key
     * @param defaultValue
     * @return
     */
    fun getStringItem(key: String, defaultValue: String): String? {
        if (preferences == null)
            return defaultValue

        return preferences!!.getString(key, defaultValue)
    }

    /**
     * putBooleanItem</br>
     *
     * @param key
     * @param value void</br>
     *              boolean 값을 넣는다.</br>
     */
    fun putBooleanItem(key: String, value: Boolean) {
        editor?.run {
            putBoolean(key, value)
            commit()
        }
    }

    /**
     * getBooleanItem</br>
     *
     * @param key
     * @param defaultValue boolean 값을 가져온다.</br>
     */
    fun getBooleanItem(key: String, defaultValue: Boolean): Boolean {
        if (preferences == null)
            return defaultValue

        return preferences!!.getBoolean(key, defaultValue)
    }

    /**
     * putIntItem</br>
     *
     * @param key
     * @param value void</br>
     *              int 값을 넣는다.</br>
     */
    fun putIntItem(key: String, value: Int) {
        editor?.run {
            putInt(key, value)
            commit()
        }
    }

    /**
     * getIntItem</br>
     *
     * @param key
     * @param defaultValue int 값을 가져온다.</br>
     */
    fun getIntItem(key: String, defaultValue: Int): Int {
        if (preferences == null)
            return defaultValue

        return preferences!!.getInt(key, defaultValue)
    }

    /**
     * putFloatItem</br>
     *
     * @param key
     * @param value void</br>
     *              float 값을 넣는다.</br>
     */
    fun putFloatItem(key: String, value: Float) {
        editor?.run {
            putFloat(key, value)
            commit()
        }
    }

    /**
     * getFloatItem</br>
     *
     * @param key
     * @param defaultValue float 값을 가져온다.</br>
     */
    fun getFloatItem(key: String, defaultValue: Float): Float {
        if (preferences == null)
            return defaultValue

        return preferences!!.getFloat(key, defaultValue)
    }

    /**
     * deleteAllItem</br>
     *
     *
     * 전체 데이터 삭제</br>
     */
    fun deleteAllItem() {
        editor?.clear()
        dataAccessEditor?.clear()
        dataUserInfoEditor?.clear()
        editor?.commit()
        dataAccessEditor?.commit()
        dataUserInfoEditor?.commit()
    }

    companion object {
        // 앱 설정 정보
        private var editor: SharedPreferences.Editor? = null
        private var preferences: SharedPreferences? = null

        // 데이터 접근권한 사용처
        private var dataAccessEditor: SharedPreferences.Editor? = null
        private var dataAccessPref: SharedPreferences? = null

        private var dataUserInfoEditor: SharedPreferences.Editor? = null
        private var dataUserInfoPref: SharedPreferences? = null

        @Volatile private var instance: Preferences? = null

        @JvmStatic fun getInstance(context: Context): Preferences =
            instance ?: synchronized(this) {
                instance ?: Preferences(context).also {
                    instance = it
                }
            }
    }


}