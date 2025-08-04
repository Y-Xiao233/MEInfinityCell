package net.yxiao233.meinfinitycell.common.items;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.items.AEBaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class InfinitiesCell extends AEBaseItem implements ICellWorkbenchItem {
    protected Component name;
    protected final KeyList keys;

    public InfinitiesCell(KeyList keys,Component name){
        super(new Properties().stacksTo(1));
        this.keys = keys;
        this.name = name;
    }

    public void setName(Component name){
        this.name = name;
    }

    public Component getName(){
        return this.name;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {

        return Component.translatable("item.meinfinitycell.infinity_cell",getName());
    }

    @Override
    public void addToMainCreativeTab(CreativeModeTab.Output output) {
        output.accept(this);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag isAdvanced) {
        list.add(Component.translatable("tooltip.meinfinitycell.infinity").withStyle(ChatFormatting.GREEN));
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        return this.keys.getTooltipImage(stack);
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack itemStack) {
        return FuzzyMode.IGNORE_ALL;
    }

    @Override
    public void setFuzzyMode(ItemStack itemStack, FuzzyMode fuzzyMode) {

    }

    public KeyList getKeys(){
        return keys;
    }
}
