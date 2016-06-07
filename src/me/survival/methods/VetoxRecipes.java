package me.survival.methods;

import me.survival.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import me.survival.api.ItemBuilder;

import java.util.Iterator;

public class VetoxRecipes {
	
	public static ItemStack fireball = new ItemBuilder(Material.FIREBALL).setDiplayname("§aEnchanter").addEnchant().addItemFlag(ItemFlag.HIDE_ENCHANTS).build();
	public static void addFireBallRecipe() {
		ShapedRecipe sp = new ShapedRecipe(fireball);
		sp.shape("GLG", "EDE", "EFE");
		sp.setIngredient('E', Material.IRON_INGOT);
		sp.setIngredient('F', Material.FLINT_AND_STEEL);
		sp.setIngredient('L', Material.LAPIS_BLOCK);
		sp.setIngredient('G', Material.GOLD_INGOT);
		sp.setIngredient('D', Material.DIAMOND);
		Bukkit.addRecipe(sp);
		
		
	}


	/*
	Disabels: BlazePowder, All Swords
	 */
	public static void disableRecipes(Main main) {

		Iterator iter = main.getServer().recipeIterator();
		while (iter.hasNext()) {

			Recipe r = (Recipe) iter.next();
			if(r.getResult().getType().equals(Material.STONE_SWORD) || r.getResult().getType().equals(Material.IRON_SWORD) || r.getResult().getType().equals(Material.DIAMOND_SWORD)
					|| r.getResult().getType().equals(Material.WOOD_SWORD) || r.getResult().getType().equals(Material.BLAZE_POWDER) || r.getResult().getType().equals(Material.STONE_SWORD)) {
				iter.remove();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
