package ansters.me.todosomeday.ui.home


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
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

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeVM: HomeViewModel

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
        homeVM = binding.homeVM!!
        subscribedUI()

        homeVM.dateQuery.value = Util.getDateToday()
        initAddTaskListener()
    }

    private fun initAddTaskListener() {
        binding.btnAdd.isEnabled = false // init add task button
        binding.edTask.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            binding.homeVM?.editTextFocusChange(hasFocus)
        }
        binding.btnAdd.setOnClickListener {
            AsyncTask.execute { // room cannot access DB on main thread, So we need to called it on background
                homeVM.submitNewTodo()
            }
        }
        binding.dim.setOnClickListener {
            clearFocus()
        }
    }

    private fun clearFocus() {
        binding.edTask.clearFocus()
        Util.hideSoftKeyboard(context!!, view!!)
    }

    private fun subscribedUI() {
        homeVM.isCurrentlyAddTask.observe(viewLifecycleOwner, Observer { isNowAddTask ->
            binding.dim.visibility = if (isNowAddTask) View.VISIBLE else View.GONE // make dim visible when user add task, else make it gone
            binding.btnAdd.visibility = if (isNowAddTask) View.VISIBLE else View.GONE // make add button visible when user add task, else make it gone
        })

        homeVM.isEditTaskNotEmpty.observe(viewLifecycleOwner, Observer { isTaskNotEmpty ->
            binding.btnAdd.visibility = if (isTaskNotEmpty) View.VISIBLE else View.GONE // when user stop typing, observe edittext empty or not. if true make button visible, else make it gone
        })

        homeVM.task.observe(viewLifecycleOwner, Observer { task ->
            binding.btnAdd.isEnabled = !task.isEmpty() // enable button base on text is empty or not
        })

        homeVM.dateQuery.observe(viewLifecycleOwner, Observer { date ->
            if (!date.isEmpty()) {
                homeVM.dateSelected.value = Util.convertFormatDateToTextDate(context!!, date) // convert raw date to readable date
            }
        })

        homeVM.todoList.observe(viewLifecycleOwner, Observer { todos ->

        })

        homeVM.isAddTaskCompleted.observe(viewLifecycleOwner, Observer { completed ->
            if (completed) {
                homeVM.reset()
                clearFocus()
            }
        })
    }

}
