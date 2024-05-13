package com.bigint.birthdayreminder.utils

class Constants {
    object Keys {
        private const val KEYS_BASIC = "reminder_birthday_"
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ARABIC = "ar"
        const val KEY_AUTHENTICATION_TOKEN = KEYS_BASIC + "authentication_token_key"
        const val IS_SSO_ENABLED = KEYS_BASIC + "sso_enabled"
    }

    object BundleKeys {
        const val SELECTED_PROGRAM_STATUS_ID_KEY = "SELECTED_PROGRAM_ID_KEY"
    }
}