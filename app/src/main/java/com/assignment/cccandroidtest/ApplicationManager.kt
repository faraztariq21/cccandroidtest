package com.assignment.cccandroidtest

import android.app.Application

class ApplicationManager : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: ApplicationManager
    }
}