package Camera

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.Main_ui.R

//가이드라인 이미지를 스위핑해서 바꾸는 기능 guideline image swiping with "ViewPager"
class Adapter(private val context: Context) : PagerAdapter() {

    private val images = intArrayOf(R.drawable.guide_basic, R.drawable.guide_girl, R.drawable.guide_sitboy,R.drawable.guide_momgirl)
    private var inflater: LayoutInflater? = null


    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View,`object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater!!.inflate(R.layout.slider, null)
        val imageView = v.findViewById<View>(R.id.imageView) as ImageView

        imageView.setImageResource(images[position])
        val vp = container as ViewPager
        vp.addView(v, 0)

        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.invalidate()
    }
}
