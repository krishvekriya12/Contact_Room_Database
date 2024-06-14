package com.example.contact_room_database

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact_room_database.databinding.ActivityMainBinding
import com.example.roomdatabase5.activity.ContactActivity
import com.example.roomdatabase5.adapter.contactAdapter
import com.example.roomdatabase5.db.ContactEntity
import com.example.roomdatabase5.db.DBHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var contactlist = listOf<ContactEntity>()
    private lateinit var contectAdapter: contactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fbAdd.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        var db = DBHelper.chekDB(this)
        contactlist = db.contectDao().getAllContacts()
        val adapter = contactAdapter(contactlist as ArrayList<ContactEntity>)
        binding.rvdata.layoutManager = LinearLayoutManager(this)
        binding.rvdata.adapter = adapter
    }


}
