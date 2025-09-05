package net.yxiao233.meinfinitycell;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import appeng.api.storage.StorageCells;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.yxiao233.meinfinitycell.common.inventory.InfinitiesCellInventory;
import net.yxiao233.meinfinitycell.common.inventory.InfinityCellInventory;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.registry.MICCreativeModeTab;
import net.yxiao233.meinfinitycell.common.registry.MICItems;

import java.util.ArrayList;

@Mod(Meinfinitycell.MODID)
public class Meinfinitycell {
    public static final String MODID = "meinfinitycell";
    public Meinfinitycell(IEventBus modEventBus, ModContainer container) {
        MICItems.ITEMS.register(modEventBus);
        MICCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
        StorageCells.addCellHandler(InfinityCellInventory.HANDLER);
        StorageCells.addCellHandler(InfinitiesCellInventory.HANDLER);
    }

    public static long getMax(AEKey key){
        return (long) Integer.MAX_VALUE * key.getAmountPerUnit();
    }

    @SuppressWarnings("removal")
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onAddInfinityItemsTip(ItemTooltipEvent event){
            ArrayList<InfinitiesCell> list = InfinitiesCell.LIST;
            if(!list.isEmpty()){
                list.forEach(cell ->{
                    cell.getKeys().getList().forEach(key ->{
                        AEKey aeKey = key.get();
                        if(aeKey != null && aeKey.getType().equals(AEKeyType.items())){
                            if(event.getItemStack().is(BuiltInRegistries.ITEM.get(aeKey.getId()))){
                                event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                            }
                        }
                    });
                });
            }
        }
    }
}
