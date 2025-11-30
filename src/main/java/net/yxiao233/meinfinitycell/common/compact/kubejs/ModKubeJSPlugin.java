package net.yxiao233.meinfinitycell.common.compact.kubejs;

import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.FluxKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.AEKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.MekanismKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.items.InfinitiesCellItemBuilder;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import net.yxiao233.meinfinitycell.common.utils.LoadList;
import net.yxiao233.meinfinitycell.common.compact.kubejs.items.InfinityCellItemBuilder;

public class ModKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerBindings(BindingRegistry event) {
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
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(Registries.ITEM, reg ->{
            reg.add(getFromString("meinfinitycell:infinity_cell"), InfinityCellItemBuilder.class, InfinityCellItemBuilder::new);
            reg.add(getFromString("meinfinitycell:infinities_cell"), InfinitiesCellItemBuilder.class,InfinitiesCellItemBuilder::new);
        });
    }

    private ResourceLocation getFromString(String rl){
        String nameSpace = rl.substring(0,rl.indexOf(":"));
        String location = rl.substring(rl.indexOf(":") + 1);
        return ResourceLocation.fromNamespaceAndPath(nameSpace,location);
    }
}
