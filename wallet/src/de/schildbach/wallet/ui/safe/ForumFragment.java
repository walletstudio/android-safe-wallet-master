package de.schildbach.wallet.ui.safe;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.widget.ProgressBar;
import de.schildbach.wallet_test.R;

/**
 * 论坛
 * @author zhangmiao
 */
public class ForumFragment extends BaseFragment{

    private WebView mWebView;
    private ProgressBar proBar;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_forum;
    }

    @Override
    public void initView() {
        super.initView();

        proBar = (ProgressBar) findViewById(R.id.proBar);
        mWebView = (WebView) findViewById(R.id.webView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getActivity().setTitle(getString(R.string.safe_forum_title));
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        mWebView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_bright));
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    proBar.setVisibility(View.VISIBLE);
                    proBar.setProgress(newProgress);
                } else {
                    proBar.setProgress(100);
                    proBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            proBar.setVisibility(View.GONE);
                        }
                    }, 500);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        //自适应屏幕
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);//这个很关键
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        mWebView.loadUrl("http://www.anwang.org/");
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            handler.proceed();// 接受所有网站的证书
        }
    }

    @Override
    public boolean onBackPressed() {
        super.onBackPressed();
        if (mWebView.getUrl() != null && mWebView.getUrl().trim().contains("http://www.anwang.org/portal.php")){
            getActivity().finish();
        }
        else if(mWebView.canGoBack()){
            mWebView.goBack();
        }
        else {
            getActivity().finish();
        }
        return true;
    }

}
