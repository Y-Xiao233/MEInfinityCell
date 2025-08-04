package net.yxiao233.meinfinitycell.common.items;

import appeng.api.config.FuzzyMode;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.items.AEBaseItem;
import appeng.items.storage.StorageCellTooltipComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class InfinityCell extends AEBaseItem implements ICellWorkbenchItem {
    protected final Supplier<AEKey> key;
    public InfinityCell(){
        super(new Properties().stacksTo(1));
        this.key = () -> AEItemKey.of(Items.COBBLESTONE);
    }
    public InfinityCell(Properties properties) {
        super(properties);
        this.key = () -> AEItemKey.of(Items.COBBLESTONE);
    }

    public InfinityCell(Supplier<AEKey> key, Properties properties){
        super(properties);
        this.key = key;
    }

    public InfinityCell(Supplier<AEKey> key){
        super(new Properties().stacksTo(1));
        this.key = key;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Component.translatable("item.meinfinitycell.infinity_cell",this.key.get().getDisplayName());
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
        var content = Collections.singletonList(new GenericStack(this.key.get(), Meinfinitycell.getMax(this.key.get())));
        return Optional.of(new StorageCellTooltipComponent(List.of(), content, false, true));
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack itemStack) {
        return FuzzyMode.IGNORE_ALL;
    }

    @Override
    public void setFuzzyMode(ItemStack itemStack, FuzzyMode fuzzyMode) {

    }
    public Supplier<AEKey> getKey() {
        return key;
    }
}
