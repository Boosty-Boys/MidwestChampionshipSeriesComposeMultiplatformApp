package app.com.mcs.data

interface CounterRepository {
    fun getCounter(): Counter
    fun setCounterValue(value: Int)
}
