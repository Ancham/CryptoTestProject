package com.example.crypttestproject.addCurrencyForm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crypttestproject.R
import com.example.crypttestproject.databinding.ActivityAddNewCryptoCurrencyBinding
import org.koin.core.KoinComponent

class AddNewCryptoCurrency : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityAddNewCryptoCurrencyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_crypto_currency)
        binding = ActivityAddNewCryptoCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNewCryptoCurrency::class.java)
        }
    }
}