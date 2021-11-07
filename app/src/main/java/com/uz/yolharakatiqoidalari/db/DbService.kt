package com.uz.yolharakatiqoidalari.db

import com.uz.yolharakatiqoidalari.models.Znak
import com.uz.yolharakatiqoidalari.models.ZnakLeek

interface DbService {
    fun addZnak(znak: Znak)


    fun editeZnak(znak: Znak)


    fun deleteZnak(znak: Znak)



    fun getAllZnak():List<Znak>

}