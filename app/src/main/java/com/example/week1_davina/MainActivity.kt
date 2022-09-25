package com.example.week1_davina

import Adaptor.ListDataAdaptor
import Database.GlobalVar
import Interface.CardListener
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week1_davina.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), CardListener {
    private lateinit var viewBind: ActivityMainBinding
    private var adapter = ListDataAdaptor(GlobalVar.listDataHewan, this)
    private var secAdaptor = ListDataAdaptor(GlobalVar.filterDataHewan, this)
//    private var adapterS = sapiLDA(GlobalVar.listDataSapi, this)
//    private var adapterA = ayamLDA(GlobalVar.listDataAyam, this)
//    private var adapterK = kambingLDA(GlobalVar.listDataKambing, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        setupRecyclerView()
        Listener()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(baseContext)
        viewBind.listHewanRV.layoutManager = layoutManager //set layout
        viewBind.listHewanRV.adapter = adapter //set adaptor
    }

//    private fun sapiRV(){
//        val layoutManager = LinearLayoutManager(baseContext)
//        viewBind.listHewanRV.layoutManager = layoutManager //set layout
//        viewBind.listHewanRV.adapter = adapterS //set adaptor
//        adapterS.notifyDataSetChanged()
//    }
//    private fun ayamRV(){
//        val layoutManager = LinearLayoutManager(baseContext)
//        viewBind.listHewanRV.layoutManager = layoutManager //set layout
//        viewBind.listHewanRV.adapter = adapterA //set adaptor
//        adapterA.notifyDataSetChanged()
//    }
//    private fun kambingRV(){
//        val layoutManager = LinearLayoutManager(baseContext)
//        viewBind.listHewanRV.layoutManager = layoutManager //set layout
//        viewBind.listHewanRV.adapter = adapterK //set adaptor
//        adapterK.notifyDataSetChanged()
//    }

    private fun Listener() {
        viewBind.tambahtombol.setOnClickListener {
            val keAddEditIntent = Intent(this, AddEditHewanActivity::class.java)
            startActivity(keAddEditIntent)
        }
        viewBind.semuaFilter.setOnClickListener {
//            Toast.makeText(this, "ini semuanya", Toast.LENGTH_SHORT).show()
            setupRecyclerView()
        }
        viewBind.sapiFilter.setOnClickListener {
//            Toast.makeText(this, "ini sapi", Toast.LENGTH_SHORT).show()
            GlobalVar.filterDataHewan.clear()
            for (i in 0..GlobalVar.listDataHewan.size - 1) {
                if (GlobalVar.listDataHewan[i].jenis == "Sapi") {
                    GlobalVar.filterDataHewan.add(GlobalVar.listDataHewan[i])
//                    Toast.makeText(this, GlobalVar.listDataHewan[i].nama, Toast.LENGTH_SHORT).show()
                }
            }
            viewBind.listHewanRV.adapter = secAdaptor
            adapter.notifyDataSetChanged()
//            GlobalVar.listDataSapi
//            sapiRV()
        }
        viewBind.ayamFilter.setOnClickListener {
//            Toast.makeText(this, "ini ayam", Toast.LENGTH_SHORT).show()
            GlobalVar.filterDataHewan.clear()
            for (i in 0..GlobalVar.listDataHewan.size - 1) {
                if (GlobalVar.listDataHewan[i].jenis == "Ayam") {
                    GlobalVar.filterDataHewan.add(GlobalVar.listDataHewan[i])
//                    Toast.makeText(this, GlobalVar.listDataHewan[i].nama, Toast.LENGTH_SHORT).show()
                }
            }
            viewBind.listHewanRV.adapter = secAdaptor
            adapter.notifyDataSetChanged()
//            GlobalVar.listDataAyam
//            ayamRV()
        }
        viewBind.kambingFilter.setOnClickListener {
//            Toast.makeText(this, "ini kambing", Toast.LENGTH_SHORT).show()
            GlobalVar.filterDataHewan.clear()
            for (i in 0..GlobalVar.listDataHewan.size - 1) {
                if (GlobalVar.listDataHewan[i].jenis == "Kambing") {
                    GlobalVar.filterDataHewan.add(GlobalVar.listDataHewan[i])
//                    Toast.makeText(this, GlobalVar.listDataHewan[i].nama, Toast.LENGTH_SHORT).show()
                }
            }
            viewBind.listHewanRV.adapter = secAdaptor
            adapter.notifyDataSetChanged()
//            GlobalVar.listDataKambing
//            kambingRV()
        }
    }

    override fun onCardClick(id: String, position: Int) {
        if (id == "hapus") {
            AlertDialog.Builder(this).setMessage("Apakah anda yakin ingin menghapusnya?")
                .setPositiveButton("Ya") { dialog, id ->
                    for(i in 0..GlobalVar.listDataHewan.size-1){
                        if(GlobalVar.listDataHewan[i].jenis == "Sapi"){
                            GlobalVar.filterDataHewan.removeAt(i)
                            GlobalVar.filterDataHewan.add(GlobalVar.listDataHewan[i])
                        }else if(GlobalVar.listDataHewan[i].jenis == "Ayam"){
                            GlobalVar.filterDataHewan.removeAt(i)
                            GlobalVar.filterDataHewan.add(GlobalVar.listDataHewan[i])
                        }else if(GlobalVar.listDataHewan[i].jenis == "Kambing"){
                            GlobalVar.filterDataHewan.removeAt(i)
                            GlobalVar.filterDataHewan.add(GlobalVar.listDataHewan[i])
                        }
                        val keMainIntent = Intent(this, MainActivity::class.java)
                        startActivity(keMainIntent)
                        adapter.notifyDataSetChanged()
                    }
                    val snackBar =
                        Snackbar.make(viewBind.root, "Hewan telah dihapus", Snackbar.LENGTH_SHORT)
                }
                .setNegativeButton("Tidak") { dialog, id ->

                    dialog.dismiss()
                }.create().show()
            for(i in 0..GlobalVar.listDataHewan.size-1){
                if(GlobalVar.listDataHewan[i].urutan == position){
                    GlobalVar.listDataHewan.removeAt(i)
                    break
                }
            }


        }else if (id == "edit") {
            val keAddEditIntent =
                Intent(this, AddEditHewanActivity::class.java).putExtra("buatEdit", position)
            startActivity(keAddEditIntent)
            adapter.notifyDataSetChanged()
        }else if (id == "suara") {

            Toast.makeText(this, GlobalVar.listDataHewan.get(position).suara(), Toast.LENGTH_SHORT)
                .show()
        }else if (id == "makan") {
            if (GlobalVar.listDataHewan.get(position).jenis == "Ayam") {
                Toast.makeText(
                    this,
                    "Kamu memberi makan hewan dengan " + GlobalVar.listDataHewan.get(position)
                        .makan("makananAyam") + " :)",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Kamu memberi makan hewan dengan " + GlobalVar.listDataHewan.get(position)
                        .makan() + " :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

//    fun suara(){
//        if()
//    }

