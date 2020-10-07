package ru.startandroid.develop.retrofittest

import ru.startandroid.develop.retrofittest.main.MainView
import java.lang.ref.WeakReference

abstract class BasicPresenter <V : MainView?> {
    private var view: WeakReference<V>? = null
    private fun bindView(view: V) {
        this.view = WeakReference(view)
    }
    open  fun onEnterScope() {}
    open  fun onExitScope() {}
    protected fun getView(): V? {
        return view!!.get()
    }

    fun enterWithView(v: V) {
        bindView(v)
        onEnterScope()
    }

    open fun exitFromView() {
        onExitScope()
        unbindView()
    }

    private fun unbindView() {
        view = null
    }
}