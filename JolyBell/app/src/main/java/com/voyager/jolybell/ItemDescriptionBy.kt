package com.voyager.jolybell

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.voyager.jolybell.adapters.GaleryAdapter
import com.voyager.jolybell.adapters.PointsRecomindationAdapter
import com.voyager.jolybell.adapters.RecommendationsAdapter
import com.voyager.jolybell.adapters.SizeListAdapter
import com.voyager.jolybell.algotitms.AlertManager
import com.voyager.jolybell.algotitms.RecomindationsAlgoritm
import com.voyager.jolybell.model.*
import com.voyager.jolybell.model.basket.Basket
import com.voyager.jolybell.model.basket.BasketStaticData
import com.voyager.jolybell.model.basket.ItemInBasketBuilder
import kotlin.concurrent.thread

class ItemDescriptionBy : AppCompatActivity(), SelectSizeListener {

    private var fromePage: String = ""

    private lateinit var item_description_name: TextView
    private lateinit var item_description_img: ImageView
    private lateinit var item_description_galery: ViewPager
    lateinit var galery_count: RecyclerView

    private lateinit var item_description_cost: TextView
    private lateinit var item_description_description: TextView
    private lateinit var item_description_sub: TextView
    private lateinit var item_description_count: TextView
    private lateinit var item_description_add: TextView
    private lateinit var item_description_size_chart: TextView
    private lateinit var item_description_care_of_the_thing: TextView
    private lateinit var item_size_list: RecyclerView

    // Alert
    private lateinit var alert_care_of_the_thing: RelativeLayout
    private lateinit var alert_dialog: RelativeLayout
    private lateinit var alert_dialog_block: RelativeLayout
    private var alert_open: Boolean = false
    private var alertType: String = ""

    // Basket
    private lateinit var item_description_add_to_basket: TextView
    private lateinit var item_description_preloader: RelativeLayout

    // Recommendations
    private lateinit var recommendations_view: ViewPager
    lateinit var recommendations_points_count: RecyclerView

    private lateinit var item: ItemFromShop

    private var itemInBasketId: Int = 0

    var galery_selected : Int = 0
    var recommendation_selected : Int = 0

    private var itemSelected: Int = 0

    private var itemsCountToBy: Int = 1
    private val itemsCountToByMax: Int = 20

    private var itemSizeId: Int = 0

