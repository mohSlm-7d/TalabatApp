package com.demo.foodorderanddeliveryappkotlin

import UserContract
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.foodorderanddeliveryappkotlin.adapter.FragmentAdapter
import com.demo.foodorderanddeliveryappkotlin.adapter.RestaurantListAdapter
import com.demo.foodorderanddeliveryappkotlin.models.RestaurentModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), RestaurantListAdapter.RestaurantListClickListener {
    private lateinit var fragmentContainer: RecyclerView
    private lateinit var fragmentAdapter: FragmentAdapter

    companion object{

    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val contentValues = ContentValues().apply {
            put(UserContract.UserEntry.TABLE_NAME, "users")
            put(UserContract.UserEntry.COLUMN_FIRST_NAME, "John")
            put(UserContract.UserEntry.COLUMN_LAST_NAME, "Doe")
            put(UserContract.UserEntry.COLUMN_EMAIL, "johndoe@example.com")
            put(UserContract.UserEntry.COLUMN_PASSWORD, "password123")
            put(UserContract.UserEntry.COLUMN_PHONE_NO, "1234567890")
        }
        /*val dbHelper = UserDbHelper(this)
        dbHelper.onCreate(dbHelper.writableDatabase)*/


        //val uri = contentResolver.insert(UserContract.CONTENT_URI, contentValues)








        fragmentContainer = findViewById(R.id.recyclerViewRestaurant)
        fragmentContainer?.layoutManager = LinearLayoutManager(this)
        fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentContainer?.adapter = fragmentAdapter

        // Add your logic to show the initial fragment (logo fragment) here
        // fragmentAdapter.addFragment(LogoFragment())

        // Show the sign-in fragment initially
        val signInFragment = SignInFragment()
        fragmentAdapter.addFragment(signInFragment)


        // Implement any necessary methods or callbacks for handling navigation between fragments
        // ...
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle("Restaurant List")

        /*val restaurantList = getRestaurantData()
        initRecyclerView(restaurantList)*/
        // If the user is authenticated then they get to access the app.

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.user_account_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.updateUserInfo ->{
                var dialog = UpdateDialogFragment()
                dialog.show(supportFragmentManager, "Update Dialog")
            }
            R.id.deleteUser -> {
                var dialog = DeleteDialogFragment()
                dialog.show(supportFragmentManager, "Delete Dialog")
            }
        }
        return true
    }
    fun initRecyclerView(restaurantList: List<RestaurentModel?>?) {
        val recyclerViewRestaurant = findViewById<RecyclerView>(R.id.recyclerViewRestaurant)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
        val adapter = RestaurantListAdapter(restaurantList, this)
        recyclerViewRestaurant.adapter =adapter
    }

    fun getRestaurantData(): List<RestaurentModel?>? {
        val inputStream: InputStream = resources.openRawResource(R.raw.restaurent)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n : Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)

            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val restaurantModel = gson.fromJson<Array<RestaurentModel>>(jsonStr, Array<RestaurentModel>::class.java).toList()

        return restaurantModel
    }

    override fun onItemClick(restaurantModel: RestaurentModel) {
       val intent = Intent(this@MainActivity, RestaurantMenuActivity::class.java)
        intent.putExtra("RestaurantModel", restaurantModel)
        startActivity(intent)
    }
}

private operator fun Unit.invoke(signUpFragment: SignUpFragment) {

}
