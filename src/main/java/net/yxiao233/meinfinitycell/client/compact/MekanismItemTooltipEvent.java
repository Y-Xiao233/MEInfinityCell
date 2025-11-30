package net.yxiao233.meinfinitycell.client.compact;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.client.compact.mekanism.MekanismInfinitiesTooltip;
import net.yxiao233.meinfinitycell.common.utils.LoadList;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Meinfinitycell.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MekanismItemTooltipEvent {
    @SubscribeEvent
    public static void onAddInfinityItemsTip(ItemTooltipEvent event){
        if(LoadList.MEKANISM && LoadList.APPLIED_MEKANISM){
            MekanismInfinitiesTooltip.addTooltip(event);
        }
    }
}
