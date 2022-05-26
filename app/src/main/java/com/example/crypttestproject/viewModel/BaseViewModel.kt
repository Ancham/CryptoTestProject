package com.example.crypttestproject.viewModel

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypttestproject.exceptions.InternalException

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState> = _uiState

    private val _exceptionOccured = MutableLiveData<InternalException>()
    val exceptionOccured = _exceptionOccured

    protected open fun setPendingState() {
        this._uiState.value = UiState.Pending
    }

    protected fun onFailure(throwable: Throwable) {
        throwable.message?.let { Log.e("Failure", it) }
    }

    protected open fun setIdleState() {
        this._uiState.value = UiState.Idle
    }

    open fun onBackPressed() {}


    suspend fun <T : Any> safeLaunch(
        exceptionSource: String,
        call: suspend () -> T
    ): T? {
        return try {
            call()
        } catch (exception: Exception) {
            handleError(
                InternalException(
                    exceptionSource = exceptionSource,
                    message = exception.stackTraceToString()
                )
            )
            return null
        }
    }

    private fun handleError(internalException: InternalException) {
        Log.e("Exception source", internalException.exceptionSource)
        Log.e("Exception message", internalException.stackTraceToString())
        _exceptionOccured.value = internalException
    }
}
