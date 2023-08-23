package com.example.androidcw2

import android.net.Network
import android.util.Log

object RecipeforUser
{

    fun showPotionsViaTextView(potionsList: List<CategoryOfFood>): StringBuilder
    {

        val potionsRecipes = StringBuilder()


        for(meal in potionsList)
        {
            val ingredients = mutableListOf<String>()
            val quantities = mutableListOf<String>()
// Loop through the first 20 ingredients and quantities of the current meal
            for ( i in 1..20 )
            {
                // Get the i-th ingredient and quantity of the current meal
                val ingredient = meal.getIngredient(i)
                val quantity = meal.getQuantity(i)

                // If the ingredient and quantity are not null and pass the validation test, add them to their respective lists
                if (Verification.validate(ingredient) && ingredient != null)
                    ingredients.add(ingredient)

                if(Verification.validate(quantity) && quantity != null)
                    quantities.add(quantity)
            }
// Append the details of the current meal to the potionsRecipes string
            with(meal)
            {
                potionsRecipes.append("Meal:$strMeal\n")
                potionsRecipes.append("DrinkAlternate:$strDrinkAlternate\n")
                potionsRecipes.append("Category:$strCategory\n")
                potionsRecipes.append("Area:$strArea\n")
                potionsRecipes.append("Instructions:$strInstructions\n")
                potionsRecipes.append("Tags:$strTags\n")
                potionsRecipes.append("Youtube:$strYoutube\n")

                // Append each ingredient to the potionsRecipes string with its corresponding index
                for (i in ingredients.indices)
                {
                    potionsRecipes.append("Ingredient${i + 1}:${ingredients[i]}\n")
                }
// Append each quantity to the potionsRecipes string with its corresponding index
                for (i in quantities.indices)
                {
                    potionsRecipes.append("Measure${i + 1}:${quantities[i]}\n")
                }
                // Add new lines to separate the details of each meal in the potionsRecipes string
                potionsRecipes.append("\n\n")
            }
        }
        // return the stringBuilder object.
        return potionsRecipes
    }

    // The showRecipesInRecyclerView function is declared as a suspend function, so it can be called from within a coroutine.
    suspend fun showRecipesInRecyclerView(mealList: List<CategoryOfFood>, mealPair: ArrayList<ImageMeal>): ArrayList<ImageMeal>
    {

        // Iterate through each Meal object in the list and extract its ingredients and measurements
        for (meal in mealList)
        {
            val ingredients = mutableListOf<String>()
            val measurements = mutableListOf<String>()

            for (i in 1..20)
            {
                val ingredient = meal.getIngredient(i)
                if (ingredient != null) {
                    Log.d("+++++++++++++",ingredient )
                }
                val measurement = meal.getQuantity(i)
                if (measurement != null) {
                    Log.d("+++++++++++++",measurement )
                }
                // Verify that the ingredient and measurement are not null or empty before adding to the list
                if (Verification.validate(ingredient) && ingredient != null)
                    ingredients.add(ingredient)

                if(Verification.validate(measurement) && measurement != null)
                    measurements.add(measurement)
            }
// Create a StringBuilder object to construct the recipe string
            val recipe = StringBuilder()
            with(meal)
            {

                // Append various attributes of the meal object to the recipe string
                recipe.append("Meal:$strMeal\n")
                recipe.append("DrinkAlternate:$strDrinkAlternate\n")
                recipe.append("Category:$strCategory\n")
                recipe.append("Area:$strArea\n")
                recipe.append("Instructions:$strInstructions\n")
                recipe.append("strMealThumb :- $strMealThumb\n")
                recipe.append("Tags:$strTags\n")
                recipe.append("Youtube:$strYoutube\n")
                // Append the ingredients and measurements to the recipe string
                for (i in ingredients.indices)
                {
                    recipe.append("Ingredient${i + 1}:${ingredients[i]}\n")
                }

                for (i in measurements.indices)
                {
                    recipe.append("Measure${i + 1}:${measurements[i]}\n")
                }
                recipe.append("\n\n")
                Log.d("***********************", recipe.toString())
            }

// Load the image for the meal and add a new ImageMeal object to the list
            mealPair.add(ImageMeal( Connection.loadforImage(meal.strMealThumb!!), recipe.toString()) )
        }

        return mealPair
    }
}