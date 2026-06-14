package net.yxiao233.meinfinitycell.common.items;

import appeng.api.config.FuzzyMode;
import appeng.api.stacks.AEKey;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.items.AEBaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class InfinitiesCell extends AEBaseItem implements ICellWorkbenchItem {
    protected Component name;
    protected final KeyList keys;
    public static final ArrayList<InfinitiesCell> LIST = new ArrayList<>();

    public InfinitiesCell(Properties properties, KeyList keys, Component name, boolean needTip){
        super(properties.stacksTo(1));
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
    public void addToMainCreativeTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltip.meinfinitycell.infinity").withStyle(ChatFormatting.GREEN));
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
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if(level.isClientSide()){
            if(player.isShiftKeyDown() && this.getKeys() != null && !this.getKeys().isEmpty()){
                player.sendSystemMessage(this.getName(this.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD).append(Component.translatable("tooltip.meinfinitycell.show_context").withStyle(ChatFormatting.WHITE)));
                HashMap<Component,List<Component>> map = new HashMap<>();
                this.getKeys().getList().forEach((key ->{
                    AEKey aeKey = key.get();
                    if(aeKey != null){
                        Component type = aeKey.getType().getDescription().copy().withStyle(ChatFormatting.AQUA);
                        Component component = aeKey.getDisplayName().copy().withStyle(ChatFormatting.GREEN);
                        map.computeIfAbsent(type,t -> new CopyOnWriteArrayList<>()).add(component);
                    }
                }));
                map.forEach((type, list) ->{
                    player.sendSystemMessage(type.copy().append(Component.translatable("tooltip.meinfinitycell.type")).append(":"));
                    list.forEach(player::sendSystemMessage);
                    player.sendSystemMessage(Component.literal(""));
                });
            }
            return InteractionResult.SUCCESS;
        }else{
            return super.use(level, player, hand);
        }
    }
}
