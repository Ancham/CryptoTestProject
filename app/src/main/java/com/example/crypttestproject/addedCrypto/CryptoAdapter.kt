package com.example.crypttestproject.addedCrypto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.crypttestproject.room.data.CoinData

class CryptoAdapter(val context: Context, val data: ArrayList<CoinData>) : BaseAdapter() {
    lateinit var viewHolder: ViewHolder
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup): View? {
        return if (convertView == null) {
            val view = LayoutInflater.from(context)
                .inflate(com.example.crypttestproject.R.layout.list_view_item, container, false)

            viewHolder = ViewHolder(view)

            with(getItem(position) as CoinData) {
                viewHolder.dynamicCoinName.text = coin.name
                viewHolder.dynamicCoinSymbol.text = coin.symbol
                viewHolder.dynamicCoinId.text = coin.id
                view.tag = viewHolder
                view
            }

        } else {
            convertView
        }
    }


    class ViewHolder(view: View) {
        var dynamicCoinName: TextView
        var dynamicCoinSymbol: TextView
        var dynamicCoinId: TextView

        init {
            dynamicCoinName =
                view.findViewById(com.example.crypttestproject.R.id.dynamicCoinName)
            dynamicCoinSymbol =
                view.findViewById(com.example.crypttestproject.R.id.dynamicCoinSymbol)
            dynamicCoinId =
                view.findViewById(com.example.crypttestproject.R.id.dynamicCoinId)
        }
    }
}