package com.example.budgetplan.di

import com.example.budgetplan.presentation.RegistrationActivity
import dagger.Component

@Component(modules = [PresentationModule::class])
interface ApplicationComponent {

    fun inject(activity: RegistrationActivity)
}