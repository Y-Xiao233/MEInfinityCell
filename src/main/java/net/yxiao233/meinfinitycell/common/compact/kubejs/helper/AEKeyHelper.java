package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import mekanism.api.MekanismAPI;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.meinfinitycell.common.utils.KeyList;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
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

    public static TagKey<Item> itemTag(String tag){
        return TagKey.create(Registries.ITEM, Objects.requireNonNull(ResourceLocation.tryParse(tag)));
    }

    public static TagKey<Fluid> fluidTag(String tag){
        return TagKey.create(Registries.FLUID, Objects.requireNonNull(ResourceLocation.tryParse(tag)));
    }

    public static KeyList byMod(String modid){
        KeyList keyList = KeyList.create();
        BuiltInRegistries.ITEM.forEach(item -> {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            if (id.getNamespace().equals(modid)){
                keyList.add(item(id));
            }
        });
        BuiltInRegistries.FLUID.forEach(fluid -> {
            ResourceLocation id = BuiltInRegistries.FLUID.getKey(fluid);
            if (id.getNamespace().equals(modid)){
                keyList.add(fluid(id));
            }
        });

        MekanismAPI.CHEMICAL_REGISTRY.forEach(chemical -> {
            ResourceLocation id = MekanismAPI.CHEMICAL_REGISTRY.getKey(chemical);
            if (id.getNamespace().equals(modid)){
                keyList.add(MekanismKeyHelper.of(chemical));
            }
        });
        return keyList;
    }

    @SuppressWarnings("unchecked")
    public static Supplier<KeyList> byTags(TagKey<?>... tags){
        return () -> {
            KeyList keyList = KeyList.create();
            for (Object tag : tags) {
                if(tag instanceof TagKey<?> key){
                    if(key.isFor(Registries.ITEM)){
                        TagKey<Item> itemTagKey = (TagKey<Item>) key;
                        BuiltInRegistries.ITEM.forEach(item -> {
                            if(item.getDefaultInstance().is(itemTagKey)){
                                keyList.add(item(BuiltInRegistries.ITEM.getKey(item)));
                            }
                        });
                    }else if(key.isFor(Registries.FLUID)){
                        TagKey<Fluid> fluidTagKey = (TagKey<Fluid>) key;
                        BuiltInRegistries.FLUID.forEach(fluid -> {
                            if(new FluidStack(fluid,1).is(fluidTagKey)){
                                keyList.add(fluid(BuiltInRegistries.FLUID.getKey(fluid)));
                            }
                        });
                    }
                }
            }
            return keyList;
        };
    }

    public static Supplier<KeyList> byTag(TagKey<?> tag){
        return byTags(tag);
    }

    public static Supplier<AEKey> of(AEKey key){
        return () -> key;
    }
}
