package school.app.beaches

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.viewpager_content.view.*

class ViewPagerAdapter(private val listData: List<ThumbnailItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.viewpager_content, container, false)
        ItemHolder(view).bind(getItem(position))
        container.addView(view)
        return view
    }

    private fun getItem(position: Int) = listData[position]

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View?)

    override fun getCount() = listData.size

    class ItemHolder(val view: View) {
        fun bind(item: ThumbnailItem) {
            view.apply {
                tvBeach.text = item.name
                Glide.with(context).load(item.thumb).into(ivImage)
                btnViewDetail.setOnClickListener {
                    context.startActivity(Intent(context, BeachDetail::class.java).apply {
                        putExtra(THUMBNAIL_ITEM, item)
                    })
                }
            }
        }
    }

    companion object {
        const val THUMBNAIL_ITEM = "THUMBNAIL_ITEM"
    }

}