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

    private lateinit var uiState: GameUiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("sdv74", " onCreate ${savedInstanceState == null}")
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
        //val viewModel: GameViewModel = ViewModelContainer.viewModel

        val viewModel: GameViewModel = (application as QuizApp).viewModel

        val update: () -> Unit = {
            //uiState.update(binding = binding)
            uiState.update(
                binding.questionTextView,
                binding.firstChoiceButton,
                binding.secondChoiceButton,
                binding.thirdChoiceButton,
                binding.forthChoiceButton,
                binding.nextButton,
                binding.checkButton
            )
        }

        binding.firstChoiceButton.setOnClickListener {
            uiState = viewModel.chooseFirst()
            //uiState.update(binding = binding)
            update.invoke()
        }
        binding.secondChoiceButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            //uiState.update(binding = binding)
            update.invoke()
        }
        binding.thirdChoiceButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            //uiState.update(binding = binding)
            update.invoke()
        }
        binding.forthChoiceButton.setOnClickListener {
            uiState = viewModel.chooseForth()
            //uiState.update(binding = binding)
            update.invoke()
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check()
            //uiState.update(binding = binding)
            update.invoke()
        }
        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            //uiState.update(binding = binding)
            update.invoke()
        }

        uiState = viewModel.init(savedInstanceState == null)
        //uiState.update(binding = binding)


        //uiState.update(binding = binding)
        update.invoke()


    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("sdv74", " onSaveInstanceState")
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("sdv74", " onRestoreInstanceState")
        //todo restore data
    }

    companion object {
        private const val KEY = "uiState"
    }*/
}

// Нужен контейнер типа object потому что object не может принимать в себя зависимости
// и создать object viewModel : GameViewModel(GameRepository.Base())
// иными словами создаем singlton
// НО!!!!! Согласно логам ViewModelContainer создается после Activity что нас не устраивает,
// поэтому мы от него откажемся
// в андроид разработке нет смысла использовать синглтоны (object)


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
