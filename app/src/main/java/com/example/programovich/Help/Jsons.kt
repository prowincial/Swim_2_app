package com.example.programovich.Help

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Jsons {

    val gson=Gson()
    fun <T> arrayToJson(list:ArrayList<T>):String{
        val stringObj = gson.toJson(list)
        return stringObj

    }

    fun <T> arrayFromJson(stringObj:String): T {
        val type = object : TypeToken<ArrayList<T>>(){}.type
        val list = gson.fromJson<T>(stringObj,type)
        return list
    }

    fun  itemToJson(item: Image):String{
        val stringObj=gson.toJson(item)
        return stringObj
    }

    fun  itemFromJson(stringObj: String): Image {
        val type=object :TypeToken<Image>(){}.type
        val item=gson.fromJson<Image>(stringObj,type)
        return item
    }

}