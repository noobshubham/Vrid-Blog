package org.eu.noobshubham.vrid

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.eu.noobshubham.vrid.adapter.BlogAdapter
import org.eu.noobshubham.vrid.model.Blog
import org.eu.noobshubham.vrid.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set the title
        supportActionBar?.title = "Vrid Blog"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        blogAdapter = BlogAdapter(this)
        recyclerView.adapter = blogAdapter

        // Retrofit initialization
        val retrofit = Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        val call = api.getBlogList(1, 10) // Initial call with page 1 and 10 items per page

        call.enqueue(object : Callback<List<Blog>> {
            override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                if (response.isSuccessful) {
                    val blogs = response.body()
                    blogAdapter.setBlogs(blogs)
                }
            }

            override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
