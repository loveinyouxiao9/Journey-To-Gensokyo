package com.katrix.journeyToGensokyo.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class GensokyoNoteItem extends JTGBaseItem {
	
	public GensokyoNoteItem() {
		super();
		setHasSubtypes(true);
	}
	
	public IIcon[] icons = new IIcon[6];
	
    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < 4; i ++) {
        	if(i == 0){
                this.icons[i] = reg.registerIcon("journeytogensokyo:gensokyoNotesRuined");
        	}
        	if(i == 1){
                this.icons[i] = reg.registerIcon("journeytogensokyo:gensokyoNotesPat");
        	}
        	if(i == 2){
                this.icons[i] = reg.registerIcon("journeytogensokyo:gensokyoNotesDus");
        	}
        	if(i == 3){
                this.icons[i] = reg.registerIcon("journeytogensokyo:gensokyoNotes");
        	}
        }
    }
    
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > 3)
            meta = 0;

        return this.icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 4; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.gensokyoNotes" + "_" + stack.getItemDamage();
    }
}
