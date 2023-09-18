package com.boostyboys.mcs.data

interface CounterRepository {
    fun getCounter(): Counter
    fun setCounterValue(value: Int)
}
