package com.bigint.birthdayreminder

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.bigint.birthdayreminder.data.preferences.Prefs
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class BaseApplication : Application() {
    companion object {
        private lateinit var prefs: Prefs
        private lateinit var mContext: BaseApplication

        fun getString(id: Int): String {
//            var conf: Configuration = mContext.resources.configuration
//            conf = Configuration(conf)
//            conf.setLocale(
//                if (isEnglishSelected()) Locale(LANGUAGE_ENGLISH) else Locale(
//                    LANGUAGE_ARABIC
//                )
//            )
//            val localizedContext: Context = mContext.createConfigurationContext(conf)
            return mContext.resources.getString(id)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        prefs = Prefs(mContext)
    }
}