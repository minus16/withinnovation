package com.test.withinnovation.view.base.BaseMVP

interface BaseView<T : BasePresenter>{

    fun setPresenter(presenter:T)

    fun showProgress(b:Boolean)

    fun showError(a:Int, str:String)

}