package com.example.budgetplan.di

import android.app.Application
import com.example.budgetplan.presentation.RegistrationActivity
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideRegistrationViewModel(): Application{
        return Application()
    }
}