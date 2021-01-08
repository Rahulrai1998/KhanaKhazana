package com.rahul.khanakhazana.adapter

import android.content.Context
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rahul.khanakhazana.R
import com.rahul.khanakhazana.model.Restaurant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_home_single_row.view.*

//primary constructors are declared below also
class HomeRecyclerAdapter(val context: Context , val itemList:ArrayList<Restaurant>):RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    //onCreateViewHolder method is responsible for creating the initial required viewHolders that can fit on the screen on one go....
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_single_row,parent,false)/*it will inflate the layout
        we created earlier as the list item of Recycler View */

        return HomeViewHolder(view)//Here the view is passed to ViewHolder which will hold it

    }

    // getItemCount method stores the total number of the item of Lists
    override fun getItemCount(): Int {
        return itemList.size //it gives the size of the arrayList of the Item List
    }

    //onBindViewHolder method is responsible for recycling and reusing of View Holders and also it ensures that the Data goes to the correct position
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        //data under menu lists are entered in this method
        // in this function the default names of views declared in item layout(recycler_home_single_row.xml) of Recycler view are overriden.....
       /* val text = itemList[position]// 0 index element stored in text variable ; position is used for index 0
        holder.textView.text = text //stores in the Text Views of the List*/
        val restaurant = itemList[position]
        holder.txtRestaurantName.text = restaurant.restaurantName
        holder.txtRestaurantRate.text = restaurant.restaurantCost
        holder.txtRestaurantRating.text = restaurant.restaurantRating
       // holder.imgRestaurantImage.setImageResource(restaurant.restaurantImage)
        Picasso.get().load(restaurant.restaurantImage).into(holder.imgRestaurantImage)// it will show the restaurant image fetched by server

        holder.linearLayContent.setOnClickListener{
            Toast.makeText(context , "You have clicked on ${holder.txtRestaurantName.text}" , Toast.LENGTH_SHORT).show()
        }

        //above , the work of default view overriding is done with the Restaurant arguements..

    }

    //in View Holder all the Recycler View Lists elements' views are initialized
    class HomeViewHolder(view: View):RecyclerView.ViewHolder(view){//View Holder of Recycler View is a class that is declared in Adapter class

        val txtRestaurantName:TextView = view.findViewById(R.id.txtRestaurantName)
        val txtRestaurantRate:TextView = view.findViewById(R.id.txtRestaurantRate)
        val txtRestaurantRating:TextView = view.findViewById(R.id.txtRestaurantRating)
        val imgRestaurantImage:ImageView = view.findViewById(R.id.imgRestaurantImage)
        val linearLayContent:LinearLayout = view.findViewById(R.id.linearLayContent)
    }
}