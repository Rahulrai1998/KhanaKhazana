package com.rahul.khanakhazana.fragment

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rahul.khanakhazana.R
import com.rahul.khanakhazana.adapter.HomeRecyclerAdapter
import com.rahul.khanakhazana.model.Restaurant
import com.rahul.khanakhazana.util.ConnectionManager


class HomeFragment : Fragment() {

    lateinit var recyclerHome:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var btnConnectivityCheck:Button

    val restaurantInfoList = arrayListOf<Restaurant>()

   /* val restaurantsList = arrayListOf(
        "Pind Tadka" ,
        "Grbar Burgers",
        "Baco Tell",
          "Heera Mahal",
           "Smokin Chik" ,
           "Swirly Shack" ,
            "Dominoe's bread" ,
              "Everything but Food" ,
           "LFC","Central Terk" , "Mitti ke Sandwitches" , "Pizza Put" , "Burger Jack" , "Rotten Tomatoes" , "NcDonald's"
            ,"Askin' Poppins" , "Baasa Menu")*/

    lateinit var recyclerAdapter: HomeRecyclerAdapter // Adapter variable

    // The data type 'Restaurant' is used below came from the Data Class Restaurant....
    //the Datatype of following array is Restaurant , because each elements of the array is an object of Restaurant Data Class...
    /*val restaurantInfoList = arrayListOf<Restaurant>()(
        Restaurant("Pind Tadka","Rs 253/person","4.5",R.drawable.lord_of_rings),
        Restaurant("Gurbur Burger","Rs 200/person","4.4",R.drawable.lord_of_rings),
        Restaurant("Baco Tell","Rs 263/person","4.0",R.drawable.lord_of_rings),
        Restaurant("Heera Mahal","Rs 353/person","4.7",R.drawable.lord_of_rings),
        Restaurant("Smokin Chik","Rs 453/person","4.6",R.drawable.lord_of_rings),
        Restaurant("Swirly Shack","Rs 753/person","4.3",R.drawable.lord_of_rings),
        Restaurant("Dominoe's Bread","Rs 953/person","4.8",R.drawable.lord_of_rings),
        Restaurant("Everyrhing but Food","Rs 153/person","4.6",R.drawable.lord_of_rings),
        Restaurant("LFC","Rs 203/person","4.0",R.drawable.lord_of_rings),
        Restaurant("Central Tadka","Rs 200/person","4.9",R.drawable.lord_of_rings),
        Restaurant("Mitti Ke Sandwitches","Rs 653/person","4.8",R.drawable.lord_of_rings),
        Restaurant("Pizza But","Rs 299/person","4.7",R.drawable.lord_of_rings),
        Restaurant("Burger Jack","Rs 266/person","3.1",R.drawable.lord_of_rings),
        Restaurant("Rotten Tomatoes","Rs 753/person","2.1",R.drawable.lord_of_rings),
        Restaurant("Lr.Donalds","Rs 245/person","3.1",R.drawable.lord_of_rings),
        Restaurant("Askins","Rs 200/person","4.0",R.drawable.lord_of_rings),
        Restaurant("Poppins","Rs 333/person","4.6",R.drawable.lord_of_rings)
    )*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*in the following parameters passed under the inflator method ,
         first parameter inflates/attaches the corresponding layout to fragment
         second parameter provides a container/viewgroup where the layout gets placed , it's a view group provided by the activity in which the fragment lies
         third parameter prevents the fragment to attach permanently to the activity....
         */

        val view  = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerHome = view.findViewById(R.id.recyclerHome)//in fragments such way is used to initialize the variables due to absence of onCreate View here

        btnConnectivityCheck = view.findViewById(R.id.btnConnectivityCheck)
        btnConnectivityCheck.setOnClickListener {
            if(ConnectionManager().checkConnectivity(activity as Context)){

                val dialog = AlertDialog.Builder(activity as Context)//Will create a dialog box
                dialog.setTitle("Success")
                dialog.setMessage("Internet Connection Found")
                dialog.setPositiveButton("Ok"){text,listener->

                }
                dialog.setNegativeButton("Cancel"){text,listener->

                }
                dialog.create()
                dialog.show()


            }
            else{
                val dialog = AlertDialog.Builder(activity as Context)//Will create a dialog box
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection not Found")
                dialog.setPositiveButton("Ok"){text,listener->

                }
                dialog.setNegativeButton("Cancel"){text,listener->

                }
                dialog.create()
                dialog.show()


            }
        }

        layoutManager =  LinearLayoutManager(activity)//context would be the Activity of Fragment



        //code for sending the request to server is written below
        //we are using Volley Library for request mechanism

        val queue = Volley.newRequestQueue(activity as Context) //it is the queue of requests
        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"  //from this url the data will be fetched
        //for using http we would have to add some piece of code in resources directory , which will allow the domain to send the request over http

        // object below is an anonymous object , created with purpose of using getHeaders method to send headers to the API
        /*headers tell us the type(JSON , plane text , Strings etc) of content which is being sent
        to and received from the API also headers help to send unique TOKEN eith the request*/
        // TOKEN ensures that each request send to the API is unique

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET , url ,null, Response.Listener{


            Log.d("response",it.toString())
            Log.e("res","success"+it)
            Log.e("res","error"+it)

            val success = it.getBoolean("success")//it will give the value of key "success"
            if(success){
                val data = it.getJSONArray("data")//json data(Array) is stored in this variable and now it will be iterated below
                for(i in 0 until data.length()){

                    val restaurantJsonObject = data.getJSONObject(i)/*it will store the Json objects extracted from json array
                     and below it will be parsed into restaurant object*/

                    //below is the code for parsing the JSON object to the Restaurant object
                    val restaurantObject = Restaurant(
                        restaurantJsonObject.getString("name"),
                        restaurantJsonObject.getString("rating"),
                        restaurantJsonObject.getString("cost_for_one"),
                        restaurantJsonObject.getString("image_url")
                    )

                    restaurantInfoList.add(restaurantObject)// it will add each Restaurant object as the elements of array list and then this arraylist will be passed to the adapter
                    recyclerAdapter = HomeRecyclerAdapter(activity as Context, restaurantInfoList) // it will pass the restaurant list to the Adapter
                    // as is used for Typecasting

                    //following is done for setup the adapter and the layout manager with the Recycler View

                    recyclerHome.layoutManager = layoutManager
                    recyclerHome.adapter = recyclerAdapter


                    //methods below are used to add a seperating lines between two rows of menu items
                    recyclerHome.addItemDecoration(
                        DividerItemDecoration(
                            recyclerHome.context,(layoutManager as LinearLayoutManager).orientation
                        )
                    )
                }
            }else {
                Toast.makeText(activity as Context, "Some error occurred", Toast.LENGTH_SHORT)
                    .show()
            }
        },Response.ErrorListener {

            println("Error is $it")

        }){
            //below is the code of sending headers to the API
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "9f54d54c4d2055"
                return headers
            }
        }//This JsonObject Request made by making an object of JsonObjectRequest class

        queue.add(jsonObjectRequest)//here we are adding the json request to the request queue

        return view
    }// Unlike in Activities we use Layout inflator instead of setContentView method in Fragments to inflate/set a layout to the fragment kotlin file

}
