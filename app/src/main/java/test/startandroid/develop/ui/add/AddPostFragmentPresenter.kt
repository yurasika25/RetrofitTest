package test.startandroid.develop.ui.add

import test.startandroid.develop.mvp.BasicPresenter

class AddPostFragmentPresenter : BasicPresenter<AddPostFragmentView?>(){


    override fun onEnterScope() {
        super.onEnterScope()
        getView()?.setUpUI()
    }
}
