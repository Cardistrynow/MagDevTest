package com.magdev.presentation.common.moxystrategies

import moxy.MvpView
import moxy.viewstate.ViewCommand
import moxy.viewstate.strategy.StateStrategy


class AddToEndSingleByTagStateStrategy : StateStrategy {

    override fun <View : MvpView?> beforeApply(currentState: MutableList<ViewCommand<View>>, incomingCommand: ViewCommand<View>) {
        currentState.removeAll { it.tag == incomingCommand.tag }
        currentState.add(incomingCommand)
    }

    override fun <View : MvpView?> afterApply(currentState: MutableList<ViewCommand<View>>, incomingCommand: ViewCommand<View>) {
        // Do nothing
    }
}