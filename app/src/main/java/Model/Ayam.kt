package Model

import android.widget.Toast

class Ayam (nama: String, jenis: String, usia: String, imageUri: String, urutan: Int): Hewan(nama, jenis, usia,
    imageUri,
    urutan
) {
    override fun suara(): String {
        return "POKKK!! POKK!"
    }
}
