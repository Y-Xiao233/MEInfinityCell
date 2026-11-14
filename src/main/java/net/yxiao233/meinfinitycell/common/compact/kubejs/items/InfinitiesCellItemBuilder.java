package net.yxiao233.meinfinitycell.common.compact.kubejs.items;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.yxiao233.meinfinitycell.common.compact.kubejs.helper.AEKeyHelper;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.utils.KeyList;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class InfinitiesCellItemBuilder extends ItemBuilder {
    protected Component name = null;
    protected Supplier<KeyList> keys;
    protected boolean needTip = true;
    public InfinitiesCellItemBuilder(ResourceLocation i) {
        super(i);
    }

    public InfinitiesCellItemBuilder setName(Component name){
        this.name = name;
        return this;
    }

    public InfinitiesCellItemBuilder setKeys(KeyList keys){
        this.keys = () -> keys;
        return this;
    }

    public InfinitiesCellItemBuilder byMod(String modid){
        return setKeys(AEKeyHelper.byMod(modid));
    }

    public InfinitiesCellItemBuilder byTag(TagKey<?> tagKey){
        this.keys = AEKeyHelper.byTag(tagKey);
        return this;
    }

    public InfinitiesCellItemBuilder byTags(TagKey<?>... tagKeys){
        this.keys = (AEKeyHelper.byTags(tagKeys));
        return this;
    }

    public InfinitiesCellItemBuilder setNeedTip(boolean needTip){
        this.needTip = needTip;
        return this;
    }
    @Override
    public Item createObject() {
        return new InfinitiesCell(keys,name,needTip);
    }
}
