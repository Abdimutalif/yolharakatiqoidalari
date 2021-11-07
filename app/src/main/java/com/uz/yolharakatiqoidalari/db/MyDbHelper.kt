package com.uz.yolharakatiqoidalari.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uz.yolharakatiqoidalari.models.Znak
import com.uz.yolharakatiqoidalari.models.ZnakLeek
import com.uz.yolharakatiqoidalari.utils.Constant

class MyDbHalper(context: Context):SQLiteOpenHelper(context,Constant.DB_NAME,null,Constant.DB_VERSION),DbService{
    override fun onCreate(db: SQLiteDatabase?) {
        var quaryAdd = "create table ${Constant.TABLE_ZNAK_NAME} (${Constant.ZNAK_ID} integer primary key autoincrement unique not null,${Constant.IMAGE_ZNAK} text not null,${Constant.ZNAK_NAME} text not null,${Constant.ZNAK_INFO} text not null,${Constant.ZNAK_CATEGORY} text not null,${Constant.ZNAK_KATEGORY_POSITION} integer not null,${Constant.ZNAK_LEEK_BACKGRAUND1} integer not null)"
        db!!.execSQL(quaryAdd)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addZnak(znak: Znak) {
        val database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(Constant.IMAGE_ZNAK,znak.znak_image)
        contentValues.put(Constant.ZNAK_NAME,znak.znak_name)
        contentValues.put(Constant.ZNAK_INFO,znak.znak_info)
        contentValues.put(Constant.ZNAK_CATEGORY,znak.znak_category)
        contentValues.put(Constant.ZNAK_KATEGORY_POSITION,znak.znak_category_position)
        contentValues.put(Constant.ZNAK_LEEK_BACKGRAUND1,znak.znak_leek_backGraund)
        database.insert(Constant.TABLE_ZNAK_NAME,null,contentValues)
        database.close()
    }



    override fun editeZnak(znak: Znak) {
        val database = this.readableDatabase
        var contentValues = ContentValues()
        contentValues.put(Constant.ZNAK_ID,znak.id)
        contentValues.put(Constant.IMAGE_ZNAK,znak.znak_image)
        contentValues.put(Constant.ZNAK_NAME,znak.znak_name)
        contentValues.put(Constant.ZNAK_INFO,znak.znak_info)
        contentValues.put(Constant.ZNAK_CATEGORY,znak.znak_category)
        contentValues.put(Constant.ZNAK_KATEGORY_POSITION,znak.znak_category_position)
        contentValues.put(Constant.ZNAK_LEEK_BACKGRAUND1,znak.znak_leek_backGraund)
        database.update(Constant.TABLE_ZNAK_NAME,contentValues,"${Constant.ZNAK_ID}=?", arrayOf("${znak.id}"))

    }


    override fun deleteZnak(znak: Znak) {
        val database = this.readableDatabase
        database.delete(Constant.TABLE_ZNAK_NAME,"${Constant.ZNAK_ID}=?", arrayOf("${znak.id}"))
        database.close()
    }

    override fun getAllZnak(): ArrayList<Znak> {
        var list = ArrayList<Znak>()
        val database = this.readableDatabase
        var quary = "select*from ${Constant.TABLE_ZNAK_NAME}"
        var cursor = database.rawQuery(quary,null)
        if (cursor.moveToFirst()){
            do {
                var znak = Znak(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6))
                list.add(znak)
            }while (cursor.moveToNext())
        }
        return list
    }

}