package com.iteo.android_navigation.extensions

import androidx.fragment.app.Fragment

// works only on dialogs (finds last started fragment - dialog parent)
fun Fragment.getPreviousFragment(): Fragment {
    val index = requireFragmentManager().fragments.indexOf(this)
    return requireFragmentManager().fragments[index - 1]
}
