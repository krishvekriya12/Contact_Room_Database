package com.example.roomdatabase5.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1)
abstract class DBHelper : RoomDatabase() {
    abstract fun contectDao(): ContactDAO

    companion object {
        val db: DBHelper? = null
        fun chekDB(context: Context): DBHelper {
            if (db != null) {
                return db
            } else {
                return initDBHelper(context)
            }
        }

         fun initDBHelper(context: Context): DBHelper {
            return Room.databaseBuilder(context, DBHelper::class.java, "contact.db")
                .allowMainThreadQueries().build()
        }
    }
}