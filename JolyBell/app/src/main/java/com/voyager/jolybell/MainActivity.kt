package com.voyager.jolybell

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voyager.jolybell.algotitms.AlertManager
import com.voyager.jolybell.model.basket.Basket
import com.voyager.jolybell.model.basket.BasketChanged
import com.voyager.jolybell.model.GetCatalogItems


class MainActivity : AppCompatActivity(),
    BasketChanged {

    lateinit var bottom_navigation_view: BottomNavigationView

    companion object{
        var fragment: Fragment = MainPageFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set categorys
        GetCatalogItems.SetCategorys()

        // Get items
        GetCatalogItems.GetCatalogItems()

        // Set start page
        setPage()

        bottom_navigation_view = findViewById(R.id.button_navigation_view)
        bottom_navigation_view.setOnNavigationItemSelectedListener(selectedListener)

        // Add to listeners basket change
        Basket.listeners.add(this)



        // Identify alert languages
        AlertManager.IdentifyLanguages()
    }

    override fun onStart() {
        super.onStart()

        basketChanged()
    }

    fun setPage(){
        val fragmentTransaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment, "")
        fragmentTransaction.commit()
    }

    private val selectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mainPageFragment -> {
                    fragment = MainPageFragment()
                }
                R.id.calatogPageFragment -> {
                    fragment = calatogPageFragment()
                }
                R.id.basketPageFragment -> {
                    fragment = basketPageFragment()
                }
                R.id.profilPageFragment -> {
                    fragment = profilPageFragment()
                }
            }
            setPage()
            return@OnNavigationItemSelectedListener true
        }

    // Change in basket
    override fun basketChanged() {
        bottom_navigation_view.getOrCreateBadge(R.id.basketPageFragment).apply {
            number = Basket.getItemsCount()
            isVisible = number > 0
        }
    }
}

