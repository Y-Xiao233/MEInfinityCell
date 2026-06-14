package net.yxiao233.meinfinitycell.common.compact.kubejs.items;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import org.jspecify.annotations.NonNull;

@SuppressWarnings("unused")
public class InfinitiesCellItemBuilder extends ItemBuilder {
    protected Component name = null;
    protected KeyList keys;
    protected boolean needTip = true;
    public InfinitiesCellItemBuilder(Identifier i) {
        super(i);
    }

    public InfinitiesCellItemBuilder setName(Component name){
        this.name = name;
        return this;
    }

    public InfinitiesCellItemBuilder setKeys(KeyList keys){
        this.keys = keys;
        return this;
    }

    public InfinitiesCellItemBuilder setNeedTip(boolean needTip){
        this.needTip = needTip;
        return this;
    }
    @Override
    public @NonNull Item createObject() {
        return new InfinitiesCell(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,id)),keys,name,needTip);
    }
}
