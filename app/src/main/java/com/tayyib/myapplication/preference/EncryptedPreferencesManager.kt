package com.tayyib.myapplication.preference
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
class EncryptedPreferencesManager(context:Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val sharedPreferences = EncryptedSharedPreferences(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private val editor = sharedPreferences.edit()
    fun saveTokenAndUserID(token: String, userID: String) {
        editor.putString("token", token)
        editor.putString("userID", userID)
        editor.apply()
    }
    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }
    fun getUserID(): String? {
        return sharedPreferences.getString("userID", null)
    }
    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}