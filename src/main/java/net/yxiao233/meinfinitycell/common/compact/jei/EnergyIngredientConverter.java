package net.yxiao233.meinfinitycell.common.compact.jei;

import appeng.api.stacks.GenericStack;
import com.glodblock.github.appflux.common.me.key.FluxKey;
import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import mezz.jei.api.ingredients.IIngredientType;
import org.jetbrains.annotations.NotNull;
import tamaized.ae2jeiintegration.api.integrations.jei.IngredientConverter;

public record EnergyIngredientConverter(IIngredientType<EnergyType> type) implements IngredientConverter<EnergyType> {
    @Override
    public @NotNull IIngredientType<EnergyType> getIngredientType() {
        return type;
    }

    @Override
    public EnergyType getIngredientFromStack(GenericStack stack) {
        if(stack.what() instanceof FluxKey fluxKey && type.getIngredientClass().isInstance(fluxKey.getEnergyType())){
            return fluxKey.getEnergyType();
        }
        return null;
    }

    @Override
    public @NotNull GenericStack getStackFromIngredient(@NotNull EnergyType type) {
        return new GenericStack(FluxKey.of(type),1);
    }
}
