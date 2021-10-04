package com.mobilpogbead.entity

abstract class Entity (private var coordinates: Pair<Int, Int>) {

    // ABSTRACT VARIABLES
    abstract var speed: Int
    abstract var hp: Int

    // ABSTRACT FUNCTIONS
    abstract fun hit()
    abstract fun moveRight()
    abstract fun moveLeft()
    abstract fun moveUp()
    abstract fun moveDown()
}