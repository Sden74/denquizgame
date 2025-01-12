package com.example.denquizgame

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.denquizgame.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    /*constructor() : super() {
            Log.d("sdv74", "onCreate called")
        }*/
    init {
        Log.d("sdv74", "mainActivity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(/*findViewById(R.id.rootLayout)*/ binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val viewModel: GameViewModel = GameViewModel(GameRepository.Base())
        // создаем статику модельки GameViewModel
        // при пересоздании Activity View-моделька не будет пересоздаваться, т.к. находится в статике
        //
        val viewModel: GameViewModel = ViewModelContainer.viewModel

        binding.firstChoiceButton.setOnClickListener {
            val uiState: GameUiState = viewModel.chooseFirst()
            uiState.update(binding = binding)
        }
        binding.secondChoiceButton.setOnClickListener {
            val uiState: GameUiState = viewModel.chooseSecond()
            uiState.update(binding = binding)
        }
        binding.thirdChoiceButton.setOnClickListener {
            val uiState: GameUiState = viewModel.chooseThird()
            uiState.update(binding = binding)
        }
        binding.forthChoiceButton.setOnClickListener {
            val uiState: GameUiState = viewModel.chooseForth()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            val uiState: GameUiState = viewModel.check()
            uiState.update(binding = binding)
        }
        binding.nextButton.setOnClickListener {
            val uiState: GameUiState = viewModel.next()
            uiState.update(binding = binding)
        }

        val uiState: GameUiState = viewModel.init()
        uiState.update(binding = binding)


    }
}

// Нужен контейнер типа object потому что object не может принимать в себя зависимости
// и создать object viewModel : GameViewModel(GameRepository.Base())
// иными словами создаем singlton
// НО!!!!! Согласно логам ViewModelContainer создается после Activity что нас не устраивает,
// поэтому мы от него откажемся
// в андроид разработке нет смысла использовать синглтоны (object)
object ViewModelContainer { // todo remove
    init {
        Log.d("sdv74", "vmcontainer")
    }

    val viewModel = GameViewModel(GameRepository.Base())
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
