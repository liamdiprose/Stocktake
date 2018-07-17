package com.liamdiprose.myitems

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import java.util.concurrent.TimeUnit

/**
 * Namestore will slowly emit names over time. For getting RxKotlin working with android
 */
class NameStore {

    val names = listOf("Liam", "Bob", "Jeff")

    public fun fetchNames(amount: Long): Observable<String> {

        return this.names.toObservable()
                .repeat(amount)
//                .delay(1, TimeUnit.SECONDS)
    }
}