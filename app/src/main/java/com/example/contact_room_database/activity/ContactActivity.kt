package com.example.roomdatabase5.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contact_room_database.databinding.ActivityContactBinding
import com.example.roomdatabase5.db.ContactEntity
import com.example.roomdatabase5.db.DBHelper

class ContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = DBHelper.chekDB(this)

        binding.btnSave.setOnClickListener {
            val name = binding.edtname.text.toString()
            val phone = binding.edtphone.text.toString()
            val email = binding.edtemail.text.toString()

            var model = ContactEntity(name = name, phone = phone, email = email)
            db.contectDao().insertContact(model)
            finish()
        }
    }
}
