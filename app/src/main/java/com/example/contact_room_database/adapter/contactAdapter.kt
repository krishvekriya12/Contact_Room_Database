package com.example.roomdatabase5.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contact_room_database.R
import com.example.contact_room_database.databinding.ContactTileBinding
import com.example.roomdatabase5.activity.ContactActivity
import com.example.roomdatabase5.db.ContactEntity
import com.example.roomdatabase5.db.DBHelper

class contactAdapter( var contactList: ArrayList<ContactEntity>) :
    RecyclerView.Adapter<contactAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : ViewHolder(itemView) {
        val binding = ContactTileBinding.bind(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_tile, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.binding.txtname.text = contact.name
        holder.binding.txtphone.text = contact.phone
        holder.binding.txtemail.text = contact.email

        holder.binding.btndelete.setOnClickListener {
            var db = DBHelper.chekDB(holder.itemView.context)
            db.contectDao().deleteContact(contactList[position])
            contactList.removeAt(position)
            notifyDataSetChanged()
        }
        holder.binding.btnedit.setOnClickListener {
            var intent = Intent(holder.itemView.context, ContactActivity::class.java)
            intent.putExtra("name", contactList[position].name)
            intent.putExtra("phone", contactList[position].phone)
            intent.putExtra("email", contactList[position].email)
            intent.putExtra("id",contactList[position].id)
            holder.itemView.context.startActivity(intent)
        }

    }


}