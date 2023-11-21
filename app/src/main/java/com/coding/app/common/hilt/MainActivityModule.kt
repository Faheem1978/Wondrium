package com.coding.app.common.hilt

import android.content.Context
import com.coding.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @ActivityScoped
    @Provides
    @Named("string3")
    fun getMainString(@ApplicationContext context: Context, @Named("str1") testString:String) = "${context.getString(
        R.string.get_main_string)}  -  $testString"


}