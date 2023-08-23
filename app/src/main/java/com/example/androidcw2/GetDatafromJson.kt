package com.example.androidcw2

import org.json.JSONObject

class GetDatafromJson {

    fun jsonData (meal: JSONObject, detailsOfRecipe: MutableList<CategoryOfFood>): MutableList<CategoryOfFood> {
        detailsOfRecipe.add(
            // Here we create a new Meal object using the data from the JSON object.
            CategoryOfFood(

                meal.getInt("idMeal"),
                meal.getString("strMeal"),
                meal.getString("strDrinkAlternate"),
                meal.getString("strCategory"),
                meal.getString("strArea"),
                meal.getString("strInstructions"),
                meal.getString("strMealThumb"),
                meal.getString("strTags"),
                meal.getString("strYoutube"),
                meal.getString("strIngredient1"),
                meal.getString("strIngredient2"),
                meal.getString("strIngredient3"),
                meal.getString("strIngredient4"),
                meal.getString("strIngredient5"),
                meal.getString("strIngredient6"),
                meal.getString("strIngredient7"),
                meal.getString("strIngredient8"),
                meal.getString("strIngredient9"),
                meal.getString("strIngredient10"),
                meal.getString("strIngredient11"),
                meal.getString("strIngredient12"),
                meal.getString("strIngredient13"),
                meal.getString("strIngredient14"),
                meal.getString("strIngredient15"),
                meal.getString("strIngredient16"),
                meal.getString("strIngredient17"),
                meal.getString("strIngredient18"),
                meal.getString("strIngredient19"),
                meal.getString("strIngredient20"),
                meal.getString("strMeasure1"),
                meal.getString("strMeasure2"),
                meal.getString("strMeasure3"),
                meal.getString("strMeasure4"),
                meal.getString("strMeasure5"),
                meal.getString("strMeasure6"),
                meal.getString("strMeasure7"),
                meal.getString("strMeasure8"),
                meal.getString("strMeasure9"),
                meal.getString("strMeasure10"),
                meal.getString("strMeasure11"),
                meal.getString("strMeasure12"),
                meal.getString("strMeasure13"),
                meal.getString("strMeasure14"),
                meal.getString("strMeasure15"),
                meal.getString("strMeasure16"),
                meal.getString("strMeasure17"),
                meal.getString("strMeasure18"),
                meal.getString("strMeasure19"),
                meal.getString("strMeasure20"),
                meal.getString("strSource"),
                meal.getString("strImageSource"),
                meal.getString("strCreativeCommonsConfirmed"),
                meal.getString("dateModified")

            )
        )
        return detailsOfRecipe

    }
}