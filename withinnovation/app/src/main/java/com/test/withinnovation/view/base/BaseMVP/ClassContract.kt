package com.test.withinnovation.view.base.BaseMVP

import java.util.*

interface ClassContract {
    interface View : BaseView<Presenter>{
        fun updateUI(obj:Object)
    }

    interface Presenter : BasePresenter{
        fun connect()
    }
}