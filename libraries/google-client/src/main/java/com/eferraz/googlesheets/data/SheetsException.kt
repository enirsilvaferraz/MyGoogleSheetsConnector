package com.eferraz.googlesheets.data

import android.content.Intent
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException

class SheetsException(throwable: Throwable, val intent: Intent? = null) : Throwable(throwable) {
    companion object {
        fun Throwable.resolve(): SheetsException = when (this) {
            is UserRecoverableAuthIOException -> SheetsException(this, intent)
            is UserRecoverableAuthException -> SheetsException(this, intent)
            else -> SheetsException(this)
        }
    }
}