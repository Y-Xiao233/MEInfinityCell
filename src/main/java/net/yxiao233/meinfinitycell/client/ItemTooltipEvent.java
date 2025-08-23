package net.yxiao233.meinfinitycell.client;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;

import java.util.ArrayList;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Meinfinitycell.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemTooltipEvent {
    @SubscribeEvent
    public static void onAddInfinityItemsTip(net.minecraftforge.event.entity.player.ItemTooltipEvent event){
        ArrayList<InfinitiesCell> list = InfinitiesCell.LIST;
        if(!list.isEmpty()){
            list.forEach(cell ->{
                cell.getKeys().getList().forEach(key ->{
                    AEKey aeKey = key.get();
                    if(aeKey != null && aeKey.getType().equals(AEKeyType.items())){
                        if(event.getItemStack().is(ForgeRegistries.ITEMS.getValue(aeKey.getId()))){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }
                });
            });
        }
    }
}
