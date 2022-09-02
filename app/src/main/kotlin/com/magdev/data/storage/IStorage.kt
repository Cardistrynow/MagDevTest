package com.magdev.data.storage


interface IStorage<T> {

    fun put(value: T)

    fun get(): T?

    fun clear()

}