package com.liferay.vulcan.blog.postings.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.liferay.vulcan.blog.postings.R
import com.liferay.vulcan.blog.postings.viewholder.BlogPostingViewHolder
import com.liferay.vulcan.consumer.delegates.bindNonNull
import com.liferay.vulcan.consumer.model.Thing
import com.liferay.vulcan.consumer.screens.ScreenletEvents
import com.liferay.vulcan.consumer.screens.ThingScreenlet
import com.liferay.vulcan.consumer.screens.views.BaseView
import com.liferay.vulcan.consumer.screens.views.CollectionView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), ScreenletEvents {

    val thingScreenlet by bindNonNull<ThingScreenlet>(R.id.thing_screenlet)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = "http://192.168.0.156:8080/o/api/group/20143/p/blogs"

        thingScreenlet.load(id) {
            (it.viewModel as CollectionView).customLayout = R.layout.blog_posting_row to ::BlogPostingViewHolder
        }

        thingScreenlet.screenletEvents = this
    }

    override fun <T : BaseView> onClickEvent(baseView: T, view: View, thing: Thing) = View.OnClickListener {
        startActivity<DetailActivity>("id" to thing.id)
    }

}