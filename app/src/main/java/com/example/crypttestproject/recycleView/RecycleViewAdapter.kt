package com.example.crypttestproject.recycleView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crypttestproject.R
import com.example.crypttestproject.data.Coin
import com.google.gson.Gson
import org.koin.core.KoinComponent

class RecycleViewAdapter(private var dataSet: List<Coin>) :
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>(), Filterable, KoinComponent {

    private var dataSetFiltered: MutableList<Coin>
    private var searchingPhraseLength = 0
    private val gson by lazy { Gson() }

    private lateinit var onItemClickListener: OnItemClickListener

    init {
        dataSetFiltered = dataSet.toMutableList()
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_item_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.textView) {
            text = dataSetFiltered[position].name

            setOnClickListener {
                if (holder.adapterPosition == position) {
                    Log.e("Test", "Selected item --------- START")
                    Log.e("Test", gson.toJson(dataSetFiltered[position]))
                    Log.e("Test", "Selected item --------- END")
                    onItemClickListener.onItemClick(dataSetFiltered[position])
                }
            }
        }
    }

    fun setOnItemCheckListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    override fun getItemCount(): Int = dataSetFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(searchingPhrase: CharSequence?): FilterResults {
                val stringToSearch = searchingPhrase.toString()
                val resultList: ArrayList<Coin> = arrayListOf()


                if (stringToSearch.isEmpty()) {
                    dataSetFiltered.addAll(dataSet)
                } else if ((searchingPhrase?.length ?: 0) > searchingPhraseLength) {
                    filterData(resultList, stringToSearch)
                } else {
                    dataSetFiltered = dataSet.toMutableList()
                    filterData(resultList, stringToSearch)
                }

                searchingPhraseLength = stringToSearch.length


                val filterResults = FilterResults()
                filterResults.values = dataSetFiltered
                return filterResults
            }

            private fun filterData(
                resultList: ArrayList<Coin>,
                stringToSearch: String
            ) {
                resultList.addAll(dataSetFiltered.filter { it.name.contains(stringToSearch) })
                dataSetFiltered = resultList
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                dataSetFiltered = filterResults?.values as MutableList<Coin>
                notifyDataSetChanged()
            }

            override fun convertResultToString(resultValue: Any?): CharSequence {
                return super.convertResultToString(resultValue)
            }
        }
    }
}