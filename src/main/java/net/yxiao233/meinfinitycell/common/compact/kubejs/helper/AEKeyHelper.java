package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.meinfinitycell.common.utils.KeyList;

import java.util.Objects;
import java.util.Optional;
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

    public static KeyList byMod(String modid){
        KeyList keyList = KeyList.create();
        BuiltInRegistries.ITEM.forEach(item -> {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            if (id.getNamespace().equals(modid)){
                keyList.add(item(id));
            }
        });
        BuiltInRegistries.FLUID.forEach(fluid -> {
            Identifier id = BuiltInRegistries.FLUID.getKey(fluid);
            if (id.getNamespace().equals(modid)){
                keyList.add(fluid(id));
            }
        });
        return keyList;
    }

    public static TagKey<Item> itemTag(String tag){
        return TagKey.create(Registries.ITEM, Objects.requireNonNull(Identifier.tryParse(tag)));
    }

    public static TagKey<Fluid> fluidTag(String tag){
        return TagKey.create(Registries.FLUID, Objects.requireNonNull(Identifier.tryParse(tag)));
    }

    @SuppressWarnings("unchecked")
    public static Supplier<KeyList> byTags(TagKey<?>... tags){
        return () -> {
            KeyList keyList = KeyList.create();
            for (Object tag : tags) {
                if(tag instanceof TagKey<?> key){
                    if(key.isFor(Registries.ITEM)){
                        TagKey<Item> itemTagKey = (TagKey<Item>) key;
                        Optional<HolderSet.Named<Item>> holders = BuiltInRegistries.ITEM.get(itemTagKey);
                        holders.ifPresent(itemNamed -> itemNamed.forEach(itemHolder -> {
                            keyList.add(item(BuiltInRegistries.ITEM.getKey(itemHolder.value())));
                        }));
                    }else if(key.isFor(Registries.FLUID)){
                        TagKey<Fluid> fluidTagKey = (TagKey<Fluid>) key;
                        Optional<HolderSet.Named<Fluid>> holders = BuiltInRegistries.FLUID.get(fluidTagKey);
                        holders.ifPresent(fluidNamed -> fluidNamed.forEach(fluidHolder -> {
                            keyList.add(fluid(BuiltInRegistries.FLUID.getKey(fluidHolder.value())));
                        }));
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
