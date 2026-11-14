package net.yxiao233.meinfinitycell.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class MEInfinityCellEvents{
    public static final EventGroup GROUP = EventGroup.of("MEInfinityCellEvents");
    public static final EventHandler JEI = GROUP.startup("jei",() -> JeiInfoRegistryEvent.class);
}
