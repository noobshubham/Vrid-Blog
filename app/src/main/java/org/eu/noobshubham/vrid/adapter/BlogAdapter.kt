package org.eu.noobshubham.vrid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.eu.noobshubham.vrid.R
import org.eu.noobshubham.vrid.WebViewActivity
import org.eu.noobshubham.vrid.model.Blog

class BlogAdapter(private val context: Context) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    private var blogs: List<Blog>? = null

    fun setBlogs(blogs: List<Blog>?) {
        this.blogs = blogs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = blogs?.get(position)
        holder.titleTextView.text = blog?.title?.rendered

        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", blog?.link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return blogs?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }
}
