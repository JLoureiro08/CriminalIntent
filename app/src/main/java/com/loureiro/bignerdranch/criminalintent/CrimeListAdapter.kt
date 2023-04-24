package com.loureiro.bignerdranch.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.loureiro.bignerdranch.criminalintent.databinding.ListItemCrimeBinding

open class CrimeHolder (
    val binding: ListItemCrimeBinding

): RecyclerView.ViewHolder(binding.root)
{
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener{
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}

abstract class NormalCrimeHolder(view:View) : CrimeHolder(ListItemCrimeBinding.bind(view)), View.OnClickListener{
    init {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()

            binding.root.setOnClickListener{
                Toast.makeText(
                    binding.root.context, "${crime.title} clicked", Toast.LENGTH_SHORT).show()
            }

        }
    }

}

abstract class SeriousCrimeHolder(view:View) : CrimeHolder(ListItemCrimeBinding.bind(view)), View.OnClickListener{
    val contactPoliceButton: Button = itemView.findViewById(R.id.contact_police_button)
    init {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            contactPoliceButton.setOnClickListener { Toast.makeText(binding.root.context, "This crime is serious!",
                Toast. LENGTH_SHORT).show()
            }

            binding.root.setOnClickListener{
                Toast.makeText(
                    binding.root.context, "${crime.title} clicked", Toast.LENGTH_SHORT).show()
            }

        }

    }
}

class CrimeListAdapter(private val crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        return when (viewType) {
           0 -> {
               val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
               NormalCrimeHolder(view)
           }
           else -> {
               val view = layoutInflater.inflate(R.layout.list_item_seriouscrime, parent, false)
           }
       }


        /*val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)*/
    }


    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is NormalCrimeHolder -> holder.bind(crime)
            is SeriousCrimeHolder -> holder.bind(crime)
            else -> throw IllegalArgumentException()       //This is in case the argument passed doesn't follow the logic
        }
        /*holder.apply {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
        }*/
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return when (crime.requiresPolice) {
                true -> 1
                else -> 0
        }
    }

}