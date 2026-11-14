package net.yxiao233.meinfinitycell.common.compact.jei;

import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import dev.latvian.mods.kubejs.script.ScriptType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.compact.jei.category.InfinityCellInfoCategory;
import net.yxiao233.meinfinitycell.common.compact.kubejs.events.JeiInfoRegistryEvent;
import net.yxiao233.meinfinitycell.common.compact.kubejs.events.MEInfinityCellEvents;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;
import net.yxiao233.meinfinitycell.common.utils.LoadList;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import tamaized.ae2jeiintegration.api.integrations.jei.IngredientConverters;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
@SuppressWarnings({"unused","removal"})
public class MICJEIPlugin implements IModPlugin {
    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        if(LoadList.APPLIED_FLUX){
            IngredientConverters.register(new EnergyIngredientConverter(AppFluxTypes.ENERGY_TYPE));
        }
        MEInfinityCellEvents.JEI.post(ScriptType.STARTUP, new JeiInfoRegistryEvent());
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

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new InfinityCellInfoCategory(guiHelper));
    }

    @Override
    public void registerRecipes(@NonNull IRecipeRegistration registration) {
        ArrayList<InfinityCellInfoCategory.CellInfoRecipeWrapper> wrappers = new ArrayList<>();
        BuiltInRegistries.ITEM.forEach(item -> {
            if(item instanceof InfinityCell infinityCell){
                wrappers.add(new InfinityCellInfoCategory.CellInfoRecipeWrapper(infinityCell.getDefaultInstance()));
            }else if(item instanceof InfinitiesCell infinitiesCell){
                wrappers.add(new InfinityCellInfoCategory.CellInfoRecipeWrapper(infinitiesCell.getDefaultInstance()));
            }
        });
        registration.addRecipes(InfinityCellInfoCategory.TYPE,wrappers);
    }
}