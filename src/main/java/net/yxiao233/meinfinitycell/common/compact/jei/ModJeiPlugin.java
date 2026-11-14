package net.yxiao233.meinfinitycell.common.compact.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.compact.jei.category.InfinityCellInfoCategory;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    @Override
    public @NonNull Identifier getPluginUid() {
        return Meinfinitycell.makeId("jei_plugin");
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
