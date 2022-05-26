package com.example.crypttestproject.activities.addCryptoCurrencyActivity

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypttestproject.R
import com.example.crypttestproject.activities.baseActivity.BaseActivity
import com.example.crypttestproject.addCurrencyForm.AddNewCryptoCurrency
import com.example.crypttestproject.data.Coin
import com.example.crypttestproject.databinding.ActivityAddCryptoCurrencyBinding
import com.example.crypttestproject.recycleView.OnItemClickListener
import com.example.crypttestproject.recycleView.RecycleViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class AddCryptoCurrencyActivity : BaseActivity() {


    private val viewModel: AddCryptoCurrencyViewModel by viewModel()
    private lateinit var binding: ActivityAddCryptoCurrencyBinding

    private lateinit var recycleViewAdapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_crypto_currency)
        binding = ActivityAddCryptoCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservers()

        //Move it from here later
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recycleViewAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recycleViewAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun initListeners() {
//        TODO("Not yet implemented")
    }

    private fun initObservers() {
        initUpdateCryptoListObserver()
        initExceptionOccurredObserver()
    }

    private fun initUpdateCryptoListObserver() {
        viewModel.updatedCryptoList.observe { coinList ->
            with(binding.recycleView) {
                recycleViewAdapter = RecycleViewAdapter(coinList)
                    .also {
                        it.setOnItemCheckListener(object : OnItemClickListener {
                            override fun onItemClick(coin: Coin) {
                                onShowToastWithAddedCrypto(coin.name)
                                viewModel.addNewCryptoToDatabase(coin)
                            }
                        })
                    }
                layoutManager = LinearLayoutManager(this@AddCryptoCurrencyActivity)
                adapter = recycleViewAdapter
            }
        }
    }


    private fun onShowToastWithAddedCrypto(cryptoName: String) {
        toast("Added: ${cryptoName}")
    }

    private fun initExceptionOccurredObserver() {
        viewModel.exceptionOccured.observe {
            toast("Exception ${it.exceptionSource}")
        }
    }

    private fun startAddNewCryptoCurrencyActivity() {
        startActivity(AddNewCryptoCurrency.newIntent(this))
    }

}