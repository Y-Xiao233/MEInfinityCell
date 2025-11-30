package net.yxiao233.meinfinitycell.client;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import me.ramidzkh.mekae2.ae2.MekanismKeyType;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tier.ChemicalTankTier;
import mekanism.common.util.ChemicalUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Meinfinitycell.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemTooltipEvent {
    @SubscribeEvent
    public static void onAddInfinityItemsTip(net.minecraftforge.event.entity.player.ItemTooltipEvent event){
        ArrayList<InfinitiesCell> list = InfinitiesCell.LIST;
        if(!list.isEmpty()){
            list.forEach(cell -> cell.getKeys().getList().forEach(key ->{
                AEKey aeKey = key.get();
                if(aeKey != null){
                    if(aeKey.getType().equals(AEKeyType.items())){
                        AEItemKey itemKey = (AEItemKey) aeKey;
                        if(ItemStack.isSameItemSameTags(event.getItemStack(),itemKey.toStack())){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }else if(aeKey.getType().equals(AEKeyType.fluids())){
                        AEFluidKey fluidKey = (AEFluidKey) aeKey;
                        Item bucket = fluidKey.toStack(1000).getFluid().getBucket();
                        if(event.getItemStack().is(bucket)){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.fluid.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }else if(aeKey.getType().equals(MekanismKeyType.TYPE)){
                        MekanismKey mekanismKey = (MekanismKey) aeKey;
                        ItemStack creativeChemicalTank = ChemicalUtil.getFilledVariant(MekanismBlocks.CREATIVE_CHEMICAL_TANK.getItemStack(), ChemicalTankTier.CREATIVE.getStorage(), mekanismKey.getStack().getRaw());
                        if(ItemStack.isSameItemSameTags(event.getItemStack(),creativeChemicalTank)){
                            event.getToolTip().add(Component.translatable("tooltip.meinfinitycell.chemical.infinity_in",cell.getName(cell.getDefaultInstance()).copy().withStyle(ChatFormatting.GOLD)));
                        }
                    }
                }
            }));
        }
    }
}
