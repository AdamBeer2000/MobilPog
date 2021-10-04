package com.mobilpogbead.controller

import com.mobilpogbead.model.Model
import com.mobilpogbead.view.View
import kotlin.time.*

class Controller
{
    val model=Model()
    val view=View(model)

    fun start()
    {
        while(true)
        {
            //todo késleltetés
            //todo szenzor model.()
            view.update()
        }
    }
}