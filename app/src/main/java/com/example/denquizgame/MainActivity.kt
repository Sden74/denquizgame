package com.example.denquizgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

/*
interface Context {
    fun getString()

    class ContextImpl:Context{

        override fun getString() {
            TODO("real code to get string resource")
        }

        fun setDisplay(double: Any){

        }

    }
    class Activity : Context {

        private lateinit var baseContext: Context

        fun setContext(context: Context) {
            this.context = context
        }

        override fun getString() {
            context.getString()
        }

    }

    class Application:Context{
        private lateinit var context: Context
        private lateinit var application: Application

        fun setContext(context: Context){
                this.context = context
        }

        fun setApplication(application: Application){
            this.application=application
        }
        override fun getString() {
            context.getString()
        }

    }
}

class Init{
    fun main(){
        val app=makeApplication()
        app.SetContext(Context.ContextImpl().apply {
            setType(ApplicationTypeContext)
        })

        val activity=makeActivity()
        activity.setContext(Context.ContextImpl().apply{
            setDisplay("display")
            setType(ActivityContextType)
        })

        activity.setApplication(app)

        activity.callOnCreate()
    }

}*/
