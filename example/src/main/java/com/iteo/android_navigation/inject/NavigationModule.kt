package com.iteo.android_navigation.inject

import android.content.Intent
import com.jakewharton.rxrelay2.PublishRelay
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Observable
import javax.inject.Singleton

@Module
object NavigationModule {
    @Provides
    @Singleton
    fun provideNewIntentRelay(): PublishRelay<Intent> = PublishRelay.create()

    @Provides
    fun provideNewIntentObservable(intentRelay: PublishRelay<Intent>): Observable<Intent> = intentRelay

}
