package com.tugas.db_zidan34

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.db_zidan34.room.Constant
import com.tugas.db_zidan34.room.UserDb
import com.tugas.roomdb_zidan34.data.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Constructor

class MainActivity : AppCompatActivity() {

    val db by lazy { UserDb(this) }
    lateinit var useradapter:UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        loaduser()

    }

    private fun loaduser() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = db.userDao().getusers()
            Log.d("MainActivity","dbrespond : $user")
            withContext(Dispatchers.Main){
                useradapter.setData(user)
            }
        }
    }


    private fun setupListener() {
        btnTambah.setOnClickListener{
            intenEdit(0, Constant.TYPE_CREAT)
        }
    }

    fun intenEdit(userid:Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddActivity::class.java)
                .putExtra("intent_id", userid)
                .putExtra("intent_type", intentType)
        )
    }

    private fun setupList() {
        useradapter = UserAdapter(arrayListOf(), object : UserAdapter.OnAdapterListener{
            override fun onClick(user: User) {
                intenEdit(user.id, Constant.TYPE_READ)
            }

            override fun onUpdate(user: User) {
                intenEdit(user.id, Constant.TYPE_UPDATE)
            }

            override fun onDelete(user: User) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().delete(user)
                    loaduser()
                }
            }

        })
        list_user.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = useradapter
        }
    }
}