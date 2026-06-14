package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class AEKeyHelper {
    public static Supplier<AEKey> item(Identifier id){
        return () -> AEItemKey.of(Objects.requireNonNull(BuiltInRegistries.ITEM.getValue(id)));
    }

    public static Supplier<AEKey> fluid(Identifier id){
        return () -> AEFluidKey.of(BuiltInRegistries.FLUID.getValue(id));
    }

    public static Supplier<AEKey> ofItem(Supplier<ItemStack> stackSupplier){
        return () -> AEItemKey.of(stackSupplier.get());
    }

    public static Supplier<AEKey> ofFluid(Supplier<FluidStack> stackSupplier){
        return () -> AEFluidKey.of(stackSupplier.get());
    }

    public static Supplier<AEKey> of(AEKey key){
        return () -> key;
    }
}
