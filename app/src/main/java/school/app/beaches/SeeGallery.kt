package school.app.beaches

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.drawer_with_thumbs.*

class SeeGallery : AppCompatActivity() {

    private var mSectionsPagerAdapter: PagerAdapter? = null
    private var adapter: ThumbnailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_with_thumbs)
        val data_ = intent.getParcelableExtra<ThumbnailItem>(ViewPagerAdapter.THUMBNAIL_ITEM)
        val images = data_.getImages(this)
        val position = intent.getIntExtra(BeachDetail.GALLERY_POSITION, 0)
        val data = images.map { ThumbnailItem("", it, "") }
        mSectionsPagerAdapter = ViewPagerAdapter(data)

        adapter = ThumbnailAdapter(data) {
            container.setCurrentItem(it, true)
        }

        container.adapter = mSectionsPagerAdapter
        rvBeaches.adapter = adapter
        rvBeaches.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        container.setCurrentItem(position, true)
    }
}
