package com.t.mediacorp2359pocs.presentation.oc170

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.utils.toast
import kotlinx.android.synthetic.main.activity_oc170.*

class Oc170Activity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc170Activity::class.java)
        }

        private const val BAD_SAMPLE_IFRAME_1 = """<iframe name="lb24" frameborder="0" height="660px" width="100%" class="lb24-iframe" scrolling="auto" src="https://v.24liveblog.com/iframe/?id=2457291385143869393"></iframe><script src="https://v.24liveblog.com/iframe.js"></script>"""

        private const val BAD_SAMPLE_IFRAME_2 = """<iframe allow="encrypted-media" allowtransparency="true" frameborder="0" height="909" scrolling="no" src="https://www.facebook.com/plugins/post.php?href=https%3A%2F%2Fwww.facebook.com%2Fmindefsg%2Fposts%2F10156320135991059&amp;width=500" style="border:none;overflow:hidden" width="500"></iframe><iframe allow="encrypted-media" allowtransparency="true" frameborder="0" height="631" scrolling="no" src="https://www.facebook.com/plugins/post.php?href=https%3A%2F%2Fwww.facebook.com%2Fleehsienloong%2Fposts%2F3236536699742347&amp;width=500" style="border:none;overflow:hidden" width="500"></iframe>

<iframe src="https://www.facebook.com/plugins/post.php?href=https%3A%2F%2Fwww.facebook.com%2Fleehsienloong%2Fposts%2F3243873485675335&width=500" width="500" height="917" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowTransparency="true" allow="encrypted-media"></iframe>

<script async defer src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2"></script> <div class="fb-post" data-href="https://www.facebook.com/mindefsg/posts/10156320135991059/" data-width="500"></div>

<script async defer src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2"></script> <div class="fb-post" data-href="https://www.facebook.com/leehsienloong/posts/3243873485675335/" data-width="500"></div>"""

        private const val JSON_API_PARAGRAPH_DEFAULT = """<iframe src="https://infographics.channelnewsasia.com/covid-19/map.html" height="200" width="300" title="Iframe Example"></iframe>"""

        private const val SAMPLE_REST_EXPORT_VIEWS_1 = """https://infographics.channelnewsasia.com/covid-19/map.html"""
        private const val SAMPLE_REST_EXPORT_VIEWS_2 = """<iframe src="https://infographics.channelnewsasia.com/covid-19/map.html" height="200" width="300" title="Iframe Example"></iframe>"""


        val IFRAMES = listOf(
            BAD_SAMPLE_IFRAME_1,
            BAD_SAMPLE_IFRAME_2,
            JSON_API_PARAGRAPH_DEFAULT,
            SAMPLE_REST_EXPORT_VIEWS_1,
            SAMPLE_REST_EXPORT_VIEWS_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc170)

        setUpViews()

        toast("Swipe to move between pages\n<-   ->")
    }

    private fun setUpViews() {
        val adapter = PageAdapter(supportFragmentManager, IFRAMES)
        vpContent.adapter = adapter
        vpContent.offscreenPageLimit = adapter.count
    }
}