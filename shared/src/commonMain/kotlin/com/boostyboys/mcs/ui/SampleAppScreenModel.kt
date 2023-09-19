package com.boostyboys.mcs.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.Counter
import com.boostyboys.mcs.data.CounterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SampleAppScreenModel(private val counterRepository: CounterRepository) : ScreenModel {

    private val _count: MutableStateFlow<Counter> = MutableStateFlow(counterRepository.getCounter())
    val count: StateFlow<Counter> get() = _count

    fun increment() {
        coroutineScope.launch {
            val currentValue = counterRepository.getCounter().value
            counterRepository.setCounterValue(currentValue + 1)
            _count.emit(counterRepository.getCounter())
        }
    }
}
