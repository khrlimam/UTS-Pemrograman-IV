package school.app.beaches

import android.content.Context
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.thumbnail.view.*

class ThumbnailAdapter(private val data: List<ThumbnailItem>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.thumbnail, p0, false), onClick)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(data[p1])
    }

    fun setActivePosition(position: Int) {

    }

    class ViewHolder(view: View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: ThumbnailItem) {
            itemView.apply {
                Glide.with(context).load(item.thumb).into(ivImage)
                setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}


@Parcelize
data class ThumbnailItem(val name: String, val thumb: Int, val desc: String) : Parcelable {
    fun getImages(ctx: Context): List<Int> {
        return (1..10).map {
            ctx.getResources().getIdentifier("${name.toLowerCase()}_$it", "drawable", ThumbnailItem::class.java.`package`?.name);
        }
    }
}