package com.jam.cookingina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.jam.cookingina.Database.AppDatabase
import com.jam.cookingina.Database.CookingDatabaseCopier
import com.jam.cookingina.Database.CookingModel
import com.jam.cookingina.Database.CookingRepository
import com.jam.cookingina.Database.CookingViewModel
import com.jam.cookingina.Database.CookingViewModelFactory
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var cookingViewModel: CookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        CookingDatabaseCopier.copyDatabaseFromAssets(this)

        val repository = CookingRepository(AppDatabase.getDatabase(this).cookingInaDao())
        cookingViewModel = ViewModelProvider(
            this,
            CookingViewModelFactory(repository)
        )[CookingViewModel::class.java]

        cookingViewModel.allData.observe(this) { datas ->
            // Handle the data here
            Log.e("TABLEVIEWMODEL", datas.toString())
            // Check if you have at least one data item
            viewPager(datas)
        }
    }

    private fun viewPager(data1: List<CookingModel>) {

        val fragments = mutableListOf<MainpageFragment>()
        if (data1.isNotEmpty()) {
            for (i in data1.indices) {
                // fragments.add(MainpageFragment.newInstance(data, drawableName))
                fragments.add(
                    MainpageFragment.newInstance(
                        data1[0].Category,
                        data1[0].Category!!.toLowerCase(Locale.ROOT)
                    )
                )
                fragments.add(
                    MainpageFragment.newInstance(
                        data1[4].Category,
                        data1[4].Category!!.toLowerCase(Locale.ROOT)
                    )
                )
                fragments.add(
                    MainpageFragment.newInstance(
                        data1[8].Category,
                        data1[8].Category!!.toLowerCase(Locale.ROOT)
                    )
                )
                fragments.add(
                    MainpageFragment.newInstance(
                        data1[12].Category,
                        data1[12].Category!!.toLowerCase(Locale.ROOT)
                    )
                )
                fragments.add(
                    MainpageFragment.newInstance(
                        data1[16].Category,
                        data1[16].Category!!.toLowerCase(Locale.ROOT)
                    )
                )
                fragments.add(
                    MainpageFragment.newInstance(
                        data1[20].Category,
                        data1[20].Category!!.toLowerCase(Locale.ROOT)
                    )
                )
            }
        }
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val adapter = MyFragmentAdapter(this, fragments)
        viewPager.adapter = adapter

// You need to retain one page on each side so that the next and previous items are visible
        viewPager.offscreenPageLimit = 1

// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)
    }
}