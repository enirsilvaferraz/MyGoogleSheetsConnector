package com.eferraz.mygooglesheetsconnector

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppApplication @Inject constructor() : Application()