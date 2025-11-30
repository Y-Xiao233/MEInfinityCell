package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEKey;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.api.chemical.pigment.Pigment;
import mekanism.api.chemical.slurry.Slurry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class MekanismKeyHelper {
    public static Supplier<AEKey> gas(ResourceLocation id){
        return () -> MekanismKey.of(Gas.getFromRegistry(id).getStack(256));
    }

    public static Supplier<AEKey> infuseType(ResourceLocation id){
        return () -> MekanismKey.of(InfuseType.getFromRegistry(id).getStack(256));
    }

    public static Supplier<AEKey> pigment(ResourceLocation id){
        return () -> MekanismKey.of(Pigment.getFromRegistry(id).getStack(256));
    }

    public static Supplier<AEKey> slurry(ResourceLocation id){
        return () -> MekanismKey.of(Slurry.getFromRegistry(id).getStack(256));
    }
}
