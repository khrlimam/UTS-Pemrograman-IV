package school.app.beaches

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_beach_detail.fab
import kotlinx.android.synthetic.main.activity_beach_detail.ivImageHeader
import kotlinx.android.synthetic.main.activity_beach_detail.rvImages
import kotlinx.android.synthetic.main.activity_beach_detail.toolbar
import kotlinx.android.synthetic.main.activity_beach_detail.toolbar_layout
import kotlinx.android.synthetic.main.content_beach_detail.tvDesc

class BeachDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beach_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<ThumbnailItem>(ViewPagerAdapter.THUMBNAIL_ITEM)
        fab.setOnClickListener { view ->
            // Create a Uri from an intent string. Use the result to create an Intent.
            val gmmIntentUri = Uri.parse("http://maps.google.com/maps?q=${data.location}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            } ?: Snackbar.make(view, "No sufficient app found", Snackbar.LENGTH_LONG)
                .setAction("Nothing", null).show()
        }
        tvDesc.text = data.desc
        toolbar_layout.title = data.name
        toolbar.title = data.name
        rvImages.layoutManager = GridLayoutManager(this, 2)
        rvImages.adapter = BeachImageAdapter(data.getImages(this)) {
            startActivity(Intent(this, SeeGallery::class.java).apply {
                putExtra(GALLERY_POSITION, it)
                putExtra(ViewPagerAdapter.THUMBNAIL_ITEM, data)
            })
        }
        Glide.with(this).load(data.thumb).into(ivImageHeader)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val GALLERY_POSITION = "GALLERY POSITION"
    }

}
