package com.example.roomdatabase5.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contact_room_database.R
import com.example.contact_room_database.databinding.ActivityContactBinding
import com.example.roomdatabase5.db.ContactEntity
import com.example.roomdatabase5.db.DBHelper

class ContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityContactBinding
    private var id: Int = 0
    var name1: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getIntentData()
        val db = DBHelper.chekDB(this)
        binding.btnSave.setOnClickListener {
            val name = binding.edtname.text.toString()
            val phone = binding.edtphone.text.toString()
            val email = binding.edtemail.text.toString()

            if (name1 == null || name1!!.isEmpty()) {
                val model = ContactEntity(name = name, phone = phone, email = email)
                db.contectDao().insertContact(model)
            } else {
                val model = ContactEntity(name = name, phone = phone, email = email, id = id)
                db.contectDao().updateContact(model)
            }
            finish()
        }
    }

    private fun getIntentData() {
        name1 = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        id = intent.getIntExtra("id", 0)

        binding.edtname.setText(name1)
        binding.edtphone.setText(phone)
        binding.edtemail.setText(email)
    }
}
