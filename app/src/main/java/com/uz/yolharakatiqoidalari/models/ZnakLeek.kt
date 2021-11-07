package com.uz.yolharakatiqoidalari.models

class ZnakLeek {
    var id:Int?=null
    var znak_image:String?=null
    var znak_name:String?=null
    var znak_info:String?=null
    var znak_category:String?=null
    var znak_category_position:Int?=null
    var znak_leek_backGraund:Int?=null
    var znak_leek_id:Int?=null

    constructor(
        id: Int?,
        znak_image: String?,
        znak_name: String?,
        znak_info: String?,
        znak_category: String?,
        znak_category_position: Int?,
        znak_leek_backGraund:Int?,
        znak_leek_id:Int?
    ) {
        this.id = id
        this.znak_image = znak_image
        this.znak_name = znak_name
        this.znak_info = znak_info
        this.znak_category = znak_category
        this.znak_category_position = znak_category_position
        this.znak_leek_backGraund = znak_leek_backGraund
        this.znak_leek_id=znak_leek_id
    }

    constructor(
        znak_image: String?,
        znak_name: String?,
        znak_info: String?,
        znak_category: String?,
        znak_category_position: Int?,
        znak_leek_backGraund:Int?,
        znak_leek_id:Int?
    ) {
        this.znak_image = znak_image
        this.znak_name = znak_name
        this.znak_info = znak_info
        this.znak_category = znak_category
        this.znak_category_position = znak_category_position
        this.znak_leek_backGraund = znak_leek_backGraund
        this.znak_leek_id=znak_leek_id
    }

    override fun toString(): String {
        return "Znak(id=$id, znak_image=$znak_image, znak_name=$znak_name, znak_info=$znak_info, znak_category=$znak_category, znak_category_position=$znak_category_position, znak_backGraund=$znak_leek_backGraund)"
    }

}