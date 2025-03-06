package com.example.denquizgame

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun show(containerId: Int, fragmentManager: FragmentManager)

    //abstract class Replace(private val fragment: Fragment) : Screen {
    abstract class Replace(private val fragment: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                //.replace(containerId, fragment)
                //.replace(containerId, fragment.getDeclaredConstructor().newInstance())// рефлексия
                .replace(containerId, newFragment())// рефлексия
                //.addToBackStack(fragment.tag)
                .commit()
        }

        protected open fun newFragment(): Fragment = fragment.getDeclaredConstructor().newInstance()
    }


}