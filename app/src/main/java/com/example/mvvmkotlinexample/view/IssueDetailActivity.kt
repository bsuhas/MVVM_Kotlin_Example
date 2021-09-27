package com.example.mvvmkotlinexample.view

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.interfaces.CallBackListItemClickListener
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.utils.CommonFunctions
import com.example.mvvmkotlinexample.viewmodel.IssueDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recyclerview
import kotlinx.android.synthetic.main.activity_main.wp7progressBar
import kotlinx.android.synthetic.main.issue_details_layout.*
import kotlinx.android.synthetic.main.issue_details_layout.view.*

@Suppress("UNCHECKED_CAST")
class IssueDetailActivity : AppCompatActivity(), CallBackListItemClickListener {

    private var selectedIssueModel: IssueResponseModel? = null

    private lateinit var adapter: IssueListAdapter
    private lateinit var data: java.util.ArrayList<IssueResponseModel>
    lateinit var context: Context

    lateinit var issueDetailsViewModel: IssueDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issue_details_layout)

        issueDetailsViewModel = ViewModelProvider(this).get(IssueDetailsViewModel::class.java)
        selectedIssueModel =
            intent.getParcelableExtra(CommonFunctions.ISSUE_DATA) as? IssueResponseModel
        context = this@IssueDetailActivity


        actionBarInit()
        init()
        wp7progressBar.showProgressBar()
        if (selectedIssueModel != null) {
            selectedIssueModel!!.number?.let { getIssueListFromServer(it) }
        }

    }

    private fun actionBarInit() {
        supportActionBar?.apply {
            title = getString(R.string.issue_detail_screen)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun getIssueListFromServer(number: String) {
        issueDetailsViewModel.getCommentList(number)?.observe(this,
            Observer<List<Any?>?> { data ->
                if (data != null) {
                    if (data.isNotEmpty()) {
                        adapter.setIssueList(data as java.util.ArrayList<IssueResponseModel>)
                        wp7progressBar.hideProgressBar()
                        recyclerview.isVisible = true
                    } else {
                        showEmptyMessage()
                    }
                } else {
                    showEmptyMessage()
                }
            })
    }

    private fun showEmptyMessage() {
        tv_no_comments.isVisible = true
        recyclerview.isVisible = false
    }

    private fun init() {
        tv_title.text = selectedIssueModel?.title ?: ""
        tv_description.text = selectedIssueModel?.body ?: ""
        tv_username.text = selectedIssueModel?.user?.login ?: ""
        Picasso.with(iv_profile_image.context).load(selectedIssueModel?.user?.avatar_url)
            .placeholder(R.drawable.ic_default)
            .into(iv_profile_image)
        tv_updated_at.text =
            selectedIssueModel?.updated_at?.let { "Updated At " + CommonFunctions.formattedDate(it) }

        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()

        // This will pass the ArrayList to our Adapter
        adapter = IssueListAdapter(data, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        recyclerview.isVisible = false
    }

    override fun onListItemClickListener(issueResponseModel: IssueResponseModel) {
//        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
