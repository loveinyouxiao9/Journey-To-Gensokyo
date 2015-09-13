package com.katrix.journeyToGensokyo.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class GensokyoDustItem extends JTGBaseItem {
	
	public GensokyoDustItem() {
		super();
		setHasSubtypes(true);
	}
	
	public IIcon[] icons = new IIcon[3];
	
    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < 3; i ++) {
        	if(i == 0){
                this.icons[i] = reg.registerIcon("journeytogensokyo:gensokyoDust");
        	}
        	
        	if(i == 1){
                this.icons[i] = reg.registerIcon("journeytogensokyo:demonDust");
        	}
        	
        	if(i == 2){
                this.icons[i] = reg.registerIcon("journeytogensokyo:celestialDust");
        	}
        }
    }
    
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > 2)
            meta = 0;

        return this.icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 3; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.gensokyoDust" + "_" + stack.getItemDamage();
    }
}
