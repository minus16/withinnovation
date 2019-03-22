package com.test.withinnovation.view.base.BaseMVP

abstract class AbstractPresenter<V :BaseView<*>>  : BasePresenter{

    var view:V

    constructor(view: V) {
        this.view = view

        //view.setPresenter()
    }
}