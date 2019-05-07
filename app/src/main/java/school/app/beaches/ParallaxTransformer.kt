package school.app.beaches

import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.viewpager_content.view.*


class ParallaxTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {

        val pageWidth = view.getWidth()


        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1F)

        } else if (position <= 1) { // [-1,1]

            view.ivImage.setTranslationX(-position * (pageWidth / 2)) //Half the normal speed

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1F)
        }


    }
}