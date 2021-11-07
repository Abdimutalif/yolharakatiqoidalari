package com.uz.yolharakatiqoidalari.adapters


import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uz.yolharakatiqoidalari.databinding.ItemRvBinding
import com.uz.yolharakatiqoidalari.models.Znak
import com.uz.yolharakatiqoidalari.models.ZnakLeek

class RvAdapterLeek(var list: List<Znak>, var onItemClick: OnItemClick):
    RecyclerView.Adapter<RvAdapterLeek.Vh>() {
    inner class Vh(var itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(znak: Znak, position: Int){
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
        fun onItemClickLeek(znakLeek: Znak, position: Int)
        fun onItemClicDelete(znakLeek: Znak, position: Int)
        fun onItemEditeZnak(znakLeek: Znak, position: Int)
        fun onItemClickOp(znakLeek: Znak, position: Int)
    }

}