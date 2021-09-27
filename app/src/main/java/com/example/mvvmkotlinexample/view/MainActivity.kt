package com.example.mvvmkotlinexample.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.interfaces.CallBackListItemClickListener
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.utils.CommonFunctions
import com.example.mvvmkotlinexample.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CallBackListItemClickListener {

    private lateinit var adapter: IssueListAdapter
    private lateinit var data: java.util.ArrayList<IssueResponseModel>
    lateinit var context: Context

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        init()
        wp7progressBar.showProgressBar()
        getIssueListFromServer()

    }

    private fun getIssueListFromServer() {
        mainActivityViewModel.getIssueList()?.observe(this,
            Observer<List<Any?>?> { data ->
                adapter.setIssueList(data as java.util.ArrayList<IssueResponseModel>)
                wp7progressBar.hideProgressBar()
                recyclerview.isVisible = true
            })
    }

    private fun init() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList();

        adapter = IssueListAdapter(data, this)

        recyclerview.adapter = adapter
        recyclerview.isVisible = false
    }

    override fun onListItemClickListener(issueResponseModel: IssueResponseModel) {
        val i = Intent(this, IssueDetailActivity::class.java)
            i.putExtra(CommonFunctions.ISSUE_DATA, issueResponseModel)
             startActivity(i)
    }
}
