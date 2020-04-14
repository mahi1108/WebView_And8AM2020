package cubex.mahesh.webview_and8am2020

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wview.webViewClient = object:WebViewClient(){
            var pDialog = ProgressDialog(this@MainActivity)
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pDialog.setTitle("Message")
                pDialog.setMessage("Please wait page is loading...")
                pDialog.show()
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pDialog.dismiss()
            }

            override fun shouldOverrideUrlLoading(view: WebView?,
                                                  url: String?): Boolean {
                Toast.makeText(this@MainActivity,
                    url,Toast.LENGTH_LONG).show()
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        wview.settings.javaScriptEnabled = true
        wview.settings.builtInZoomControls = true
        wview.addJavascriptInterface(this@MainActivity,
            "myinterface")
    }
    fun search(v:View){
            when(v.id){
                R.id.search -> wview.loadUrl(et1.text.toString())
                R.id.fb ->  wview.loadUrl("https://www.facebook.com")
                R.id.google -> wview.loadUrl("https://www.google.com")
                R.id.youtube -> wview.loadUrl("https://www.youtube.com")
                R.id.html->wview.loadUrl("file:///android_asset/login.html")
            }
    }

    @JavascriptInterface
    fun callFromJS(name:String,pass:String){
        Toast.makeText(this@MainActivity,
            name+"\n"+pass,Toast.LENGTH_LONG).show()
    }
}
