package com.stuart.stuartapp.kotlin

import android.util.Log

/**
 * Created by stuart on 2017/5/26.
 */

class Person {
    var id: Int = 0
    var name: String? = null
    var age: Int = 0

    var isOld : Boolean = false
    get() = this.age > 600
    set(value) {
        field = value
    }

    constructor(id: Int) {
        this.id = id
    }

    constructor(id: Int, age: Int) {
        this.id = id
        this.age = age
    }

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    constructor(name: String) {
        this.name = name
    }

    constructor(id: Int, name: String, age: Int) {
        this.id = id
        this.name = name
        this.age = age
    }
}
