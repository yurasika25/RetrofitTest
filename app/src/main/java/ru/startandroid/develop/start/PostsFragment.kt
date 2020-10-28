package ru.startandroid.develop.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main_recycler.*
import kotlinx.android.synthetic.main.fragment_main_recycler.view.*
import ru.startandroid.develop.R
import ru.startandroid.develop.adapter.MyAdapter
import ru.startandroid.develop.scroll.AddEditFragment
import ru.startandroid.develop.empty.EmptyFragment
import ru.startandroid.develop.emptytwo.EmptyTwoFragment
import ru.startandroid.develop.retrofittest.model.Post

class PostsFragment : Fragment(), PostsFragmentView {

    private var presenter: PostsFragmentPresenter? = null

    companion object {
        fun newInstance(): PostsFragment {
            val args = Bundle()
            val fragment = PostsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onPause() {
        super.onPause()
        presenter?.exitFromView()
    }

    override fun onResume() {
        super.onResume()
        presenter?.enterWithView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_main_recycler, container, false)
        presenter = PostsFragmentPresenter()
        view.FAT.setOnClickListener { presenter!!.onFatButtonClicked() }
        return view
    }

    override fun navigateToAddEdit() {
        val fragment : Fragment = AddEditFragment.newInstance()
        val fm = requireActivity().supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.mainContainer,fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun navigateToEmptyTwo() {
        val fragment : Fragment = EmptyTwoFragment.newInstance()
        val fm = requireActivity().supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.mainContainer,fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
    override fun navigateToEmpty() {
        val fragment: Fragment = EmptyFragment.newInstance()
        val fm = requireActivity().supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.mainContainer, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun setUpUI(data : List<Post>) {
        recyclerViewTwo.setHasFixedSize(true)
        recyclerViewTwo.layoutManager = LinearLayoutManager(context)
        val adapter =  MyAdapter(data, {presenter?.onFatButtonClicked()}, {presenter?.onPenButtonClicked()})
        recyclerViewTwo.adapter = adapter
    }
}
