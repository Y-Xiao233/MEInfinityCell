package net.yxiao233.meinfinitycell.common.registry;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class MICItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Meinfinitycell.MODID);

    public static final DeferredHolder<Item, InfinityCell> INFINITY_COBBLESTONE_CELL = registryInfinityCell("infinity_cobblestone_cell",() -> AEItemKey.of(Items.COBBLESTONE));
    public static final DeferredHolder<Item, InfinityCell> INFINITY_WATER_CELL = registryInfinityCell("infinity_water_cell", () -> AEFluidKey.of(Fluids.WATER));
    public static DeferredHolder<Item, InfinityCell> registryInfinityCell(String name, Supplier<AEKey> key){
        return ITEMS.register(name,() -> new InfinityCell(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Meinfinitycell.makeId(name))),key));
    }
}
