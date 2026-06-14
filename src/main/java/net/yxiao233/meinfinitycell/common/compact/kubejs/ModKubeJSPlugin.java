package net.yxiao233.meinfinitycell.common.compact.kubejs;

import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.AEKeyHelper;
import net.yxiao233.meinfinitycell.common.compact.kubejs.items.InfinitiesCellItemBuilder;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import net.yxiao233.meinfinitycell.common.compact.kubejs.items.InfinityCellItemBuilder;

public class ModKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerBindings(BindingRegistry event) {
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

    private Identifier getFromString(String rl){
        String nameSpace = rl.substring(0,rl.indexOf(":"));
        String location = rl.substring(rl.indexOf(":") + 1);
        return Identifier.fromNamespaceAndPath(nameSpace,location);
    }
}
