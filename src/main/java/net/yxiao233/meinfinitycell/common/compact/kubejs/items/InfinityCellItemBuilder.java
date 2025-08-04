package net.yxiao233.meinfinitycell.common.compact.kubejs.items;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import mekanism.api.chemical.gas.Gas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.MekanismKeyHelper;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;

import java.util.Objects;
import java.util.function.Supplier;

public class InfinityCellItemBuilder extends ItemBuilder {
    private Supplier<AEKey> key;
    public InfinityCellItemBuilder(ResourceLocation i) {
        super(i);
    }

    public InfinityCellItemBuilder type(Supplier<AEKey> key){
        this.key = key;
        return this;
    }

    public InfinityCellItemBuilder fluidType(ResourceLocation id){
        this.key = () -> AEFluidKey.of(ForgeRegistries.FLUIDS.getValue(id));
        return this;
    }

    public InfinityCellItemBuilder itemType(ResourceLocation id){
        this.key = () -> AEItemKey.of(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id)));
        return this;
    }

    @Override
    public Item createObject() {
        return new InfinityCell(key);
    }
}
