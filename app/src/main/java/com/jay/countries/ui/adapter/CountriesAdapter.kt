package com.jay.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.jay.countries.R
import com.jay.countries.repository.database.countries.Country
import com.jay.countries.repository.database.countries.CountriesDatabaseManager

open class CountriesAdapter : RecyclerView.Adapter<BaseViewHolder<Country>>(), LifecycleObserver {

    private val countryList: MutableList<Country> = mutableListOf()
//    private val countriesDbManager = CountriesDatabaseManager.instance

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

        private var isLiked = false
        private val countryName: TextView = itemView.findViewById(R.id.country_name)
//        private val likeBtn: AppCompatImageButton = itemView.findViewById(R.id.like_btn)

        override fun bind(item: Country) {
            countryName.text = item.name

//            if (isLiked) {
//                setLikedButtonDrawable(R.drawable.ic_heart_red)
//            }
//
//            onLikeButtonClick(item)
        }

//        private fun onLikeButtonClick(item: Country) {
//            likeBtn.setOnClickListener {
//
//                if (!isLiked) {
//                    setLikedButtonDrawable(R.drawable.ic_heart_red)
//                    countriesDbManager.saveToDatabase(item)
//
//                } else {
//                    setLikedButtonDrawable(R.drawable.ic_heart)
//                    item.name?.let { name -> countriesDbManager.removeFromDatabaseByName(name) }
//                }
//
//                isLiked = !isLiked
//            }
//        }

//        private fun setLikedButtonDrawable(resId: Int) {
//            likeBtn.setImageDrawable(itemView.context.resources.getDrawable(resId))
//        }

//        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//        fun onDestroy() {
//            countriesDbManager.clearResources()
//        }
    }
}
