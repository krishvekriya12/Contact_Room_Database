package com.example.roomdatabase5.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {
    @Insert
    fun insertContact(contactEntity: ContactEntity)

   @Update
   fun updateContact(contactEntity: ContactEntity)

    @Delete
    fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<ContactEntity>

}