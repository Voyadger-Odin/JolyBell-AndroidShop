package com.voyager.jolybell

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voyager.jolybell.adapters.CategoryAdapter
import com.voyager.jolybell.adapters.CatalogAdapter
import com.voyager.jolybell.model.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [catalogPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class calatogPageFragment : Fragment(), CategoryChanged {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var catalogRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    lateinit var catalogAdapter: CatalogAdapter
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)

        var lm: RecyclerView.LayoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        categoryRecyclerView.layoutManager = lm
        categoryAdapter = CategoryAdapter(view.context)
        categoryAdapter.CategoryChangedListeners.add(this)
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.setOnClickListener {

        }


        // Items
        catalogRecyclerView = view.findViewById(R.id.catalogRecyclerView)
        setItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calatog_page, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment calatogPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            calatogPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setItemsAdapter(){
        CatalogItems.itemsShow = arrayListOf()
        CatalogItems.items.forEachIndexed { index, itemFromShop ->
            if(itemFromShop.categoryId == CategoryItems.categorySelected){
                CatalogItems.itemsShow.add(itemFromShop)
            }
        }
        var lm = LinearLayoutManager(context)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            lm = GridLayoutManager(context, 2)
        }

        catalogRecyclerView.layoutManager = lm
        catalogAdapter = CatalogAdapter(context)
        catalogRecyclerView.adapter = catalogAdapter

        var animation = AnimationUtils.loadAnimation(context, R.anim.catalog_item_vis)
        catalogRecyclerView.animation = animation
    }

    override fun categoryChanged() {
        setItemsAdapter()
    }
}