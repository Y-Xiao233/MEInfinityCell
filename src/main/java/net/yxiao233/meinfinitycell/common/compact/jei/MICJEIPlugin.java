package net.yxiao233.meinfinitycell.common.compact.jei;

import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.utils.LoadList;
import org.jetbrains.annotations.NotNull;
import tamaized.ae2jeiintegration.api.integrations.jei.IngredientConverters;

import java.util.List;

@JeiPlugin
@SuppressWarnings({"unused","removal"})
public class MICJEIPlugin implements IModPlugin {
    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        if(LoadList.APPLIED_FLUX){
            IngredientConverters.register(new EnergyIngredientConverter(AppFluxTypes.ENERGY_TYPE));
        }
    }

    @Override
    public void registerIngredients(@NotNull IModIngredientRegistration registration) {
        if(LoadList.APPLIED_FLUX){
            registration.register(AppFluxTypes.ENERGY_TYPE, List.of(EnergyType.FE,EnergyType.GTEU),new AppFluxTypes.EnergyStackHelper(),new AppFluxTypes.EnergyStackRenderer());
        }
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Meinfinitycell.MODID,"jei");
    }
}