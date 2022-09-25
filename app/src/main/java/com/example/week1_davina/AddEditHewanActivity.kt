package com.example.week1_davina

import Database.GlobalVar
import Model.Ayam
import Model.Hewan
import Model.Kambing
import Model.Sapi
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.week1_davina.databinding.ActivityAddEditHewanBinding

class AddEditHewanActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityAddEditHewanBinding
    private lateinit var ayam: Ayam
    private lateinit var kambing: Kambing
    private lateinit var sapi: Sapi
    private lateinit var hewan: Hewan
    private var position = -1
    private var photoHewan: String = ""
    private var jenisnya: String = ""
    private var urutan = 0
    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

                }
            }

            viewBind.foto.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            photoHewan = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityAddEditHewanBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        CheckPermissions()
        GetIntent()
        Listener()
    }

    private fun GetIntent(){
        position = intent.getIntExtra("buatEdit", -1)
        if(position != -1){
            val hewan = GlobalVar.listDataHewan[position]
            viewBind.toolbarAddEdit.title = "Edit Hewan"
            viewBind.foto.setImageURI(Uri.parse(hewan.imageUri))
            viewBind.namaInput.editText?.setText(hewan.nama)
            if(hewan.jenis == "Sapi"){
                viewBind.sapiInput.isChecked = true
            }else if(hewan.jenis == "Ayam"){
                viewBind.ayamInput.isChecked = true
            }else {
                viewBind.kambingInput.isChecked = true
            }
            jenisnya = hewan.jenis.toString()
            viewBind.usiaInput.editText?.setText(hewan.usia)
        }
    }

    private fun CheckPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGEWrite_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), GlobalVar.STORAGERead_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGEWrite_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == GlobalVar.STORAGERead_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Listener(){
        viewBind.foto.setOnClickListener{
            val gambarIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            gambarIntent.type = "image/*"
            GetResult.launch(gambarIntent)
        }

        viewBind.sapiInput.setOnClickListener{
            jenisnya = viewBind.sapiInput.text.toString()
        }

        viewBind.ayamInput.setOnClickListener{
            jenisnya = viewBind.ayamInput.text.toString()
        }

        viewBind.kambingInput.setOnClickListener{
            jenisnya = viewBind.kambingInput.text.toString()
        }

        viewBind.simpantombol.setOnClickListener{

            var nama = viewBind.namaInput.editText?.text.toString()
            var usia = viewBind.usiaInput.editText?.text.toString()
            //urutan+=1
            //photoHewan = viewBind.foto.toString()


            if(GlobalVar.listDataHewan.size>0){
                urutan = GlobalVar.listDataHewan[GlobalVar.listDataHewan.size-1].urutan +1
                hewan = Hewan(nama,jenisnya,usia, photoHewan, urutan)
            }else{
                hewan = Hewan(nama,jenisnya,usia, photoHewan, urutan)
            }

//            GlobalVar.listDataHewan.add(hewan)
//
//            finish()


//            if(viewBind.sapiInput.isClickable){
//
////                viewBind.sapiInput.setOnClickListener {
////                    hewan.jenis = viewBind.sapiInput.text.toString()
////                }
//                sapi = Sapi()
//                hewan.nama = viewBind.namaInput.editText?.text.toString().trim()
//                hewan.usia = viewBind.usiaInput.editText?.text.toString().trim()
//                hewan.jenis = viewBind.sapiInput.text.toString()
//                GlobalVar.listDataHewan.add(hewan)
//                Toast.makeText(this, "sapi msk", Toast.LENGTH_SHORT).show()
//                viewBind.radioGroup.setOnCheckedChangeListener{buttonView, isChecked
//                    Toast.makeText(this, "sapiii", Toast.LENGTH_SHORT).show()
//                }
//            ayam.imageUri = photoHewan
//                hewan = Hewan()
                //break
           // }

//            if(viewBind.ayamInput.isClickable){
//
////
////                ayam.jenis = viewBind.radioGroup.checkedRadioButtonId.toString()
//                Toast.makeText(this, "ayam msk", Toast.LENGTH_SHORT).show()
//                viewBind.radioGroup.setOnClickListener {
//                    hewan.jenis = viewBind.sapiInput.text.toString()
//                }
//            ayam.imageUri = photoHewan
//                hewan = Hewan()
            //}
            //hewan = Hewan()

            Checking()
//            GlobalVar.listDataHewan.add(hewan)

        }
        viewBind.toolbarAddEdit.getChildAt(1).setOnClickListener{
            finish()
        }
    }

    private fun Checking(){
        var isCompleted:Boolean = true

        //nama hewan
        if(hewan.nama!!.isEmpty()){
            viewBind.namaInput.error = "Tolong isi nama hewannya"
            isCompleted = false
        }else{
            viewBind.namaInput.error = ""
        }

        //usia hewan
        if(hewan.usia!!.isEmpty()){
            viewBind.usiaInput.error = "Tolong isi usia hewannya"
            isCompleted = false
        }else{
            viewBind.usiaInput.error = ""
        }

        //jenis hewan
        if(hewan.jenis!!.isEmpty()){
            Toast.makeText(this, "Tolong isi jenis hewannya", Toast.LENGTH_SHORT).show()
            isCompleted = false
        }

        if(isCompleted) {
            if (position == -1) {
                if(hewan.jenis == viewBind.sapiInput.text.toString()){
                    var namaS = hewan.nama
                    var jenisS = hewan.jenis
                    var usiaS = hewan.usia
                    var fotoS = hewan.imageUri
                    sapi = Sapi(namaS, jenisS, usiaS, fotoS, urutan)
                    hewan = sapi
                }
                if(hewan.jenis == viewBind.ayamInput.text.toString()){
                    var namaA = hewan.nama
                    var jenisA = hewan.jenis
                    var usiaA = hewan.usia
                    var fotoA = hewan.imageUri
                    ayam = Ayam(namaA, jenisA, usiaA, fotoA, urutan)
                    hewan = ayam
                }
                if(hewan.jenis == viewBind.kambingInput.text.toString()){
                    var namaK = hewan.nama
                    var jenisK = hewan.jenis
                    var usiaK = hewan.usia
                    var fotoK = hewan.imageUri
                    kambing = Kambing(namaK, jenisK, usiaK, fotoK, urutan)
                    hewan = kambing
                }
                GlobalVar.listDataHewan.add(hewan)
                finish()
            } else {
                //untuk edit
                if(hewan.jenis == viewBind.sapiInput.text.toString()){
                    var namaS = hewan.nama
                    var jenisS = hewan.jenis
                    var usiaS = hewan.usia
                    var fotoS = hewan.imageUri
                    sapi = Sapi(namaS, jenisS, usiaS, fotoS, urutan)
                    hewan = sapi
                }
                if(hewan.jenis == viewBind.ayamInput.text.toString()){
                    var namaA = hewan.nama
                    var jenisA = hewan.jenis
                    var usiaA = hewan.usia
                    var fotoA = hewan.imageUri
                    ayam = Ayam(namaA, jenisA, usiaA, fotoA, urutan)
                    hewan = ayam
                }
                if(hewan.jenis == viewBind.kambingInput.text.toString()){
                    var namaK = hewan.nama
                    var jenisK = hewan.jenis
                    var usiaK = hewan.usia
                    var fotoK = hewan.imageUri
                    kambing = Kambing(namaK, jenisK, usiaK, fotoK, urutan)
                    hewan = kambing
                }
                GlobalVar.listDataHewan[position] = hewan
            }
            finish()
        }
    }

}