package net.yxiao233.meinfinitycell.common.items;

import appeng.api.config.FuzzyMode;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.items.AEBaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InfinitiesCell extends AEBaseItem implements ICellWorkbenchItem {
    protected Component name;
    protected final KeyList keys;
    public static final ArrayList<InfinitiesCell> LIST = new ArrayList<>();

    public InfinitiesCell(KeyList keys,Component name){
        this(keys,name,true);
    }

    public InfinitiesCell(KeyList keys,Component name,boolean needTip){
        super(new Properties().stacksTo(1));
        this.keys = keys;
        this.name = name;
        if(needTip){
            LIST.add(this);
        }
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

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(level.isClientSide()){
            if(player.isShiftKeyDown() && this.getKeys() != null && !this.getKeys().isEmpty()){
                player.sendSystemMessage(this.getName(this.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD).append(Component.translatable("tooltip.meinfinitycell.show_context").withStyle(ChatFormatting.WHITE)));
                this.getKeys().getList().forEach((key ->{
                    AEKey aeKey = key.get();
                    if(aeKey != null){
                        player.sendSystemMessage(aeKey.getDisplayName().copy().withStyle(ChatFormatting.GREEN));
                    }
                }));
            }
            return InteractionResultHolder.success(this.getDefaultInstance());
        }else{
            return super.use(level, player, hand);
        }
    }
}
