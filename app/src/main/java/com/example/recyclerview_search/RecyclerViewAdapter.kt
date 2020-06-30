package com.example.recyclerview_search

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val list: ArrayList<Movie>) : RecyclerView.Adapter<RecyclerViewHolder>(), Filterable {

    private var listFiltered: ArrayList<Movie> = ArrayList<Movie>()

    init {
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val movie: Movie = listFiltered[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    listFiltered = list
                } else {
                    val resultList = ArrayList<Movie>()
                    for (row in list) {
                        if (row.title.contains(charString, ignoreCase = true)) {
                            Log.d(ContentValues.TAG, row.title)
                            Log.d(ContentValues.TAG, charString)
                            resultList.add(row)
                        }
                    }
                    listFiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results?.values as ArrayList<Movie>

                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }

}