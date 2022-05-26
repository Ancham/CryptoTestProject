package com.example.crypttestproject.activities.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypttestproject.activities.addCryptoCurrencyActivity.AddCryptoCurrencyActivity
import com.example.crypttestproject.activities.baseActivity.BaseActivity
import com.example.crypttestproject.data.Coin
import com.example.crypttestproject.databinding.ActivityMainBinding
import com.example.crypttestproject.recycleView.OnItemClickListener
import com.example.crypttestproject.recycleView.RecycleViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var recycleViewAdapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        initObservers()

        binding.fab.setOnClickListener {
            Intent(this, AddCryptoCurrencyActivity::class.java)
                .run { startActivity(this) }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshTokensList()
    }

    private fun refreshTokensList() {
        viewModel.refreshAllCoins()
    }

    private fun initObservers() {
        getAllCoinsObserver()
    }

    private fun getAllCoinsObserver() {
        viewModel.allCoins.observe { coinList ->
            with(binding.cryptoInfoRecycleView) {
                recycleViewAdapter = RecycleViewAdapter(coinList)
                    .also {
                        it.setOnItemCheckListener(object : OnItemClickListener {
                            override fun onItemClick(coin: Coin) {
                                onShowItemName(coin)
                            }
                        })
                    }
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = recycleViewAdapter
            }

        }
    }

    private fun onShowItemName(coin: Coin) {
        toast("Coin: ${coin.name}")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}