package com.bigint.birthdayreminder.data.preferences

import android.content.Context

class Prefs(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun removePref(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    /*
        contains(key: String)Use this method to check if the preferences file contains a specific preference
        edit()Use this method to create a new Editor, through which you can make atomic changes to the data in the SharedPreferences file
        getAll()Use this method to retrieve all values in the SharedPreferences file
        getInt(key: String, defValue: Integer)Use this method to retrieve an Integer value from the SharedPreferences file
        getBoolean(key: String, defValue: Boolean)Use this method to retrieve a Boolean value from the SharedPreferences file
        getFloat(key: String, defValue: Float)Use this method to retrieve a Float value from the SharedPreferences file
        getLong(key: String, defValue: Long)Use this method to retrieve a Long value from the SharedPreferences file
        getString(key: String, defValue: String)Use this method to retrieve a String value from the SharedPreferences file
        getStringSet(key: String, defValues: Set)Use this method to retrieve a set of String values from the SharedPreferences file
    */

    /*
        putInt(key: String, value: Integer)Use this method to insert an Integer value on the SharedPreferences file
        putBoolean(key: String, value: Boolean)Use this method to insert a Boolean value on the SharedPreferences file
        putFloat(key: String, value: Float)Use this method to insert a Float value on the SharedPreferences file
        putLong(key: String, value: Long)Use this method to insert a Long value on the SharedPreferences file
        putString(key: String, value: String)Use this method to insert a String value on the SharedPreferences file
        putStringSet(key: String, values: Set)Use this method to insert a set of String values on the SharedPreferences file
    */
}