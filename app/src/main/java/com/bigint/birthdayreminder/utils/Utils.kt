package com.bigint.birthdayreminder.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.math.BigInteger
import java.security.MessageDigest

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

fun openMobileAccessibilitySettings(context: Context) {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    context.startActivity(intent)
}

fun Any.toJsonString(): String = Gson().toJson(this)
fun <T> String.fromJsonString(clazz: Class<T>): T = Gson().fromJson(this, clazz)

inline fun <reified T> String.fromJsonStringToList(): List<T> {
    val gson = Gson()
    val listType = object : TypeToken<List<T>>() {}.type
    return gson.fromJson(this, listType)
}

inline fun <reified T> deserializeDataModel(dataModel: Any?): T? {
    if (dataModel == null) return null
    val gson = Gson()
    val dataModelJson = gson.toJson(dataModel)
    return try {
        gson.fromJson(dataModelJson, T::class.java)
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }
}

inline fun <reified T> deserializeDataModelList(dataModelList: Any?): List<T>? {
    if (dataModelList == null) return null

    val gson = Gson()
    val dataModelListJson = gson.toJson(dataModelList)

    return try {
        val typeToken = object : TypeToken<List<T>>() {}.type
        gson.fromJson(dataModelListJson, typeToken)
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }
}

fun openExternalBrowserLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}

fun Context.showToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun hideKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    val currentFocus = activity.currentFocus
    currentFocus?.let {
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun logging(msg: String?) {
    Log.d(
        "E_SERVICES XXXXX",
        "xxxxxxxxx=--=-=-==-==-=-=-=--==--=-==->>>>$msg<<<<<=--=-=-==-==-=-=-=--==--=-==-xxxxxxxxx"
    )
}

fun isInternetConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

