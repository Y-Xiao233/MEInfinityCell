package net.yxiao233.meinfinitycell.client;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;

import java.util.ArrayList;

@SuppressWarnings({"unused","removal"})
@EventBusSubscriber(modid = Meinfinitycell.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemTooltipEvents {
    @SubscribeEvent
    public static void onAddInfinityItemsTip(ItemTooltipEvent event){
        ArrayList<InfinitiesCell> list = InfinitiesCell.LIST;
        if(!list.isEmpty()){
            list.forEach(cell -> cell.getKeys().getList().forEach(key ->{
                AEKey aeKey = key.get();
                if(aeKey != null){
                    if(aeKey.getType().equals(AEKeyType.items())){
                        AEItemKey itemKey = (AEItemKey) aeKey;
                        if(ItemStack.isSameItemSameComponents(event.getItemStack(),itemKey.toStack())){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }else if(aeKey.getType().equals(AEKeyType.fluids())){
                        AEFluidKey fluidKey = (AEFluidKey) aeKey;
                        Item bucket = fluidKey.toStack(1000).getFluid().getBucket();
                        if(event.getItemStack().is(bucket)){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.fluid.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }
                }
            }));
        }
    }
}
