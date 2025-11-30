package net.yxiao233.meinfinitycell.common.registry;

import appeng.api.stacks.AEFluidKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;

@SuppressWarnings("unused")
public class MICItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Meinfinitycell.MODID);

    public static final DeferredHolder<Item,Item> INFINITY_COBBLESTONE_CELL = ITEMS.register("infinity_cobblestone_cell", () -> new InfinityCell());
    public static final DeferredHolder<Item,Item> INFINITY_WATER_CELL = ITEMS.register("infinity_water_cell", () -> new InfinityCell(() -> AEFluidKey.of(Fluids.WATER)));
}
