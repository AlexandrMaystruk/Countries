package com.jay.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jay.countries.R

class CountriesAdapter : RecyclerView.Adapter<BaseViewHolder<FindCountriesOfAContinentQuery.Country>>() {

    private val countriesList: MutableList<FindCountriesOfAContinentQuery.Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseViewHolder<FindCountriesOfAContinentQuery.Country> {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_country_name, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FindCountriesOfAContinentQuery.Country>,
                                  position: Int) {

    }

    override fun getItemCount(): Int = countriesList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun setData(data: MutableList<FindCountriesOfAContinentQuery.Country?>) {
        countriesList.apply { clear(); addAll(data.filterNotNull()) }
    }


    inner class ViewHolder(itemView: View) :
        BaseViewHolder<FindCountriesOfAContinentQuery.Country>(itemView) {

        override fun bind(item: FindCountriesOfAContinentQuery.Country) {

        }
    }
}
