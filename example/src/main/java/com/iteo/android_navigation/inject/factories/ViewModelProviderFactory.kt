package com.iteo.android_navigation.inject.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * View Model factory, which takes map of ViewModel class to ViewModel provider,
 * used later for providing instance of ViewModel.
 *
 * Annotation JvmSuppressWildcards is required,
 * ref: https://stackoverflow.com/questions/44638878/binding-into-map-with-kclass-type
 */
class ViewModelProviderFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators.get(modelClass)

        if (creator == null) {
            creator = creators.entries.find { modelClass.isAssignableFrom(it.key) }?.value
        }

        if (creator == null) {
            @Suppress("ThrowRuntimeException", "TooGenericExceptionThrown")
            throw RuntimeException("Unknown view model class $modelClass")
        }

        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }
}
