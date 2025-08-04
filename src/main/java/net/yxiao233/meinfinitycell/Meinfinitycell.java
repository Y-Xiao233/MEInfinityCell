package net.yxiao233.meinfinitycell;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.storage.StorageCells;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yxiao233.meinfinitycell.common.inventory.InfinitiesCellInventory;
import net.yxiao233.meinfinitycell.common.inventory.InfinityCellInventory;
import net.yxiao233.meinfinitycell.common.registry.MICCreativeModeTab;
import net.yxiao233.meinfinitycell.common.registry.MICItems;

@Mod(Meinfinitycell.MODID)
public class Meinfinitycell {
    public static final String MODID = "meinfinitycell";
    public Meinfinitycell() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MICItems.ITEMS.register(modEventBus);
        MICCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
        StorageCells.addCellHandler(InfinityCellInventory.HANDLER);
        StorageCells.addCellHandler(InfinitiesCellInventory.HANDLER);
    }

    public static long getMax(AEKey key){
        return (long) Integer.MAX_VALUE * key.getAmountPerUnit();
    }
}
