package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

public class AEKeyHelper {
    public static Supplier<AEKey> item(ResourceLocation id){
        return () -> AEItemKey.of(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id)));
    }

    public static Supplier<AEKey> item(ResourceLocation id, CompoundTag tag){
        return () -> AEItemKey.of(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id)),tag);
    }

    public static Supplier<AEKey> fluid(ResourceLocation id){
        return () -> AEFluidKey.of(ForgeRegistries.FLUIDS.getValue(id));
    }

    public static Supplier<AEKey> fluid(ResourceLocation id, CompoundTag tag){
        return () -> AEFluidKey.of(new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(id)),1000,tag));
    }
}
