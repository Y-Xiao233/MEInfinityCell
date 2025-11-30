package net.yxiao233.meinfinitycell.common.compact.jei;

import appeng.client.gui.style.Blitter;
import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppFluxTypes {
    public static final IIngredientType<EnergyType> ENERGY_TYPE = () -> EnergyType.class;
    public static class EnergyStackHelper implements IIngredientHelper<EnergyType>{

        @Override
        public @NotNull IIngredientType<EnergyType> getIngredientType() {
            return ENERGY_TYPE;
        }

        @Override
        public @NotNull String getDisplayName(EnergyType energyType) {
            return energyType.from();
        }

        @Override
        public @NotNull String getUniqueId(@NotNull EnergyType energyType, @NotNull UidContext uidContext) {
            return energyType.from() + ":energy";
        }

        @Override
        public @NotNull ResourceLocation getResourceLocation(@NotNull EnergyType energyType) {
            return new ResourceLocation(energyType.from(),"energy");
        }

        @Override
        public @NotNull EnergyType copyIngredient(@NotNull EnergyType energyType) {
            return energyType;
        }

        @Override
        public @NotNull String getErrorInfo(@Nullable EnergyType energyType) {
            return null;
        }
    }

    public static class EnergyStackRenderer implements IIngredientRenderer<EnergyType>{

        @Override
        public void render(@NotNull GuiGraphics guiGraphics, @NotNull EnergyType type) {
            Blitter.sprite(Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(type.getIcon())).blending(false).dest(0,0, 16, 16).blit(guiGraphics);
        }

        @Override
        @SuppressWarnings("removal")
        public @NotNull List<Component> getTooltip(@NotNull EnergyType energyType, @NotNull TooltipFlag tooltipFlag) {
            List<Component> tips = new ArrayList<>();
            tips.add(Component.literal("FE"));
            return tips;
        }
    }
}
