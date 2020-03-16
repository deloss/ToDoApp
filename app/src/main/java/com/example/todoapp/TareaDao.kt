package com.example.todoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface TareaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarTarea(tarea: Tarea) : Completable

    @Query("SELECT * FROM tareas")
    fun getTareas() : Observable<List<Tarea>>

}