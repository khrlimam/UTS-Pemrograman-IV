package school.app.beaches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.beach_image.view.*

class BeachImageAdapter(private val data: List<Int>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<BeachImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.beach_image, p0, false), onClick)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(data[p1])
    }

    class ViewHolder(view: View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: Int) {
            itemView.apply {
                Glide.with(context).load(item).into(ivImage)
                setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}
