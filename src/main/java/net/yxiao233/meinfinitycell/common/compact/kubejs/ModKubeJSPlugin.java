package net.yxiao233.meinfinitycell.common.compact.kubejs;

import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.FluxKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.AEKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.MekanismKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.items.InfinitiesCellItemBuilder;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import net.yxiao233.meinfinitycell.common.utils.LoadList;
import net.yxiao233.meinfinitycell.common.compact.kubejs.items.InfinityCellItemBuilder;

public class ModKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void registerBindings(BindingsEvent event) {
        if(LoadList.MEKANISM && LoadList.APPLIED_MEKANISM){
            event.add("MekanismKeyHelper", MekanismKeyHelper.class);
        }
        if(LoadList.APPLIED_FLUX){
            event.add("FluxKeyHelper", FluxKeyHelper.class);
            event.add("EnergyType", EnergyType.class);
        }
        event.add("KeyList", KeyList.class);
        event.add("AEKeyHelper", AEKeyHelper.class);
    }

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("meinfinitycell:infinity_cell", InfinityCellItemBuilder.class, InfinityCellItemBuilder::new);
        RegistryInfo.ITEM.addType("meinfinitycell:infinities_cell", InfinitiesCellItemBuilder.class,InfinitiesCellItemBuilder::new);
    }
}
