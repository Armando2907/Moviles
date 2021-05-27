package ni.edu.uca.moviles2.arboretocarmelopalma.ui.trees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ni.edu.uca.moviles2.arboretocarmelopalma.R

class TreeInfoFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Layout para este fragment
        val view = inflater.inflate(R.layout.fragment_info_tree, container, false)
        val myWebView: WebView = view.findViewById(R.id.webview)

        var urlString = arguments?.getString("pagina")

        if (urlString != null) {
            myWebView.loadUrl(urlString)
        }

        // Obliga a usar webview en lugar de un navegador
        myWebView.setWebViewClient(WebViewClient())
        return view
    }
}