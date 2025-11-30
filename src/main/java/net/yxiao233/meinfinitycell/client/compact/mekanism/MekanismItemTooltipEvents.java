package net.yxiao233.meinfinitycell.client.compact.mekanism;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.utils.LoadList;

@SuppressWarnings({"unused","removal"})
@EventBusSubscriber(modid = Meinfinitycell.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MekanismItemTooltipEvents {
    @SubscribeEvent
    public static void onAddInfinityItemsTip(ItemTooltipEvent event){
        if(LoadList.MEKANISM && LoadList.APPLIED_MEKANISM){
            MekanismInfinitiesTooltip.addTooltip(event);
        }
    }
}