package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

public class AEKeyHelper {
    public static Supplier<AEKey> item(ResourceLocation id){
        return () -> AEItemKey.of(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id)));
    }

    public static Supplier<AEKey> fluid(ResourceLocation id){
        return () -> AEFluidKey.of(ForgeRegistries.FLUIDS.getValue(id));
    }
}
