package com.iteo.android_navigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iteo.android_navigation.R
import com.iteo.android_navigation.extensions.navigate
import kotlinx.android.synthetic.main.fragment_login.loginButton
import javax.inject.Inject

class LoginFragment @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory
) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            navigate(LoginFragmentDirections.openDashboard())
        }
    }
}
