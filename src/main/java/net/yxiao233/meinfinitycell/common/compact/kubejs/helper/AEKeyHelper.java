package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.Objects;
import java.util.function.Supplier;

public class AEKeyHelper {
    public static Supplier<AEKey> item(ResourceLocation id){
        return () -> AEItemKey.of(Objects.requireNonNull(BuiltInRegistries.ITEM.get(id)));
    }

    public static Supplier<AEKey> item(ResourceLocation id, DataComponentMap components){
        ItemStack stack = new ItemStack(BuiltInRegistries.ITEM.get(id));
        stack.applyComponents(components);
        return () -> AEItemKey.of(stack);
    }

    public static Supplier<AEKey> fluid(ResourceLocation id){
        return () -> AEFluidKey.of(BuiltInRegistries.FLUID.get(id));
    }

    public static Supplier<AEKey> fluid(ResourceLocation id, DataComponentMap components){
        FluidStack stack = new FluidStack(BuiltInRegistries.FLUID.get(id),1000);
        stack.applyComponents(components);
        return () -> AEFluidKey.of(stack);
    }

    public static Supplier<AEKey> of(AEKey key){
        return () -> key;
    }
}
