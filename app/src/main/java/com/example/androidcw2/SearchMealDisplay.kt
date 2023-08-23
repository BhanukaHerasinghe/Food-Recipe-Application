package com.example.androidcw2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import androidx.activity.viewModels


class SearchMealDisplay : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mealPair: ArrayList<ImageMeal>
    private val MealModels:MealModels by viewModels ()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal_display)


        val editTextField = findViewById<EditText>(R.id.editboxforsearching)
        val searchingButton = findViewById<Button>(R.id.searchmeals)
        recyclerView = findViewById(R.id.recycle_id)
        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealDisplay)
        mealPair = MealModels.mealPair?: ArrayList<ImageMeal>()

        recyclerView.adapter = ImageMealAdp(mealPair)


        // Setting onClick listener to search button
        searchingButton.setOnClickListener{
            // get the user input
            val getUserInput = editTextField.text.toString()
            // Validate user Input, empty user inputs will fail the validation
            if (Verification.validateInput(getUserInput, this)){

                //  creates a database instance and a DAO instance using Room.
                runBlocking {

                    launch {

                        withContext(Dispatchers.IO) {
                            try {
                                val DataBaseID = intent.getStringExtra("db_id")
                                val DataBase =Room.databaseBuilder(this@SearchMealDisplay,ConnectorMeal::class.java,DataBaseID).build()




                                val potionDao =DataBase.categoryOfFoodDao()

                                val potions=potionDao.nameForPotionOrIngredient(getUserInput)



                                runOnUiThread{
                                    GlobalScope.launch (Dispatchers.Main){
                                        val listOfPotions: List<CategoryOfFood> = potions


                                        // set layout manager to recyclerView.
                                        recyclerView.layoutManager =
                                            LinearLayoutManager(this@SearchMealDisplay)



                                        //  The withContext function is used to run the showRecipesInRecyclerView

                                        //  pairs variable, which is then used to create the adapter for the RecyclerView.
                                        mealPair = withContext(Dispatchers.Default) {
                                            RecipeforUser.showRecipesInRecyclerView(listOfPotions, mealPair)
                                        }


                                        // creating adapter for recyclerView.
                                        val adapter = ImageMealAdp(mealPair)
                                        // setting adapter for recyclerView.
                                        recyclerView.adapter = adapter
                                        adapter.notifyDataSetChanged()
                                    }
                                }

                            } catch (e:Exception) {
                                InformationforUser.errorShowing(
                                    "Error",
                                    "Error occurred while retrieving data from the DataBase ",
                                    e.message.toString(),
                                    this@SearchMealDisplay
                                )

                            }
                        }
                    }
                }

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        MealModels.mealPair=mealPair
    }

}