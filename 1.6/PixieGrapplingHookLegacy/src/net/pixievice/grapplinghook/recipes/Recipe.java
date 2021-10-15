package net.pixievice.grapplinghook.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import net.pixievice.grapplinghook.Main;
import net.pixievice.grapplinghook.items.GrapplingHookItem;

public class Recipe {

	GrapplingHookItem gpi = new GrapplingHookItem();
	Main main;
	
	public Recipe(Main main) {
		this.main = main;
	}
		
	public void createRecipe() {
		
		if (main.getConfig().getBoolean("Recipe.use") == true) {
		
		ShapedRecipe gh = new ShapedRecipe(gpi.GrapplingHook());
		
		String itemZ = main.getConfig().getString("Recipe.materials.a");
		String itemO = main.getConfig().getString("Recipe.materials.b");
		String itemT = main.getConfig().getString("Recipe.materials.c");
		
		String line0 = main.getConfig().getString("Recipe.recipe.0");
		String line1 = main.getConfig().getString("Recipe.recipe.1");
		String line2 = main.getConfig().getString("Recipe.recipe.2");
		
		gh.shape(line0, line1, line2);
		
		gh.setIngredient('a', Material.getMaterial(itemZ));
		gh.setIngredient('b', Material.getMaterial(itemO));
		gh.setIngredient('c', Material.getMaterial(itemT));
		
		Bukkit.addRecipe(gh);
	}	
	}
	
}
