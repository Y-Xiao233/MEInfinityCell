package net.yxiao233.meinfinitycell.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yxiao233.meinfinitycell.Meinfinitycell;

@SuppressWarnings("unused")
public class MICCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Meinfinitycell.MODID);
    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> MIC_TAB = CREATIVE_MODE_TAB.register("mic_tab", () -> CreativeModeTab.builder()
            .icon(() -> MICItems.INFINITY_COBBLESTONE_CELL.get().getDefaultInstance())
            .displayItems((parameters, output) -> MICItems.ITEMS.getEntries().forEach((reg) -> output.accept(reg.get())))
            .title(Component.translatable("itemGroup.meinfinitycell"))
            .build()
    );
}
