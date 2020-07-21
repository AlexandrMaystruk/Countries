package com.jay.countries.util.diffutil

class ContinentsDiffUtil : BaseDiffUtil<GetContinentsQuery.Continent>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name() == newList[newItemPosition].name() &&
                oldList[oldItemPosition].code() == newList[newItemPosition].code()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name() == newList[newItemPosition].name() &&
                oldList[oldItemPosition].code() == newList[newItemPosition].code()
    }

}