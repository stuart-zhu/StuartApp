package com.stuart.stuartapp.kotlin.entity

/**
 * Created by stuart on 2017/6/9.
 */

open class Person( age : Int , name : String) {

    protected open val level = 0
}

class Me : Person(26, "zyy") {

    override val level = 100

}