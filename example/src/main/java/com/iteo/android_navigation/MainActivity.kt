package com.iteo.android_navigation

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import com.jakewharton.rxrelay2.PublishRelay
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var newIntentSubject: PublishRelay<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent != null) {
            newIntentSubject.accept(intent)
        }
    }
}
