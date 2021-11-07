package com.uz.yolharakatiqoidalari.models

class Category {
    var category_name:String?=null
    var category_position:Int?=null

    constructor(category_name: String?, category_position: Int?) {
        this.category_name = category_name
        this.category_position = category_position
    }

    override fun toString(): String {
        return "Category(category_name=$category_name, category_position=$category_position)"
    }

}