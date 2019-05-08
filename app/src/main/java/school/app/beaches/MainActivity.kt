package school.app.beaches

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.drawer_with_thumbs.*

class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: PagerAdapter? = null
    private var adapter: ThumbnailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_with_thumbs)
        val data = prepareData()

        mSectionsPagerAdapter = ViewPagerAdapter(data)

        adapter = ThumbnailAdapter(data) {
            container.setCurrentItem(it, true)
        }

        container.adapter = mSectionsPagerAdapter
        rvBeaches.adapter = adapter
        container.addOnPageChangeListener(onPageChange())

        container.setPageTransformer(false, ParallaxTransformer())

        rvBeaches.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun onPageChange(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageSelected(p0: Int) {
                Log.i(localClassName, "Page on position $p0")
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    private fun prepareData(): List<ThumbnailItem> {
        val beaches = resources.getStringArray(R.array.beaches)
        val beachDescs = resources.getStringArray(R.array.beachesDesc)
        val thumbs = resources.obtainTypedArray(R.array.thumbs)
        val data = beaches.withIndex()
            .map { ThumbnailItem(it.value, thumbs.getResourceId(it.index, -1), beachDescs[it.index]) }
        thumbs.recycle()
        return data
    }

}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}