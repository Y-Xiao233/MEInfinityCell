package net.yxiao233.meinfinitycell.common.compact.kubejs.events;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.rhino.util.HideFromJS;
import mezz.jei.api.ingredients.IIngredientType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.function.Function;

public class JeiInfoRegistryEvent implements KubeEvent {
    @HideFromJS
    public static final HashMap<AEKeyType, Pair<IIngredientType<?>, Function<AEKey, ?>>> MAP = new HashMap<>();
    public static <T> void registryJeiInfoType(AEKeyType type, IIngredientType<T> ingredientType, Function<AEKey, T> converter){
        MAP.put(type,Pair.of(ingredientType,converter));
    }
}
