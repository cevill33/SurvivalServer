package me.survival.shop;

import org.bukkit.inventory.ItemStack;

public class BuyObject {

	
	
	private ItemStack stack;
	private double price;

	public BuyObject(ItemStack stack, double price) {
		this.stack = stack;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public ItemStack getStack() {
		return stack;
	}
}
