/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Journey To Gensokyo Mod. Get the Source Code in github:
 * https://github.com/Katrix-/JTG
 *
 * Journey To Gensokyo is Open Source and distributed under the
 * a modifed Botania license: https://github.com/Katrix-/JTG/blob/master/LICENSE.md
 */
package net.katsstuff.journeytogensokyo.container.slot

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{IInventory, InventoryCrafting, SlotCrafting}
import net.minecraft.item.ItemStack

class SlotDanmakuOutput(player: EntityPlayer, ingredients: IInventory, matrix: InventoryCrafting, inv: IInventory, index: Int, xPos: Int, yPos: Int)
	extends SlotCrafting(player, matrix, inv, index, xPos, yPos) {

	override def onPickupFromSlot(playerIn: EntityPlayer, stack: ItemStack): Unit = {
		matrix.clear()
		ingredients.clear()
	}
}