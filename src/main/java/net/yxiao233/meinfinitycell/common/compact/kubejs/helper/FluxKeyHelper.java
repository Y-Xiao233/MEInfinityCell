package net.yxiao233.meinfinitycell.common.helper;

import com.glodblock.github.appflux.common.me.key.FluxKey;
import com.glodblock.github.appflux.common.me.key.type.EnergyType;

public class FluxKeyHelper {
    public static FluxKey get(EnergyType type){
        return FluxKey.of(type);
    }
}
