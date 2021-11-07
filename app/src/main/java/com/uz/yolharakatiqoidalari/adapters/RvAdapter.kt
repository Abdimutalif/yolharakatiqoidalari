package com.uz.yolharakatiqoidalari.adapters

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.databinding.ItemRvBinding
import com.uz.yolharakatiqoidalari.db.MyDbHalper
import com.uz.yolharakatiqoidalari.models.Znak
import com.uz.yolharakatiqoidalari.models.ZnakLeek

class RvAdapter(var context:Context,var list: List<Znak>, var onItemClick: OnItemClick,var image:Int): RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(znak: Znak,position: Int){
            itemRvBinding.imageZnak.setImageURI(Uri.parse(znak.znak_image))
            itemRvBinding.nameZnak.text = znak.znak_name
            itemRvBinding.leekBtn.setImageResource(znak.znak_leek_backGraund!!)
            itemRvBinding.leekBtn.setOnClickListener {
                onItemClick.onItemClickLeek(znak,position)
            }
            itemRvBinding.deleteBtn.setOnClickListener {
                onItemClick.onItemClicDelete(znak,position)
            }
            itemRvBinding.editeBtn.setOnClickListener {
                onItemClick.onItemEditeZnak(znak,position)
            }
            itemRvBinding.root.setOnClickListener {
                onItemClick.onItemClickOp(znak,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClick{
        fun onItemClickLeek(znak: Znak,position: Int)
        fun onItemClicDelete(znak: Znak,position: Int)
        fun onItemEditeZnak(znak: Znak,position: Int)
        fun onItemClickOp(znak: Znak,position: Int)
    }
}