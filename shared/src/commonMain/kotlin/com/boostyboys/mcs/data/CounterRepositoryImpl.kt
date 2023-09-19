package com.boostyboys.mcs.data

class CounterRepositoryImpl : CounterRepository {
    private var counter = Counter(value = 0)

    override fun getCounter() = counter

    override fun setCounterValue(value: Int) {
        counter = counter.copy(value = value)
    }
}
