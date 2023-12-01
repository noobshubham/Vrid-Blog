package org.eu.noobshubham.vrid

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView: WebView = findViewById(R.id.webView)
        val url = intent.getStringExtra("url")

        if (url != null) {
            webView.loadUrl(url)
        } else webView.loadUrl("https://dontasktoask.com/")
    }
}