    lateinit var list_recomendation: ArrayList<Int>

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_description_by)

        item_description_name = findViewById(R.id.item_description_name)
        item_description_img = findViewById(R.id.item_description_img)
        item_description_galery = findViewById(R.id.item_description_galery)
        galery_count = findViewById(R.id.galery_count)
        item_description_cost = findViewById(R.id.item_description_cost)
        item_description_description = findViewById(R.id.item_description_description)
        item_description_sub = findViewById(R.id.item_description_sub)
        item_description_count = findViewById(R.id.item_description_count)
        item_description_add = findViewById(R.id.item_description_add)
        item_description_size_chart = findViewById(R.id.item_description_size_chart)
        item_description_care_of_the_thing = findViewById(R.id.item_description_care_of_the_thing)
        item_size_list = findViewById(R.id.item_size_list)
        // Alert
        alert_care_of_the_thing = findViewById(R.id.alert_care_of_the_thing)
        alert_dialog = findViewById(R.id.alert_dialog)
        alert_dialog_block = findViewById(R.id.alert_dialog_block)
        // Basket
        item_description_add_to_basket = findViewById(R.id.item_description_add_to_basket)
        item_description_preloader = findViewById(R.id.item_description_preloader)

        // Recommendations
        recommendations_view = findViewById(R.id.recommendations_view)
        recommendations_points_count = findViewById(R.id.recommendations_points_count)

        fromePage = intent.getStringExtra("fromePage").toString()

        itemSelected = intent.getIntExtra("itemSelected", -1)



        // Load data
        if(itemSelected != -1){

            if(fromePage == "basket"){
                findViewById<LinearLayout>(R.id.itemsToAddInBasket).visibility = View.GONE

                itemInBasketId = intent.getIntExtra("itemInBasketId", 0)
                itemsCountToBy = Basket.items.get(itemInBasketId).countItems
            }

            item = CatalogItems.items.get(itemSelected)

            setTheme(item.theme)

            // Убирает кнопки 'Размерная сетка' и 'Уход за вещью'
            if(item.categoryId == 10){
                findViewById<LinearLayout>(R.id.dress_buttons).visibility = View.GONE
            }

            // Если кол-во картинок <=1, то скрывает поинты перелистования
            if(item.img.size <= 1){
                findViewById<RelativeLayout>(R.id.item_description_points_galery_obj).visibility = View.GONE
            }

            // Установить размер вещи
            itemSizeId = 0

            item_description_name.text = item.name
            item_description_cost.text = item.costToString()
            item_description_description.text = item.description
            item_description_count.text = itemsCountToBy.toString()

            // Анимация картинки
            item_description_img.visibility = View.VISIBLE
            item_description_galery.visibility = View.GONE
            Picasso.with(this)
                .load(item.img.get(0))
                .into(item_description_img)
            thread {
                Thread.sleep(500)
                runOnUiThread {
                  item_description_galery.visibility = View.VISIBLE
                }
            }
            findViewById<ViewPager>(R.id.item_description_galery).setOnTouchListener { v, event ->
                if(event.action == MotionEvent.ACTION_DOWN){
                    item_description_img.visibility = View.GONE
                    item_description_galery.visibility = View.VISIBLE
                }
                return@setOnTouchListener false
            }

            // Если item имеет разные размеры, показать меню выбора размера
            if(item.sizes.size > 0){
                var lm: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                item_size_list.layoutManager = lm
                var idSelected = 0
                if(fromePage == "basket"){
                    idSelected = Basket.items.get(itemInBasketId).sizeId
                }
                item_size_list.adapter =
                    SizeListAdapter(this, item.sizes, this, idSelected)
            }else{
                findViewById<LinearLayout>(R.id.item_size).visibility = View.GONE
            }

            // Галерея фотографий
            var adapter = GaleryAdapter(this, item.img)
            item_description_galery.adapter = adapter

            // Пункты галереи
            var lm = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            galery_count.layoutManager = lm
            galery_count.adapter =
                PointsRecomindationAdapter(this, item.img.size, item.theme)

            item_description_galery.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

                override fun onPageSelected(position: Int) {
                    if (item.theme == ItemFromShop.Theme.Dark) {
                        galery_count.get(galery_selected).findViewById<RelativeLayout>(R.id.point)
                            .setBackgroundResource(R.drawable.point_basic)
                    } else {
                        galery_count.get(galery_selected).findViewById<RelativeLayout>(R.id.point)
                            .setBackgroundResource(R.drawable.point_basic_dark)
                    }

                    galery_selected = position

                    if (item.theme == ItemFromShop.Theme.Dark) {
                        galery_count.get(galery_selected).findViewById<RelativeLayout>(R.id.point)
                            .setBackgroundResource(R.drawable.point_fill)
                    } else {
                        galery_count.get(galery_selected).findViewById<RelativeLayout>(R.id.point)
                            .setBackgroundResource(R.drawable.point_fill_dark)
                    }
                }
            })
        }

        // Alert create
        AlertCreate()

        //--------- Listeners ---------

        item_description_size_chart.setOnClickListener {
            alertType = "alert_size_cart"
            var alert_layout_id = 0
            var categoryId = item.categoryId
            when (categoryId){
                0 -> alert_layout_id = R.layout.alert_size_chart_t_shirt
                1 -> alert_layout_id = R.layout.alert_size_chart_sweatshirt
            }
            AlertOpen(alert_layout_id)
        }

        item_description_care_of_the_thing.setOnClickListener {
            alertType = "care_of_the_thing"
            AlertOpen(R.layout.alert_recomendation)
        }


        // Count
        item_description_sub.setOnClickListener {
            if(itemsCountToBy > 1){
                itemsCountToBy--
                item_description_count.text = itemsCountToBy.toString()

                if(fromePage == "basket"){
                    Basket.items.get(itemInBasketId).countItems = itemsCountToBy
                    BasketStaticData.basketItemChangedObserver.basketItemChangedObserver()
                    Basket.change()
                }
            }
        }
        item_description_add.setOnClickListener {
            if(itemsCountToBy < itemsCountToByMax){
                itemsCountToBy++
                item_description_count.text = itemsCountToBy.toString()

                if(fromePage == "basket"){
                    Basket.items.get(itemInBasketId).countItems = itemsCountToBy
                    BasketStaticData.basketItemChangedObserver.basketItemChangedObserver()
                    Basket.change()
                }
            }
        }

        // Basket
        item_description_add_to_basket.visibility = View.VISIBLE
        item_description_preloader.visibility = View.GONE
        item_description_add_to_basket.setOnClickListener {
            // Start animation loading
            item_description_add_to_basket.visibility = View.GONE
            item_description_preloader.visibility = View.VISIBLE
            thread {
                Thread.sleep(500)
                runOnUiThread {
                    item_description_add_to_basket.visibility = View.VISIBLE
                    item_description_preloader.visibility = View.GONE
                    Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_LONG).show()
                }
            }

            // Add to basket
            Basket.addItemInBasket(
                ItemInBasketBuilder()
                    .setItemId(item.itemId)
                    .setCountItems(itemsCountToBy)
                    .setSizeItems(itemSizeId)
                    .build()
            )
        }


        // Recommendations
        loadRecommendations()
    }

    override fun onBackPressed() {
        if (alert_open){
            AlertClose()
        }
        item_description_img.visibility = View.VISIBLE
        item_description_galery.visibility = View.GONE

        super.onBackPressed()
    }

    private fun AlertCreate(){
        // Alert care of the thing
        alert_care_of_the_thing.visibility = View.GONE
        alert_dialog.visibility = View.GONE
        alert_dialog_block.setOnClickListener {  }
        alert_care_of_the_thing.setOnClickListener { AlertClose() }
    }

    private fun AlertOpen(resourceId: Int){
        alert_open = true
        alert_dialog_block.removeAllViews()
        val alert_recomendation_view = layoutInflater.inflate(resourceId, alert_dialog_block, false)
        with(alert_dialog_block) {
            addView(alert_recomendation_view)
        }

        var btn_alert_care_of_the_thing_close: ImageView = findViewById(R.id.btn_alert_care_of_the_thing_close)
        btn_alert_care_of_the_thing_close.setOnClickListener { AlertClose() }

        alert_care_of_the_thing.visibility = View.VISIBLE
        alert_dialog.visibility = View.VISIBLE
        alert_dialog.animation = AnimationUtils.loadAnimation(this, R.anim.alert_open)
        alert_care_of_the_thing.animation = AnimationUtils.loadAnimation(this, R.anim.alert_fon_open)

        if (alertType == "alert_size_cart"){
            alert_recomendation_view.findViewById<TextView>(R.id.alert_size_chart_text)
                .text = AlertManager.getSizeChartTextForItemByCategory(
                    CatalogItems.items.get(itemSelected).categoryId
                )
        }else if(alertType == "care_of_the_thing"){
            val recomindationText = AlertManager.getRecomendation()

            alert_recomendation_view.findViewById<TextView>(R.id.alert_care_of_the_thing_title)
                .text = recomindationText.get(0)
            alert_recomendation_view.findViewById<TextView>(R.id.alert_care_of_the_thing_data)
                .text = recomindationText.get(1)
        }
    }

    private fun AlertClose(){
        alert_open = false
        alert_care_of_the_thing.animation = AnimationUtils.loadAnimation(this, R.anim.alert_fon_close)
        alert_dialog.animation = AnimationUtils.loadAnimation(this, R.anim.alert_close)
        alert_care_of_the_thing.visibility = View.GONE
        alert_dialog.visibility = View.GONE
    }

    private fun loadRecommendations(){
        list_recomendation = RecomindationsAlgoritm.recommend(itemSelected)
        var adapter = RecommendationsAdapter(this, list_recomendation, item.theme)
        recommendations_view.adapter = adapter

        var lm = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recommendations_points_count.layoutManager = lm
        recommendations_points_count.adapter =
            PointsRecomindationAdapter(this, list_recomendation.size, item.theme)

        recommendations_view.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageSelected(position: Int) {
                if (item.theme == ItemFromShop.Theme.Dark) {
                    recommendations_points_count.get(recommendation_selected).findViewById<RelativeLayout>(R.id.point)
                        .setBackgroundResource(R.drawable.point_basic)
                } else {
                    recommendations_points_count.get(recommendation_selected).findViewById<RelativeLayout>(R.id.point)
                        .setBackgroundResource(R.drawable.point_basic_dark)
                }

                recommendation_selected = position

                if (item.theme == ItemFromShop.Theme.Dark) {
                    recommendations_points_count.get(recommendation_selected).findViewById<RelativeLayout>(R.id.point)
                        .setBackgroundResource(R.drawable.point_fill)
                } else {
                    recommendations_points_count.get(recommendation_selected).findViewById<RelativeLayout>(R.id.point)
                        .setBackgroundResource(R.drawable.point_fill_dark)
                }
            }
        })
    }

    override fun SelectSize(sizeId: Int) {
        itemSizeId = sizeId

        if(fromePage == "basket"){
            Basket.items.get(itemInBasketId).sizeId = sizeId
            BasketStaticData.basketItemChangedObserver.basketItemChangedObserver()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setTheme(theme: ItemFromShop.Theme){
        if(theme == ItemFromShop.Theme.Dark){return}
        findViewById<RelativeLayout>(R.id.item_description_background).setBackgroundColor(getColor(R.color.white))
        item_description_name.setTextColor(getColor(R.color.black))
        item_description_description.setTextColor(getColor(R.color.black))
        findViewById<RelativeLayout>(R.id.item_description_gradient).visibility = View.GONE
        // Cost
        item_description_cost.setTextColor(getColor(R.color.white))
        item_description_cost.setBackgroundResource(R.drawable.item_design_dark)
        // Count
        item_description_count.setTextColor(getColor(R.color.white))
        item_description_count.setBackgroundColor(getColor(R.color.black))
        item_description_sub.setTextColor(getColor(R.color.black))
        item_description_sub.setBackgroundResource(R.drawable.btn_desibn_left_radius_dark)
        item_description_add.setTextColor(getColor(R.color.black))
        item_description_add.setBackgroundResource(R.drawable.btn_desibn_right_radius_dark)
        // Add to basket
        findViewById<RelativeLayout>(R.id.item_description_add_to_basket_fon).setBackgroundResource(R.drawable.item_design_dark)
        item_description_add_to_basket.setTextColor(getColor(R.color.white))
        findViewById<ProgressBar>(R.id.item_description_preloader_item_dark).visibility = View.GONE
        findViewById<ProgressBar>(R.id.item_description_preloader_item_light).visibility = View.VISIBLE
        // Recomendations
        findViewById<LinearLayout>(R.id.item_description_recomendation_gradient).visibility = View.GONE
        findViewById<TextView>(R.id.item_description_recomendation_text_1).setTextColor(getColor(R.color.black))
        findViewById<TextView>(R.id.item_description_recomendation_text_2).setTextColor(getColor(R.color.white))
        findViewById<TextView>(R.id.item_description_recomendation_text_2).setBackgroundColor(getColor(R.color.black))
    }
}