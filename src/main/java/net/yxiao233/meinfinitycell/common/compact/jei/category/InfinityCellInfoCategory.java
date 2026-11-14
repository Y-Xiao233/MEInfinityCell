package net.yxiao233.meinfinitycell.common.compact.jei.category;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import com.glodblock.github.appflux.common.me.key.type.FluxKeyType;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import me.ramidzkh.mekae2.ae2.MekanismKeyType;
import mekanism.client.recipe_viewer.jei.MekanismJEIHelper;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawablesView;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.gui.widgets.IScrollGridWidget;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.compact.jei.AppFluxTypes;
import net.yxiao233.meinfinitycell.common.compact.kubejs.events.JeiInfoRegistryEvent;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.items.InfinityCell;
import net.yxiao233.meinfinitycell.common.registry.MICItems;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.function.Function;

public class InfinityCellInfoCategory extends AbstractRecipeCategory<InfinityCellInfoCategory.CellInfoRecipeWrapper> {
    public static final RecipeType<CellInfoRecipeWrapper> TYPE = RecipeType.create(Meinfinitycell.MODID,"cell_info",CellInfoRecipeWrapper.class);
    public static final Component TITLE = Component.translatable("jei.meinfinitycell.cell_info");

    public InfinityCellInfoCategory(IGuiHelper helpers) {
        super(TYPE, TITLE, helpers.createDrawableItemLike(MICItems.INFINITY_COBBLESTONE_CELL.get()), 142, 110);
    }

    @Override
    public void setRecipe(@NonNull IRecipeLayoutBuilder builder, @NonNull CellInfoRecipeWrapper wrapper, @NonNull IFocusGroup group) {
        builder.addInputSlot().addItemStack(wrapper.cell).setStandardSlotBackground();
        wrapper.addOutputs(builder);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, @NonNull CellInfoRecipeWrapper wrapper, @NonNull IFocusGroup group) {
        IRecipeSlotDrawablesView recipeSlots = builder.getRecipeSlots();
        List<IRecipeSlotDrawable> outputSlots = recipeSlots.getSlots(RecipeIngredientRole.OUTPUT);
        IScrollGridWidget scrollGridWidget = builder.addScrollGridWidget(outputSlots, 7, 5);
        scrollGridWidget.setPosition(0, 0, this.getWidth(), this.getHeight(), HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        IRecipeSlotDrawable inputSlot = recipeSlots.getSlots(RecipeIngredientRole.INPUT).getFirst();
        inputSlot.setPosition(scrollGridWidget.getScreenRectangle().position().x() + 1, 1);
    }

    public record CellInfoRecipeWrapper(ItemStack cell){
        public void addOutputs(IRecipeLayoutBuilder builder){
            if(cell.getItem() instanceof InfinityCell infinityCell){
                addSlot(builder,infinityCell.getKey().get());
            }else if(cell.getItem() instanceof InfinitiesCell infinitiesCell){
                infinitiesCell.getKeys().get().getList().forEach(keySupplier -> {
                    addSlot(builder,keySupplier.get());
                });
            }
        }

        private void addSlot(IRecipeLayoutBuilder builder, AEKey key){
            AEKeyType type = key.getType();
            if(type == AEKeyType.items()){
                builder.addOutputSlot().addItemStack(((AEItemKey) key).toStack());
            }else if(type == AEKeyType.fluids()){
                builder.addOutputSlot().addFluidStack(((AEFluidKey) key).getFluid());
            }else if(type == FluxKeyType.TYPE){
                builder.addOutputSlot().addIngredient(AppFluxTypes.ENERGY_TYPE, EnergyType.FE);
            }else if(type == MekanismKeyType.TYPE){
                builder.addOutputSlot().addIngredient(MekanismJEIHelper.INSTANCE.getChemicalStackHelper().getIngredientType(),((MekanismKey) key).getStack());
            }else{
                addTyped(builder,key);
            }
        }

        @SuppressWarnings("unchecked")
        private <T> void addTyped(IRecipeLayoutBuilder builder, AEKey key){
            Pair<IIngredientType<?>, Function<AEKey, ?>> pair = JeiInfoRegistryEvent.MAP.getOrDefault(key.getType(),null);
            if(pair != null){
                IIngredientType<T> type = (IIngredientType<T>) pair.getLeft();
                Function<AEKey, T> converter = (Function<AEKey, T>) pair.getRight();
                builder.addOutputSlot().addIngredient(type,converter.apply(key));
            }
        }
    }
}