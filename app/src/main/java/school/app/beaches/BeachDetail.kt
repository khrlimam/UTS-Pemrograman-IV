package school.app.beaches

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_beach_detail.*
import kotlinx.android.synthetic.main.content_beach_detail.*

class BeachDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beach_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<ThumbnailItem>(ViewPagerAdapter.THUMBNAIL_ITEM)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Will redirect you to the map", Snackbar.LENGTH_LONG)
                .setAction("Nothing", null).show()
        }
        tvDesc.text = data.desc
        toolbar_layout.title = data.name
        toolbar.title = data.name
        rvImages.layoutManager = GridLayoutManager(this, 2)
        rvImages.adapter = BeachImageAdapter(data.getImages(this)) {}
        Glide.with(this).load(data.thumb).into(ivImageHeader)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}
