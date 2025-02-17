package com.example.denquizgame

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.denquizgame.databinding.ActivityMainBinding
import com.example.denquizgame.databinding.GameOverBinding
import java.io.Serializable

class MyCustomFragmentManager {
    private var lastScreen: MyCustomScreen? = null
    fun save(outState: Bundle) {
        outState.putSerializable("screen", lastScreen)
        lastScreen?.detach()
    }

    fun restore(savedInstanceState: Bundle, activity: MainActivity) {
        lastScreen = if (SDK_INT >= TIRAMISU) {
            savedInstanceState.getSerializable(
                "screen",
                MyCustomScreen::class.java
            ) as MyCustomScreen
        } else {
            savedInstanceState.getSerializable("screen") as MyCustomScreen
        }
        lastScreen?.attach(activity)
        lastScreen?.show(savedInstanceState)
    }

    fun show(screen: MyCustomScreen, activity: MainActivity) {
        if (lastScreen != null && screen::class.java.simpleName == lastScreen!!::class.java.simpleName) {
            lastScreen!!.attach(activity)
            lastScreen!!.show(null)
        } else {
            lastScreen = screen
            lastScreen!!.attach(activity)
            lastScreen!!.show(null)
        }

    }
}

interface MyCustomScreen : Serializable {
    fun attach(activity: MainActivity)
    fun detach()
    fun show(savedInstanceState: Bundle?)
}

class MainActivity() : AppCompatActivity() {

    /*constructor() : super() {
            Log.d("sdv74", "onCreate called")
        }*/
    /* init {
         Log.d("sdv74", "mainActivity")
     }*/

    //private lateinit var showGameOver: () -> Unit
    //private lateinit var showGame: () -> Unit


    private val fragmentManger = MyCustomFragmentManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Log.d("sdv74", " onCreate ${savedInstanceState == null}")
        /*showGame = {
            GameCustomScreen(showGameOver).show(this, savedInstanceState)
        }
        showGameOver = {
            GameOverMyCustomScreen(showGame).show(this)
        }
*/

        enableEdgeToEdge()
        if (savedInstanceState == null) {
            //showGame.invoke()
            navigateToGameScreen()
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

    fun navigateToGameScreen() {
        fragmentManger.show(GameCustomScreen(), this)
    }

    fun navigateToGameOver() {
        fragmentManger.show(GameOverMyCustomScreen(), this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentManger.save(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        fragmentManger.restore(savedInstanceState, this)
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


class GameCustomScreen : MyCustomScreen {

    private var activity: MainActivity? = null

    override fun attach(activity: MainActivity) {
        this.activity = activity
    }

    override fun detach() {
        activity = null
    }

    override fun show(savedInstanceState: Bundle?) {
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(activity!!.layoutInflater)
        activity!!.setContentView(binding.root)

        val viewModel: GameViewModel = (activity!!.application as QuizApp).gameViewModel
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
            activity!!.navigateToGameOver()
            //GameOverFragment(showGameFragment).show(activity)
            //showGameOver.invoke()
            /*uiState = viewModel.next()
            //uiState.update(binding = binding)
            update.invoke()*/

        }

        uiState = viewModel.init(true)
        //uiState.update(binding = binding)


        //uiState.update(binding = binding)
        update.invoke()
    }
}

class GameOverMyCustomScreen : MyCustomScreen {

    private var activity: MainActivity? = null

    override fun show(savedInstanceState: Bundle?) {

        val viewModel: GameOverViewModel = (activity!!.application as QuizApp).gameOverViewModel

        val gameOverBinding: GameOverBinding = GameOverBinding.inflate(activity!!.layoutInflater)
        activity!!.setContentView(gameOverBinding.root)

        gameOverBinding.statsTextView.updateOuter(viewModel.statsUiState)

        gameOverBinding.newGameButton.setOnClickListener {
            //activity.setContentView(binding.root)
            //showGameFragment.invoke()
            activity?.navigateToGameScreen()
        }
    }

    override fun attach(activity: MainActivity) {
        this.activity = activity
    }

    override fun detach() {
        activity = null
    }


}