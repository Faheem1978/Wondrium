package com.coding.app.common.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent ::class)
object AppModule {

    @Singleton
    @Provides
    @Named("str1")
    fun provideTestString() = "Injected string"



    @Singleton
    @Provides
    @Named("str2")
    fun provideTestStringSecond() = "Injected string second"
}