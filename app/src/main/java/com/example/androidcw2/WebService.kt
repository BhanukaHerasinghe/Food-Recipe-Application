package com.example.androidcw2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcw2.Connection.checkConnectionAvailability
import com.example.androidcw2.RecipeforUser.showPotionsViaTextView
import com.example.androidcw2.Verification.validateInput
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class WebService : AppCompatActivity() {


    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service)
        // retrieve the search field EditText instance and search button Button instance from the layout
        val searchField = findViewById<EditText>(R.id.editboxfor_searching)
        val searchButton = findViewById<Button>(R.id.search_meals)

        var textmealview = findViewById<TextView>(R.id.textviewWeb)


        // set the click listener for the search button
        searchButton.setOnClickListener {

            if (checkConnectionAvailability(this)) {  // check if there is internet connectivity

                val getUserInput = searchField.text.toString()

                if (validateInput(getUserInput, this)) {  // validate user input

                    runBlocking {  // switch to the main thread to update the UI

                        launch {

                            //function sets the context of the coroutine to IO, which is a thread pool for I/O-bound operations.
                            withContext(Dispatchers.IO){
                                val mealList: List<CategoryOfFood> = searchName(getUserInput)  // search for meals on the webed on the ingredient.

                                val stringBuilderfoodRecipe:StringBuilder=
                                    showPotionsViaTextView(mealList)

                                // function is called to update the UI with the recipe details by setting the text of the textmealview TextView view. If the StringBuilder is empty, a "can't find" message is displayed instead.

                            runOnUiThread{
                                if (stringBuilderfoodRecipe.isNotEmpty())
                                {
                                textmealview.text = stringBuilderfoodRecipe.toString()
                            }
                                else
                                {
                                textmealview.text = "cant find"
                            }
                            }

                            }

                        }
                    }
                }
            }
        }
    }



    // function to search for meals on the web with a given meal name
    private fun searchName(mealName: String): List<CategoryOfFood> {
        // create a mutable list object to hold Meal instances.
        var detailsOfRecipe = mutableListOf<CategoryOfFood>()  // create a mutable list to hold Meal instances

        try {
            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {

                        // create a URL with the given meal name and send a GET request to the API
                        val url =
                            URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$mealName")
                        // read what api returned
                        val giveConnection = url.openConnection() as HttpURLConnection

                        giveConnection.requestMethod = "GET"

                        // read the response from the API and convert it to a JSON object
                        val stringBuilderResponse = StringBuilder()
                        Scanner(giveConnection.inputStream).use { scanner ->
                            while (scanner.hasNextLine()) {
                                stringBuilderResponse.append(scanner.nextLine())
                            }
                        }

                        val jsonObject = JSONObject(stringBuilderResponse.toString())
                        // retrieve the meals array from the JSON object and loop through it to retrieve each meal's details
                        val jsonArrays = jsonObject.getJSONArray("meals")
                        for (i in 0 until jsonArrays.length()) {
                            val jsonMeals = jsonArrays.getJSONObject(i)

                            detailsOfRecipe = GetDatafromJson().jsonData(jsonMeals, detailsOfRecipe)
                        }

                    }
                }
            }


        } catch (e: Exception) {

            runOnUiThread {
                InformationforUser.errorShowing(
                    "Error",
                    "An error occurred while retrieving the data",
                    e.stackTraceToString(),
                    this
                )
            }
        }
        return detailsOfRecipe


    }

}
