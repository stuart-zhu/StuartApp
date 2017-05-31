package com.stuart.stuartapp.kotlin

/**
 * Created by stuart on 2017/5/26.
 */
interface MyTest {
    fun s()

    fun x(){
        s()
        System.out.println("x()()")
    }
}