package Model

import android.os.Parcel
import android.os.Parcelable

open class Hewan (
    nama:String,
    jenis:String,
    usia:String,
    imageUri:String,
    urutan:Int,
                  ){
    var nama:String = nama
    var jenis:String = jenis
    var usia:String = usia
    var imageUri:String = imageUri
    var urutan:Int = urutan

    open fun suara():String{
        return ""
    }
    fun makan():String{
        return "rumput"
    }
    fun makan(makananAyam:String):String{
        return "biji-bijian"
    }
}


