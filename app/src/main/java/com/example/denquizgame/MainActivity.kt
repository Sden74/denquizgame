package com.example.denquizgame

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.denquizgame.databinding.ActivityMainBinding
import com.example.denquizgame.databinding.GameOverBinding

class MainActivity() : AppCompatActivity() {

    /*constructor() : super() {
            Log.d("sdv74", "onCreate called")
        }*/
    /* init {
         Log.d("sdv74", "mainActivity")
     }*/

    private lateinit var showGameOver: () -> Unit
    private lateinit var showGame: () -> Unit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("sdv74", " onCreate ${savedInstanceState == null}")
        enableEdgeToEdge()
        showGame = {
            GameCustomScreen(showGameOver).show(this, savedInstanceState)
        }
        showGameOver = {
            GameOverMyCustomScreen(showGame).show(this)
        }

        if (savedInstanceState == null) {
            showGame.invoke()
        }

        /* ViewCompat.setOnApplyWindowInsetsListener(*//*findViewById(R.id.rootLayout)*//* binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        /*

                //val viewModel: GameViewModel = GameViewModel(GameRepository.Base())
                // создаем статику модельки GameViewModel
                // при пересоздании Activity View-моделька не будет пересоздаваться, т.к. находится в статике
                //
                //val viewModel: GameViewModel = ViewModelContainer.viewModel



        */

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
/*

// Нужен контейнер типа object потому что object не может принимать в себя зависимости
// и создать object viewModel : GameViewModel(GameRepository.Base())
// иными словами создаем singlton
// НО!!!!! Согласно логам ViewModelContainer создается после Activity что нас не устраивает,
// поэтому мы от него откажемся
// в андроид разработке нет смысла использовать синглтоны (object)
*/


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


class GameCustomScreen(private val showGameOver: () -> Unit) {


    fun show(activity: MainActivity, savedInstanceState: Bundle?) {
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(activity.layoutInflater)
        activity.setContentView(binding.root)

        val viewModel: GameViewModel = (activity.application as QuizApp).gameViewModel
        lateinit var uiState: GameUiState
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

        /*val showGameFragment = {
            show(activity,savedInstanceState)
        }*/

        binding.nextButton.setOnClickListener {
            //GameOverFragment(showGameFragment).show(activity)
            showGameOver.invoke()
            /*uiState = viewModel.next()
            //uiState.update(binding = binding)
            update.invoke()*/

        }

        uiState = viewModel.init(savedInstanceState == null)
        //uiState.update(binding = binding)


        //uiState.update(binding = binding)
        update.invoke()
    }
}

class GameOverMyCustomScreen(private val showGameFragment: () -> Unit) {
    fun show(activity: MainActivity) {

        val viewModel: GameOverViewModel = (activity.application as QuizApp).gameOverViewModel

        val gameOverBinding: GameOverBinding = GameOverBinding.inflate(activity.layoutInflater)
        activity.setContentView(gameOverBinding.root)

        gameOverBinding.statsTextView.updateOuter(viewModel.statsUiState)

        gameOverBinding.newGameButton.setOnClickListener {
            //activity.setContentView(binding.root)
            showGameFragment.invoke()
        }
    }
}