package net.yxiao233.meinfinitycell.common.registry;

import appeng.api.stacks.AEFluidKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;

public class MICItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Meinfinitycell.MODID);

    public static final RegistryObject<Item> INFINITY_COBBLESTONE_CELL = ITEMS.register("infinity_cobblestone_cell", InfinityCell::new);
    public static final RegistryObject<Item> INFINITY_WATER_CELL = ITEMS.register("infinity_water_cell", () -> new InfinityCell(() -> AEFluidKey.of(Fluids.WATER)));
}
