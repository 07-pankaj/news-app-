package com.example.newbroadcast

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), newonclick {

    private val new_adapter:New_adapter = New_adapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleview:RecyclerView = findViewById(R.id.recycle_view)
       recycleview.layoutManager = LinearLayoutManager(this)
         fetch_data()
        recycleview.adapter = new_adapter
    }

    private fun fetch_data() {

        val item :ArrayList<New_model> = ArrayList()
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
               val artical = response.getJSONArray("articles")

                for(i in 0 until artical.length())
                {
                    val obj = artical.getJSONObject(i)
                    val news = New_model(obj.getString("author"),
                                        obj.getString("title"),
                                        obj.getString("url"),
                                        obj.getString("urlToImage")
                    )
                    item.add(news)
                }

                new_adapter.addresult(item)
            },
            { error ->
                Log.d("api","not working")
            }
        )

// Add the request to the RequestQueue.
        Mysingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onclick(url: String) {

        val builder :CustomTabsIntent.Builder  = CustomTabsIntent.Builder()
        val customTabsIntent :CustomTabsIntent  = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}