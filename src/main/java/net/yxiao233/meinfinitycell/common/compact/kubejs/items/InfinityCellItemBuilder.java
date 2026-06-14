package net.yxiao233.meinfinitycell.common.compact.kubejs.items;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class InfinityCellItemBuilder extends ItemBuilder {
    private Supplier<AEKey> key;
    public InfinityCellItemBuilder(Identifier i) {
        super(i);
    }

    public InfinityCellItemBuilder type(Supplier<AEKey> key){
        this.key = key;
        return this;
    }

    public InfinityCellItemBuilder fluidType(Identifier id){
        this.key = () -> AEFluidKey.of(BuiltInRegistries.FLUID.getValue(id));
        return this;
    }

    public InfinityCellItemBuilder itemType(Identifier id){
        this.key = () -> AEItemKey.of(Objects.requireNonNull(BuiltInRegistries.ITEM.getValue(id)));
        return this;
    }

    @Override
    public @NonNull Item createObject() {
        return new InfinityCell(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,id)),key);
    }
}
