package com.voyager.jolybell

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import com.voyager.jolybell.model.*
import com.voyager.jolybell.model.basket.Basket
import com.voyager.jolybell.model.basket.BasketItemChangedObserver
import com.voyager.jolybell.model.basket.BasketStaticData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [basketPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class basketPageFragment : Fragment(),
    BasketItemChangedObserver {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var items_in_basket_list: LinearLayout

    lateinit var items_views: ArrayList<View>

    lateinit var items_id: ArrayList<Int>

    internal lateinit var view: View


    // Delever cost
    private var deliver_cost: Float = 1000f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket_page, container, false)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.view = view
        // Усли корзина пустая, выводит соответствующий экран и прекращает работу ф-ции
        viewEmptyBasket(view)
        if(Basket.items.size == 0){return}

        setCostTitles(view)

        // Animation open
        view.findViewById<ScrollView>(R.id.items_in_basket_scroll_view).animation =
            AnimationUtils.loadAnimation(view.context, R.anim.catalog_item_vis)

        items_in_basket_list = view.findViewById(R.id.items_in_basket_list)


        items_views = arrayListOf()
        items_id = arrayListOf()
        Basket.items.forEachIndexed { index, itemInBasket ->
            val viewItem = layoutInflater.inflate(R.layout.item_in_basket, items_in_basket_list, false)
            val item_in_basket_img = viewItem.findViewById<ImageView>(R.id.item_in_basket_img)

            // Item name
            viewItem.findViewById<TextView>(R.id.item_in_basket_name).text =
                CatalogItems.items.get(itemInBasket.itemId).name
            // Item image
            Picasso.with(view.context)
                .load(CatalogItems.items.get(itemInBasket.itemId).img.get(0))
                .into(item_in_basket_img)

            if(index == 0){
                viewItem.findViewById<RelativeLayout>(R.id.item_in_basket_delimiter).visibility =
                    View.GONE
            }

            items_id.add(Basket.items.get(index).unicalId)



            item_in_basket_img.setOnClickListener {
                openDescriptionItem(items_id.get(index), view.context, item_in_basket_img)
            }

            items_views.add(viewItem)



            // Delete
            viewItem.findViewById<TextView>(R.id.item_in_basket_delete).setOnClickListener {
                deleteItem(index, items_id.get(index), view)
            }

            items_in_basket_list.addView(viewItem)
        }

        setDataBasketItems()
    }

    private fun viewEmptyBasket(view: View){
        if(Basket.items.size == 0){
            view.findViewById<ScrollView>(R.id.items_in_basket_scroll_view).visibility = View.GONE
            val empty_basket_place = view.findViewById<RelativeLayout>(R.id.empty_basket_place)
            val viewItem = layoutInflater.inflate(R.layout.empty_basket, empty_basket_place, false)
            empty_basket_place.addView(viewItem)

            // Animation open
            empty_basket_place.animation =
                AnimationUtils.loadAnimation(view.context, R.anim.catalog_item_vis)
        }
    }

    // Put item size and count in form
    private fun setDataBasketItems(){
        Basket.items.forEachIndexed { index, itemInBasket ->
            var viewItem = items_views.get(index)

            //Item count
            viewItem.findViewById<TextView>(R.id.item_in_basket_count).text =
                itemInBasket.countItems.toString()

            // Item cost
            viewItem.findViewById<TextView>(R.id.item_in_basket_cost).text =
                ItemFromShop.costToString(
                    CatalogItems.items.get(itemInBasket.itemId).getCost()
                            * 100
                            * itemInBasket.countItems
                            / 100
                )

            // Item size
            if (CatalogItems.items.get(itemInBasket.itemId).sizes.size > 0){
                viewItem.findViewById<TextView>(R.id.item_in_basket_size).text =
                    CatalogItems.items.get(itemInBasket.itemId).sizes.get(
                        itemInBasket.sizeId
                    )
            }else{
                viewItem.findViewById<LinearLayout>(R.id.item_size_block).visibility = View.GONE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun deleteItem(position: Int, unicalId: Int, view: View){
        val itemIndex = Basket.findItemByUnicalId(unicalId)
        if (itemIndex >= 0){
            // Clean lists
            items_in_basket_list.removeView(items_views.get(position))
            Basket.removeItemInBasket(itemIndex)

            // Hide up delimiter in first item
            if(Basket.items.size > 0){
                items_id.forEachIndexed { index, i ->
                    if(i == Basket.items.get(0).unicalId){
                        items_views.get(index)
                            .findViewById<RelativeLayout>(R.id.item_in_basket_delimiter)
                            .visibility =
                            View.GONE

                        return@forEachIndexed
                    }
                }
            }

            items_views.removeAt(position)

            // Recalculate cost
            setCostTitles(view)
        }

        // Проверка на пустоту корзины
        viewEmptyBasket(view)
    }



    private fun openDescriptionItem(itemId: Int, context: Context, img: ImageView) {
        // Put data
        val intentDescription = Intent(context, ItemDescriptionBy::class.java)
        intentDescription.putExtra("fromePage", "basket")
        intentDescription.putExtra("itemSelected", Basket.items.get(
            Basket.findItemByUnicalId(itemId)).itemId)
        intentDescription.putExtra("itemInBasketId", Basket.findItemByUnicalId(itemId))
        BasketStaticData.basketItemChangedObserver = this
        // Animation
        val options = ActivityOptions.makeSceneTransitionAnimation(
            context as Activity?,
            Pair(img, "itemTransitionImg")
        )
        // Start activity
        context.startActivity(intentDescription, options.toBundle())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment basketPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            basketPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setCostTitles(view: View) {
        view.findViewById<TextView>(R.id.items_in_basket_items_cost).text =
            ItemFromShop.costToString(Basket.getItemsCost())
        view.findViewById<TextView>(R.id.items_in_basket_deliver_cost).text =
            ItemFromShop.costToString(deliver_cost)
        view.findViewById<TextView>(R.id.items_in_basket_cost_result).text =
            ItemFromShop.costToString(Basket.getItemsCost() + deliver_cost)
    }

    override fun basketItemChangedObserver() {
        setDataBasketItems()
        // Recalculate cost
        setCostTitles(view)
    }
}