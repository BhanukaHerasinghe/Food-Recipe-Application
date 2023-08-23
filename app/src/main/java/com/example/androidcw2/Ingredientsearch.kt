package com.example.androidcw2

import android.annotation.SuppressLint
import android.hardware.biometrics.BiometricManager.Strings
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

var mealnumkey = 1

class Ingredientsearch : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientsearch)

        var ingredientsearchingtext = findViewById<EditText>(R.id.editbox)
        var retrievebutton = findViewById<Button>(R.id.retrieve_meal)
        var savedatabasebutton = findViewById<Button>(R.id.save_meal)
        var textmealview = findViewById<TextView>(R.id.textview)


        retrievebutton.setOnClickListener{
            val searchingingredient =ingredientsearchingtext.text.toString()
            runBlocking {
                launch {

                    //function sets the context of the coroutine to IO, which is a thread pool for I/O-bound operations.
                    withContext(Dispatchers.IO){
                        val fooditem : List<CategoryOfFood> =
                            searchingbyRecipebyIngredient(searchingingredient)  // parameter to search for meals based on the ingredient.

                        val stringBuilderfoodRecipe:StringBuilder=
                            showingtheRecipe(fooditem)

                        // function is called to update the UI with the recipe details by setting the text of the textmealview TextView view. If the StringBuilder is empty, a "can't find" message is displayed instead.

                        runOnUiThread{
                            if (stringBuilderfoodRecipe.isNotEmpty()){
                                textmealview.text = stringBuilderfoodRecipe.toString()
                            } else {
                                textmealview.text = "cant find"
                            }
                        }

                    }



                }

            }
        }

        savedatabasebutton.setOnClickListener{

            val searchingingredient =ingredientsearchingtext.text.toString()  //  parameter to search for meals based on the ingredient.

            runBlocking {
                launch {

                 withContext(Dispatchers.IO) {
                     val fooditem:List<CategoryOfFood> =
                         searchingbyRecipebyIngredient(searchingingredient)
                     mealsavingtodatabse(fooditem)
                 }

                }
            }
        }

    }
    // This function takes in a string containing a list of ingredients to search for in the API
    private fun searchingbyRecipebyIngredient(recipeingredients : String) : List<CategoryOfFood> {
        val gettingurl =
            URL("https://www.themealdb.com/api/json/v1/$mealnumkey/filter.php?i=$recipeingredients")
        val jsonStringOutSummery = gettingurl.readText()  // It retrieves a JSON string from the API that contains a list of meals that include the searched ingredients


        val jsonconvertor = JSONObject( jsonStringOutSummery)

        val fooditemSummery =jsonconvertor.getJSONArray("meals")

        val recipeDetails = mutableListOf<CategoryOfFood>()

        for (i in 0  until  fooditemSummery.length()) {

            val gettingDetailsofMeal = fooditemSummery.getJSONObject(i)

            val idMeal =gettingDetailsofMeal.getInt("idMeal") // Get the ID of the current meal

            val url =
                URL ("https://www.themealdb.com/api/json/v1/$mealnumkey/lookup.php?i=$idMeal")
            // Construct the URL for the API endpoint that provides detailed information about the meal using the ID

            val jsontext = url.readText()

            val jsonobject = JSONObject(jsontext ) // Parse the JSON string into a JSONObject

            val meals = jsonobject.getJSONArray("meals")

            for (i in 0 until  meals.length()) {

                val fditem =meals.getJSONObject(i)

                val strMeal =fditem.getString("strMeal")
                val strDrinkAlternate =fditem.getString("strDrinkAlternate")
                val strCategory = fditem.getString("strCategory")
                val strArea =fditem.getString("strArea")
                val strInstructions=fditem.getString("strInstructions")
                val strMealThumb =fditem.getString("strMealThumb")
                val strTags = fditem.getString("strTags")
                val strYoutube=fditem.getString("strYoutube")
                val strIngredient1=fditem.getString("strIngredient1")
                val strIngredient2 =fditem.getString("strIngredient2")
                val strIngredient3=fditem.getString("strIngredient3")
                val strIngredient4=fditem.getString("strIngredient4")
                val strIngredient5=fditem.getString("strIngredient5")
                val strIngredient6=fditem.getString("strIngredient6")
                val strIngredient7=fditem.getString("strIngredient6")
                val strIngredient8=fditem.getString("strIngredient8")
                val strIngredient9=fditem.getString("strIngredient9")
                val strIngredient10=fditem.getString("strIngredient10")
                val strIngredient11=fditem.getString("strIngredient11")
                val strIngredient12=fditem.getString("strIngredient12")
                val strIngredient13=fditem.getString("strIngredient13")
                val strIngredient14=fditem.getString("strIngredient14")
                val strIngredient15=fditem.getString("strIngredient15")
                val strIngredient16=fditem.getString("strIngredient16")
                val strIngredient17=fditem.getString("strIngredient17")
                val strIngredient18=fditem.getString("strIngredient18")
                val strIngredient19=fditem.getString("strIngredient19")
                val strIngredient20=fditem.getString("strIngredient20")
                val strMeasure1=fditem.getString("strMeasure1")
                val strMeasure2=fditem.getString("strMeasure2")
                val strMeasure3=fditem.getString("strMeasure3")
                val strMeasure4=fditem.getString("strMeasure4")
                val strMeasure5=fditem.getString("strMeasure5")
                val strMeasure6=fditem.getString("strMeasure6")
                val strMeasure7=fditem.getString("strMeasure7")
                val strMeasure8=fditem.getString("strMeasure8")
                val strMeasure9=fditem.getString("strMeasure9")
                val strMeasure10=fditem.getString("strMeasure10")
                val strMeasure11=fditem.getString("strMeasure11")
                val strMeasure12=fditem.getString("strMeasure12")
                val strMeasure13=fditem.getString("strMeasure13")
                val strMeasure14=fditem.getString("strMeasure14")
                val strMeasure15=fditem.getString("strMeasure15")
                val strMeasure16=fditem.getString("strMeasure16")
                val strMeasure17=fditem.getString("strMeasure17")
                val strMeasure18=fditem.getString("strMeasure18")
                val strMeasure19=fditem.getString("strMeasure19")
                val strMeasure20=fditem.getString("strMeasure20")
                val strSource=fditem.getString("strSource")
                val strImageSource=fditem.getString("strImageSource")
                val strCreativeCommonsConfirmed=fditem.getString("strCreativeCommonsConfirmed")
                val dateModified=fditem.getString("dateModified")

                recipeDetails.add(
                    CategoryOfFood(

                        idMeal,
                        strMeal,
                        strDrinkAlternate,
                        strCategory,
                        strArea,
                        strInstructions,
                        strMealThumb,
                        strTags,
                        strYoutube,
                        strIngredient1,
                        strIngredient2,
                        strIngredient3,
                        strIngredient4,
                        strIngredient5,
                        strIngredient6,
                        strIngredient7,
                        strIngredient8,
                        strIngredient9,
                        strIngredient10,
                        strIngredient11,
                        strIngredient12,
                        strIngredient13,
                        strIngredient14,
                        strIngredient15,
                        strIngredient16,
                        strIngredient17,
                        strIngredient18,
                        strIngredient19,
                        strIngredient20,
                        strMeasure1,
                        strMeasure2,
                        strMeasure3,
                        strMeasure4,
                        strMeasure5,
                        strMeasure6,
                        strMeasure7,
                        strMeasure8,
                        strMeasure9,
                        strMeasure10,
                        strMeasure11,
                        strMeasure12,
                        strMeasure13,
                        strMeasure14,
                        strMeasure15,
                        strMeasure16,
                        strMeasure17,
                        strMeasure18,
                        strMeasure19,
                        strMeasure20,
                        strSource,
                        strImageSource,
                        strCreativeCommonsConfirmed,
                        dateModified

                    )
                )

            }

        }
        return recipeDetails
    }

