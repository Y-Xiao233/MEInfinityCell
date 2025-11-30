package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEKey;
import com.glodblock.github.appflux.common.me.key.FluxKey;
import com.glodblock.github.appflux.common.me.key.type.EnergyType;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class FluxKeyHelper {
    public static Supplier<AEKey> of(EnergyType type){
        return () -> FluxKey.of(type);
    }
}
