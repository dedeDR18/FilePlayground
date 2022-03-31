package com.example.fileplayground.recylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.R
import com.example.fileplayground.databinding.ActivityRecyclerviewBinding
import com.example.fileplayground.recylerview.adapter.MenuAdapter
import com.example.fileplayground.recylerview.model.Menu

class RecyclerviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRv()

        menuAdapter.setData(createData())
    }

    private fun initRv() = with(binding) {
        menuAdapter = MenuAdapter()
        rvMenu.adapter = menuAdapter
    }

    private fun createData(): ArrayList<Menu> {
        val list = ArrayList<Menu>()

        val menu1 = Menu(
            nama = "Nasi Goreng",
            jumlah = 2,
            jenis = Menu.Category.MAKANAN
        )

        val menu2 = Menu(
            nama = "Mie Goreng",
            jumlah = 4,
            jenis = Menu.Category.MAKANAN
        )

        val menu3 = Menu(
            nama = "Jus jeruk",
            jumlah = 2,
            jenis = Menu.Category.MINUMAN
        )

        val menu4 = Menu(
            nama = "jus alpukat",
            jumlah = 2,
            jenis = Menu.Category.MINUMAN
        )

        val menu5 = Menu(
            nama = "Soto ",
            jumlah = 1,
            jenis = Menu.Category.MAKANAN
        )

        list.add(menu1)
        list.add(menu2)
        list.add(menu3)
        list.add(menu4)
        list.add(menu5)

        return list
    }
}