package com.example.fileplayground.recylerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fileplayground.R
import com.example.fileplayground.databinding.ItemMakananBinding
import com.example.fileplayground.databinding.ItemMinumanBinding
import com.example.fileplayground.recylerview.model.Menu

class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<Menu>()

    fun setData(listMenu: ArrayList<Menu>) {
        list.clear()
        list.addAll(listMenu)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_MAKANAN) {
            ViewHolderMakanan(
                LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent, false)
            )
        } else {
            ViewHolderMinuman(
                LayoutInflater.from(parent.context).inflate(R.layout.item_minuman, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_MAKANAN) {
            (holder as ViewHolderMakanan).bind(list[position])
        } else {
            (holder as ViewHolderMinuman).bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].jenis == Menu.Category.MAKANAN) TYPE_MAKANAN else TYPE_MINUMAN
    }

    override fun getItemCount() = list.size


    inner class ViewHolderMakanan(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMakananBinding.bind(itemView)
        fun bind(data: Menu) {
            binding.jumlahMakanan.text = data.jumlah.toString()
            binding.namaMakanan.text = data.nama
        }

    }


    inner class ViewHolderMinuman(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMinumanBinding.bind(itemView)
        fun bind(data: Menu) {
            binding.jumlahMinuman.text = data.jumlah.toString()
            binding.namaMinuman.text = data.nama
        }
    }


    companion object {
        const val TYPE_MAKANAN: Int = 1
        const val TYPE_MINUMAN: Int = 2
    }

}