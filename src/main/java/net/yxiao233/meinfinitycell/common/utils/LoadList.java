package net.yxiao233.meinfinitycell.common.utils;

import net.neoforged.fml.ModList;

public class LoadList {
    public static final boolean MEKANISM = ModList.get().isLoaded("mekanism");
    public static final boolean APPLIED_MEKANISM = ModList.get().isLoaded("appmek");
    public static final boolean APPLIED_FLUX = ModList.get().isLoaded("appflux");
}
