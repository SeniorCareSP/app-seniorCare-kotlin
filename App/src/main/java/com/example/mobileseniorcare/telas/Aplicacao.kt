package com.example.mobileseniorcare.telas

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
//import mobileseniorcare.di.moduloUsuario

class Aplicacao: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@Aplicacao)
            modules(moduloUsuario)
        }
    }
}