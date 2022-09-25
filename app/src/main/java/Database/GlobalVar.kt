package Database

import Model.Ayam
import Model.Hewan
import Model.Kambing
import Model.Sapi

class GlobalVar {
    companion object{
        val listDataHewan = ArrayList<Hewan>()
        val filterDataHewanS = ArrayList<Hewan>()
        val filterDataHewanA = ArrayList<Hewan>()
        val filterDataHewanK = ArrayList<Hewan>()
        val filterDataHewan = ArrayList<Hewan>()
        val listDataSapi = ArrayList<Sapi>()
        val listDataAyam = ArrayList<Ayam>()
        val listDataKambing = ArrayList<Kambing>()
        val STORAGEWrite_PERMISSION_CODE: Int = 3
        val STORAGERead_PERMISSION_CODE: Int= 2
    }
}