package com.magdev.data.storage.rxstorages

import com.magdev.data.storage.IStorage
import io.reactivex.Observable


interface IRxStorage<T> : IStorage<T> {

    val observable: Observable<T>
}
