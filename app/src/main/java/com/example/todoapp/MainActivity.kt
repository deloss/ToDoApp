package com.example.todoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val AGREGAR_TAREA_CODE = 1

    lateinit var tareaAdapter : TareaAdapter

    lateinit var tareaDao : TareaDao

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tareaDao = TareaDB.getInstance(this).tareaDao()

        compositeDisposable.add(tareaDao.getTareas().subscribeOn(Schedulers.io()).subscribe {
            tareaAdapter.setTareas(ArrayList(it))
        })

        tareaAdapter = TareaAdapter()
        recycler_view.adapter = tareaAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        agregar_tarea.setOnClickListener {
            startActivityForResult(Intent(this, AgregarTareaActivity::class.java), AGREGAR_TAREA_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == AGREGAR_TAREA_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val tareaDescripcion = data!!.extras!!["tarea"] as String
                val tareaCreada = Tarea(tareaDescripcion, false)
                tareaAdapter.agregarTarea(tareaCreada)
                compositeDisposable.add(tareaDao.insertarTarea(tareaCreada).subscribeOn(Schedulers.io()).subscribe())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}
