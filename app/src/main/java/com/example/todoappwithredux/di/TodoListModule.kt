package com.example.todoappwithredux.di

import com.example.todoappwithredux.ui.AppStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object TodoListModule {

    @Provides
    fun provideAppStore(): AppStore {
        return AppStore.getInstance()
    }
}