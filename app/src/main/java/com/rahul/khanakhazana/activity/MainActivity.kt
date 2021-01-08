package com.rahul.khanakhazana.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.rahul.khanakhazana.R
import com.rahul.khanakhazana.fragment.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem:MenuItem?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()

        openHome() // to make the Home fragment as the first fragment when the activity opens

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity ,
            drawerLayout ,
            R.string.open_drawer,
            R.string.close_drawer
        ) /* this an object of ActionBarDrawerToggle function
            to enable action bar drawer toggle(Hamburger Icon) on the action bar by adding drawerlayout(Navigation Drawer) to it*/
        //Here toggle is placed at the place of home button in the action bar , hence to make it functional we need to make the home button also functional

        drawerLayout.addDrawerListener(actionBarDrawerToggle)//this method will set a clicklistener to the hamburger icon to make drawer layout functional
        actionBarDrawerToggle.syncState()/*it will synchronise(simultaneous) the state of navigation drawer to the state of the hamburger icon(toggle)
        or in other words it make the toggel a back arrow icon and vice versa*/

        //by following method we add clicklisteners to the drawer items , with setNavigationItemSelectedListeners method instead of using setOnClickListeners..
        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem!=null)//it unchecks the previously selected menu item
            {
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true //'it' holds the currently selected menu item and 'previousMenuItem' Holds the previously checked menu item
            it.isChecked = true
            previousMenuItem = it

            // below 'it' will give us the currently selected item
            when(it.itemId){
                R.id.home ->{
                    //task of opening a fragment is taken out by fragmentmanager/supportFragmentManager and the action of opening a fragment is known as transaction....
                   /* supportFragmentManager.beginTransaction().
                    replace(R.id.frameLayout , HomeFragment())/*.addToBackStack("Home")*/.commit()//commit will apply the transaction and replace will replace the frame to fragment passed...
                    //addToBackStart method is used to get back to the previous screen/fragment
                    supportActionBar?.title = "All Restaurants" // it adds the title of the fragment*/
                    openHome()
                    drawerLayout.closeDrawers()// it will close the drawer after transaction commited or after opening the fragment....
                }
                R.id.myprofile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        MyProfileFragment()
                    )/*.addToBackStack("My Profile")*/.commit()
                    supportActionBar?.title = "My profile"
                    drawerLayout.closeDrawers()
                }
                R.id.favourite ->{
                   supportFragmentManager.beginTransaction().replace(
                       R.id.frameLayout,
                       FavouriteFragment()
                   )/*.addToBackStack("Favourite Restaurants")*/.commit()
                    supportActionBar?.title = "Favourite Restaurants"
                    drawerLayout.closeDrawers()
                }
                R.id.orderHistory ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        OrderHistoryFragment()
                    )/*.addToBackStack("Order History")*/.commit()
                   supportActionBar?.title = "My Previous Orders"
                    drawerLayout.closeDrawers()
                }
                R.id.faqs ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FaqsFragment()
                    )/*.addToBackStack("FAQs")*/.commit()
                    supportActionBar?.title  = "Frequently Asked Questions"
                    drawerLayout.closeDrawers()
                }
                R.id.logout ->{
                    //below is the code of Dialog Box
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Confirmation")
                    dialog.setMessage("Are you sure you want exit?")

                    dialog.setPositiveButton("YES"){text , listener ->
                        val intent = Intent(this@MainActivity , LoginActivity::class.java)
                        startActivity(intent)

                    }
                    dialog.setNegativeButton("NO"){text , listener ->
                        openHome()
                    }
                    dialog.create()
                    dialog.show()
                    drawerLayout.closeDrawers()

                }

            }
            return@setNavigationItemSelectedListener true
        }

    }
    //below is the function declared to make toolbar as an actionbar
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "All Restaurants"

        //for adding hamburger icon on the actionbar following methods will be used
        supportActionBar?.setHomeButtonEnabled(true) //this method will activate/enable the prdefined home button at actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // this method will make home button displayed at action bar
    }

    //the method of adding clicklisteners in the actionbar(homebutton) is different , as declared below....
    //the code written below will make the home button functional(by adding clicklisteners) first , in order to experience the toggle functionalities written above
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId //extracting the item id (toggle id) and storing in id variable
        //below , id.home is used to get the id of hamburger icon since , it is placed at the home button of the action bar
        if(id == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START) //it will open the drawer from left
        }

        return super.onOptionsItemSelected(item)
    }// home button is a menuitem of action bar and in this case that menuitem is the hamburger icon(toggle)
    //here toggle is passed as the parameter in item

    fun openHome(){
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment).commit()

        supportActionBar?.title = "All Restaurants"
        navigationView.setCheckedItem(R.id.home) //it will make the home menu item already checked on activity opening
    }

    override fun onBackPressed()//it is used to handle the functionalities of the backpress
    {
        val frag  = supportFragmentManager.findFragmentById(R.id.frameLayout) //it opens the current fragment in the frame

        when(frag){
            is HomeFragment -> super.onBackPressed()//it will terminate the current activity and move to previous activity
            else -> openHome()// on back press home fragment will open
        }

    }
}
