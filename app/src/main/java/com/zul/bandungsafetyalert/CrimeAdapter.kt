package com.zul.bandungsafetyalert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zul.bandungsafetyalert.databinding.ItemCrimeBinding

class CrimeAdapter(private val crimeList: List<Crime>) : RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val binding = ItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        val crime = crimeList[position]
        holder.bind(crime)
    }

    override fun getItemCount(): Int = crimeList.size

    class CrimeViewHolder(private val binding: ItemCrimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.apply {
                WaktuKejahatan.text = crime.time
                TanggalKejahatan.text = crime.date
                tvCrimeDescription.text = crime.description
                JenisKejahatan.text = crime.crimeType
            }
        }
    }
}
