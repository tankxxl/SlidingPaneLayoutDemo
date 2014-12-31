package com.umpay.slidingpanelayoutdemo;

import android.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * 实现一个书签的小例子
 */
public class MainActivity extends ActionBarActivity implements BookMarkerFragment.BookMarkListener {

    Fragment bookmarkerFragment;
    Fragment showFragment;
    SlidingPaneLayout spl = null;
    ActionBar actionBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = this.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        spl = (SlidingPaneLayout) this.findViewById(R.id.slidingpanelayout);
        spl.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {

            }

            @Override
            public void onPanelOpened(View view) {
                MainActivity.this.getSupportFragmentManager().
                        findFragmentById(R.id.leftfragment).setHasOptionsMenu(true);
            }

            @Override
            public void onPanelClosed(View view) {
                MainActivity.this.getSupportFragmentManager().
                        findFragmentById(R.id.leftfragment).setHasOptionsMenu(false);
            }
        });
        spl.openPane();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChangeBookmark(String bookmark) {
        ShowFragment sf = (ShowFragment) MainActivity.this.getSupportFragmentManager()
                .findFragmentById(R.id.rightfragment);
        WebView webView = sf.getWebView();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);
        webView.loadUrl(bookmark);
    }
}
