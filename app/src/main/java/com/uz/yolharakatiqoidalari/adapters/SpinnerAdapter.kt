package com.uz.yolharakatiqoidalari.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.uz.yolharakatiqoidalari.databinding.ItemSpinnerBinding

class SpinnerAdapter(var list: List<String>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var spinner:Spinner
        if (convertView==null){
            var item = ItemSpinnerBinding.inflate(LayoutInflater.from(parent!!.context))
            spinner = Spinner(item)
        }else{
            spinner = Spinner(ItemSpinnerBinding.bind(convertView))
        }
        spinner.itemSpinnerBinding!!.nameCategorySpinner.text =list[position]
        return spinner.itemView!!
    }

    inner class Spinner{
        var itemView: View?=null
        var itemSpinnerBinding:ItemSpinnerBinding?=null

        constructor(itemSpinnerBinding: ItemSpinnerBinding?) {
            this.itemView = itemSpinnerBinding?.root
            this.itemSpinnerBinding = itemSpinnerBinding
        }
    }
}