fun showingtheRecipe(fooditem:List<CategoryOfFood>) : StringBuilder {

    val recipeDetails =StringBuilder()
    for (item in fooditem ){

        recipeDetails.append("Meal :- ${item.strMeal}\n")
        recipeDetails.append("DrinkAlternate :- ${item.strDrinkAlternate}\n")
        recipeDetails.append("Category :- ${item.strCategory}")
        recipeDetails.append("Area :- ${item.strArea}\n")
        recipeDetails.append("Instructions :- ${item.strInstructions}\n")
        recipeDetails.append("MealThumb :- ${item.strMealThumb}\n")
        recipeDetails.append("Tags :- ${item.strTags}\n")
        recipeDetails.append("Youtube :- ${item.strYoutube}\n")
        if (blankValidation(item.strIngredient1)) recipeDetails.append("Ingredient1 :- ${item.strIngredient1}\n")
        if (blankValidation(item.strIngredient2)) recipeDetails.append("Ingredient2 :- ${item.strIngredient2}\n")
        if (blankValidation(item.strIngredient3)) recipeDetails.append("Ingredient3 :- ${item.strIngredient3}\n")
        if (blankValidation(item.strIngredient4)) recipeDetails.append("Ingredient4 :- ${item.strIngredient4}\n")
        if (blankValidation(item.strIngredient5)) recipeDetails.append("Ingredient5 :- ${item.strIngredient5}\n")
        if (blankValidation(item.strIngredient6)) recipeDetails.append("Ingredient6 :- ${item.strIngredient6}\n")
        if (blankValidation(item.strIngredient7)) recipeDetails.append("Ingredient7 :- ${item.strIngredient7}\n")
        if (blankValidation(item.strIngredient8)) recipeDetails.append("Ingredient8 :- ${item.strIngredient8}\n")
        if (blankValidation(item.strIngredient9)) recipeDetails.append("Ingredient9 :- ${item.strIngredient9}\n")
        if (blankValidation(item.strIngredient10)) recipeDetails.append("Ingredient10 :- ${item.strIngredient10}\n")
        if (blankValidation(item.strIngredient11)) recipeDetails.append("Ingredient11 :- ${item.strIngredient11}\n")
        if (blankValidation(item.strIngredient12)) recipeDetails.append("Ingredient12 :- ${item.strIngredient12}\n")
        if (blankValidation(item.strIngredient13)) recipeDetails.append("Ingredient13 :- ${item.strIngredient13}\n")
        if (blankValidation(item.strIngredient14)) recipeDetails.append("Ingredient14 :- ${item.strIngredient14}\n")
        if (blankValidation(item.strIngredient15)) recipeDetails.append("Ingredient15 :- ${item.strIngredient15}\n")
        if (blankValidation(item.strIngredient16)) recipeDetails.append("Ingredient16 :- ${item.strIngredient16}\n")
        if (blankValidation(item.strIngredient17)) recipeDetails.append("Ingredient17 :- ${item.strIngredient17}\n")
        if (blankValidation(item.strIngredient18)) recipeDetails.append("Ingredient18 :- ${item.strIngredient18}\n")
        if (blankValidation(item.strIngredient19)) recipeDetails.append("Ingredient19 :- ${item.strIngredient19}\n")
        if (blankValidation(item.strIngredient20)) recipeDetails.append("Ingredient20:- ${item.strIngredient20}\n")
        if (blankValidation(item.strMeasure1)) recipeDetails.append("Measure1 :- ${item.strMeasure1}\n")
        if (blankValidation(item.strMeasure2)) recipeDetails.append("Measure2:- ${item.strMeasure2}\n")
        if (blankValidation(item.strMeasure3)) recipeDetails.append("Measure3:- ${item.strMeasure3}\n")
        if (blankValidation(item.strMeasure4)) recipeDetails.append("Measure4:- ${item.strMeasure4}\n")
        if (blankValidation(item.strMeasure5)) recipeDetails.append("Measure5:- ${item.strMeasure5}\n")
        if (blankValidation(item.strMeasure6)) recipeDetails.append("Measure6:- ${item.strMeasure6}\n")
        if (blankValidation(item.strMeasure7)) recipeDetails.append("Measure7:- ${item.strMeasure7}\n")
        if (blankValidation(item.strMeasure8)) recipeDetails.append("Measure8:- ${item.strMeasure8}\n")
        if (blankValidation(item.strMeasure9)) recipeDetails.append("Measure9:- ${item.strMeasure9}\n")
        if (blankValidation(item.strMeasure10)) recipeDetails.append("Measure10:- ${item.strMeasure10}\n")
        if (blankValidation(item.strMeasure11)) recipeDetails.append("Measure11:- ${item.strMeasure11}\n")
        if (blankValidation(item.strMeasure12)) recipeDetails.append("Measure12:- ${item.strMeasure12}\n")
        if (blankValidation(item.strMeasure13)) recipeDetails.append("Measure13:- ${item.strMeasure13}\n")
        if (blankValidation(item.strMeasure14)) recipeDetails.append("Measure14:- ${item.strMeasure14}\n")
        if (blankValidation(item.strMeasure15)) recipeDetails.append("Measure15:- ${item.strMeasure15}\n")
        if (blankValidation(item.strMeasure16)) recipeDetails.append("Measure16:- ${item.strMeasure16}\n")
        if (blankValidation(item.strMeasure17)) recipeDetails.append("Measure17:- ${item.strMeasure17}\n")
        if (blankValidation(item.strMeasure18)) recipeDetails.append("Measure18:- ${item.strMeasure18}\n")
        if (blankValidation(item.strMeasure19)) recipeDetails.append("Measure19:- ${item.strMeasure19}\n")
        if (blankValidation(item.strMeasure20)) recipeDetails.append("Measure20:- ${item.strMeasure20}\n")
        recipeDetails.append("\n\n")



    }

     return recipeDetails
}
    // function checks if a given string is null or empty, and returns false if it is, or true if it is not.
    private fun blankValidation(strings: String?): Boolean {
        return if (strings == "null")
            false
        else !(strings == "" || strings == " ")
    }

//objects, and saves them to a database. It first retrieves the database ID from the intent extras, then creates a database using Room library
    private suspend fun mealsavingtodatabse(fooditem: List<CategoryOfFood>) {
        try {
            val databaseId = intent.getStringExtra("database_id")
            val database = Room.databaseBuilder(this,ConnectorMeal::class.java,databaseId).build()
            val categoryOfFoodDaos =database.categoryOfFoodDao()
            for (i in fooditem){
                categoryOfFoodDaos.insertCategoryOfFoods(i)
            }
        } catch (e: java.lang.Exception){}
    }




}
