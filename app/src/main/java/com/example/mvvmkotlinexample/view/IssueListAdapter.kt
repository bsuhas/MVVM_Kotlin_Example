package com.example.mvvmkotlinexample.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.interfaces.CallBackListItemClickListener
import com.example.mvvmkotlinexample.model.IssueResponseModel
import com.example.mvvmkotlinexample.utils.CommonFunctions
import com.squareup.picasso.Picasso
import java.util.*

class IssueListAdapter(
    private val mList: ArrayList<IssueResponseModel>,
    private val callBackListItemClickListener: CallBackListItemClickListener
) :
    RecyclerView.Adapter<IssueListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.issue_list_item_layout, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.title.text = itemsViewModel.title ?: ""
        holder.desc.text = itemsViewModel.body ?: ""
        holder.username.text = itemsViewModel.user?.login ?: ""

        Picasso.with(holder.imageView.context).load(itemsViewModel.user?.avatar_url)
            .placeholder(R.drawable.ic_default)
            .into(holder.imageView);

        val formatted: String = CommonFunctions.formattedDate(itemsViewModel.updated_at.toString())
        holder.updatedTime.text = "Updated At $formatted"

        holder.itemView.setOnClickListener { view ->
            callBackListItemClickListener.onListItemClickListener(itemsViewModel)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setIssueList(arrayList: ArrayList<IssueResponseModel>) {
        mList.addAll(arrayList);
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: de.hdodenhof.circleimageview.CircleImageView =
            itemView.findViewById(R.id.iv_profile_image)
        val username: TextView = itemView.findViewById(R.id.tv_username)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val desc: TextView = itemView.findViewById(R.id.tv_description)
        val updatedTime: TextView = itemView.findViewById(R.id.tv_updated_at)
    }
}
