package com.example.crypttestproject.activities.baseActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.crypttestproject.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    protected inline fun <T> LiveData<T>.observe(crossinline onChanged: (T) -> Unit) =
        Observer<T> { observer -> onChanged.invoke(observer) }
            .apply { observe(this@BaseActivity, this) }

    protected fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}