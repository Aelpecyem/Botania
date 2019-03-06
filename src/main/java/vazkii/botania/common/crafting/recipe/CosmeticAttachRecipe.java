/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Feb 22, 2015, 8:49:53 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeHidden;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeSerializers;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.common.lib.LibMisc;

import javax.annotation.Nonnull;

public class CosmeticAttachRecipe extends IRecipeHidden {
	private static final ResourceLocation TYPE_ID = new ResourceLocation(LibMisc.MOD_ID, "cosmetic_attach");
	public static final IRecipeSerializer<CosmeticAttachRecipe> SERIALIZER = new RecipeSerializers.SimpleSerializer<>(TYPE_ID.toString(), CosmeticAttachRecipe::new);

	public CosmeticAttachRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean matches(@Nonnull IInventory inv, @Nonnull World world) {
		boolean foundCosmetic = false;
		boolean foundAttachable = false;

		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(!stack.isEmpty()) {
				if(stack.getItem() instanceof ICosmeticBauble && !foundCosmetic)
					foundCosmetic = true;
				else if(!foundAttachable) {
					if(stack.getItem() instanceof ICosmeticAttachable && !(stack.getItem() instanceof ICosmeticBauble) && ((ICosmeticAttachable) stack.getItem()).getCosmeticItem(stack).isEmpty())
						foundAttachable = true;
					else return false;
				}
			}
		}

		return foundCosmetic && foundAttachable;
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull IInventory inv) {
		ItemStack cosmeticItem = ItemStack.EMPTY;
		ItemStack attachableItem = ItemStack.EMPTY;

		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(!stack.isEmpty()) {
				if(stack.getItem() instanceof ICosmeticBauble && cosmeticItem.isEmpty())
					cosmeticItem = stack;
				else attachableItem = stack;
			}
		}

		if(!(attachableItem.getItem() instanceof ICosmeticAttachable))
			return ItemStack.EMPTY;
		
		ICosmeticAttachable attachable = (ICosmeticAttachable) attachableItem.getItem();
		if(!attachable.getCosmeticItem(attachableItem).isEmpty())
			return ItemStack.EMPTY;

		ItemStack copy = attachableItem.copy();
		attachable.setCosmeticItem(copy, cosmeticItem);
		return copy;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= 2;
	}

	@Nonnull
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
}
