package com.tugas.db_zidan34

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tugas.db_zidan34.room.Constant
import com.tugas.db_zidan34.room.UserDb
import com.tugas.roomdb_zidan34.data.User
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy {UserDb(this)}
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupView()
        setuplistener()

    }

    private fun setupView() {
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREAT -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnDaftar.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                getuser()
            }
            Constant.TYPE_UPDATE -> {
                btnDaftar.visibility = View.GONE
                getuser()
            }
        }

    }

    private fun getuser() {
        id = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.userDao().getuser(id)[0]
            etNama.setText( movies.nama)
            etSekolah.setText( movies.sekolah)
        }
    }


    private fun setuplistener() {
        btnDaftar.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().adduser(
                    User(0, etNama.text.toString(), etSekolah.text.toString())
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().update(
                    User(id, etNama.text.toString(),
                        etSekolah.text.toString())
                )
                finish()
            }
        }
    }
}