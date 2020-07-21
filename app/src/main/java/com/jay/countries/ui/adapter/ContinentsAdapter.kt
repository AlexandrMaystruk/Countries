package com.jay.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.countries.R
import com.jay.countries.util.diffutil.BaseDiffUtil
import com.jay.countries.util.diffutil.ContinentsDiffUtil

class ContinentsAdapter: RecyclerView.Adapter<BaseViewHolder<GetContinentsQuery.Continent>>() {

    val clickObserver = MutableLiveData<GetContinentsQuery.Continent>()
    private val continentsDataList: MutableList<GetContinentsQuery.Continent> = mutableListOf()
    private val diffUtil : BaseDiffUtil<GetContinentsQuery.Continent> = ContinentsDiffUtil()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseViewHolder<GetContinentsQuery.Continent> {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_continent, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<GetContinentsQuery.Continent>, position: Int) {
        holder.bind(continentsDataList[position])
    }

    override fun getItemCount(): Int = continentsDataList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun setData(data: MutableList<GetContinentsQuery.Continent?>) {
        diffUtil.setData(oldList = continentsDataList, newList = data.filterNotNull())
        continentsDataList.apply { clear(); addAll(data.filterNotNull()) }
        DiffUtil.calculateDiff(diffUtil, true).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<GetContinentsQuery.Continent>(itemView) {

        private val continentName: TextView = itemView.findViewById(R.id.continent_name)
        private val continentCode: TextView = itemView.findViewById(R.id.continent_code)
        private val rootView: View = itemView.findViewById(R.id.root_view)

        override fun bind(item: GetContinentsQuery.Continent) {
            continentName.text = item.name()
            continentCode.text = item.code()

            clickHandler(item)
        }

        private fun clickHandler(item: GetContinentsQuery.Continent) {
            rootView.setOnClickListener {
                clickObserver.value = item
                
            }
        }
    }
}