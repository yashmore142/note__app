package com.example.note_app_compose.sessionmanager


import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SessionManager(context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "user_session",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveUserId(uid: String) {
        sharedPreferences.edit().putString("uid", uid).apply()
    }

    fun getUserId(): String? = sharedPreferences.getString("uid", null)

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}
