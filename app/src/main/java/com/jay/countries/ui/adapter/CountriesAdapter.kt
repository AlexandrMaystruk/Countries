package com.jay.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView
import com.jay.countries.R
import com.jay.countries.model.Country

open class CountriesAdapter : RecyclerView.Adapter<BaseViewHolder<Country>>(), LifecycleObserver {

    private val countryList: MutableList<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Country> {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_coutnry, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Country>, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int = countryList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun setCountries(data: List<Country>) {
        countryList.apply { clear(); addAll(data) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        BaseViewHolder<Country>(itemView) {

        private val countryName: TextView = itemView.findViewById(R.id.country_name)
        private val native: TextView = itemView.findViewById(R.id.native_lang)
        private val phone: TextView = itemView.findViewById(R.id.phone_code)
        private val currency: TextView = itemView.findViewById(R.id.currency_code)

        override fun bind(item: Country) {
            countryName.text = item.name
            native.text = item.native
            phone.text = item.phone
            currency.text = item.currency
        }
    }
}
