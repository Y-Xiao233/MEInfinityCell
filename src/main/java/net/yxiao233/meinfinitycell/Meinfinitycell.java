package net.yxiao233.meinfinitycell;

import appeng.api.stacks.AEKey;
import appeng.api.storage.StorageCells;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.yxiao233.meinfinitycell.common.inventory.InfinitiesCellInventory;
import net.yxiao233.meinfinitycell.common.inventory.InfinityCellInventory;
import net.yxiao233.meinfinitycell.common.registry.MICCreativeModeTab;
import net.yxiao233.meinfinitycell.common.registry.MICItems;

@Mod(Meinfinitycell.MODID)
@SuppressWarnings("unused")
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
}
