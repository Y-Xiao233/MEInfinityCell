package net.yxiao233.meinfinitycell.client.compact.mekanism;

import appeng.api.stacks.AEKey;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import me.ramidzkh.mekae2.ae2.MekanismKeyType;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.util.ChemicalUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class MekanismInfinitiesTooltip {
    public static void addTooltip(ItemTooltipEvent event){
        ArrayList<InfinitiesCell> list = InfinitiesCell.LIST;
        if(!list.isEmpty()){
            list.forEach(cell -> cell.getKeys().getList().forEach(key ->{
                AEKey aeKey = key.get();
                if(aeKey != null){
                    if(aeKey.getType().equals(MekanismKeyType.TYPE)){
                        MekanismKey mekanismKey = (MekanismKey) aeKey;
                        ItemStack creativeChemicalTank = ChemicalUtil.getFilledVariant(MekanismBlocks.CREATIVE_CHEMICAL_TANK.getItemHolder(), mekanismKey.getStack().getChemicalHolder());
                        if(ItemStack.isSameItemSameComponents(event.getItemStack(),creativeChemicalTank)){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.chemical.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }
                }
            }));
        }
    }
}
