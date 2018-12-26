package ansters.me.todosomeday.ui.home


import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import ansters.me.todosomeday.R
import ansters.me.todosomeday.base.BaseFragment
import ansters.me.todosomeday.databinding.FragmentHomeBinding
import ansters.me.todosomeday.util.Util
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setLifecycleOwner(this)
        binding.homeVM = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.btnAdd.isEnabled = false
        initAddTaskListener()
        subscribedUI()
    }

    private fun initAddTaskListener() {
        binding.edTask.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            binding.homeVM?.addTaskFocusChange(hasFocus)
        }
        binding.dim.setOnClickListener {
            clearFocus()
        }
    }

    private fun clearFocus() {
        binding.homeVM?.addTaskFocusChange(false)
        binding.edTask.clearFocus()
        Util.hideSoftKeyboard(context!!, view!!)
    }

    private fun subscribedUI() {
        binding.homeVM?.isCurrentlyAddTask?.observe(viewLifecycleOwner, Observer { isNowAddTask ->
            binding.dim.visibility = if (isNowAddTask) View.VISIBLE else View.GONE
        })
    }

}
