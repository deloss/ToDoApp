package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tarea::class], version = 1, exportSchema = false)
abstract class TareaDB : RoomDatabase() {

    abstract fun tareaDao() : TareaDao

    companion object {

        var INSTANCIA : TareaDB? = null

        fun getInstance(context: Context) : TareaDB {
            if(INSTANCIA == null) {
                INSTANCIA = Room.databaseBuilder(
                    context.applicationContext,
                    TareaDB::class.java, "database-name"
                ).build()
            }
            return INSTANCIA!!
        }

    }

}