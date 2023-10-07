package com.boostyboys.mcs.state

class HandleActionProxy<T : Any> {
    val receivedActions = mutableListOf<T>()

    private fun <A> countActionsOfType(action: A): Int = receivedActions.count { it == action }

    fun verify(
        exactly: Int = 1,
        order: ActionOrder = ActionOrder.Last,
        action: T,
    ) {
        val actualCount = countActionsOfType(action)

        if (actualCount != exactly) {
            throw AssertionError("Expected $action to be called $exactly times, but was called $actualCount times")
        }

        when (order) {
            ActionOrder.First -> if (receivedActions.first() != action) {
                throw AssertionError("Expected $action to be the first action, but was not")
            }
            ActionOrder.Last -> if (receivedActions.last() != action) {
                throw AssertionError("Expected $action to be the last action, but was not")
            }
            is ActionOrder.Index -> if (receivedActions.getOrNull(order.value) != action) {
                throw AssertionError("Expected $action to be at index ${order.value}, but was not")
            }
        }
    }

    fun clear() = receivedActions.clear()

    sealed class ActionOrder {
        data object First : ActionOrder()
        data object Last : ActionOrder()
        data class Index(val value: Int) : ActionOrder()
    }

    companion object {
        fun <T : Any> StateHandler<*, *, T, *>.verifyActionHandled(
            action: T,
            exactly: Int = 1,
            order: ActionOrder = ActionOrder.Last,
        ) = proxy?.verify(exactly, order, action)

        fun <T : Any> StateHandler<*, *, T, *>.clearProxy() = proxy?.clear()
    }
}
