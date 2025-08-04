package net.yxiao233.meinfinitycell.common.helper;

import me.ramidzkh.mekae2.ae2.MekanismKey;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MekanismKeyHelper {
    public static final String GAS = "gas";
    public static final String INFUSE_TYPE = "infuse_type";
    public static final String SLURRY = "slurry";
    public static final String PIGMENT = "pigment";
    private static final List<String> TYPES = List.of(GAS,INFUSE_TYPE,SLURRY,PIGMENT);
    public static MekanismKey get(ResourceLocation id, String type){
        return MekanismKey.of(getStack((Chemical<?>) byType(type).getValue(id)));
    }

    public static MekanismKey get(String id, String type){
        return get(new ResourceLocation(id),type);
    }

    public static MekanismKey get(ResourceLocation id){
        return MekanismKey.of(getStack(find(id)));
    }

    public static MekanismKey get(String id){
        return get(new ResourceLocation(id));
    }

    private static IForgeRegistry<?> byType(String type){
        return switch (type){
            case GAS -> MekanismAPI.gasRegistry();
            case INFUSE_TYPE -> MekanismAPI.infuseTypeRegistry();
            case SLURRY -> MekanismAPI.slurryRegistry();
            case PIGMENT -> MekanismAPI.pigmentRegistry();
            default -> throw new IllegalArgumentException("Type does not exist");
        };
    }

    private static Chemical<?> find(ResourceLocation id){
        AtomicReference<Chemical<?>> chemical = new AtomicReference<>(null);
        TYPES.forEach(type ->{
            if(byType(type).containsKey(id)){
                chemical.set((Chemical<?>) byType(type).getValue(id));
            }
        });

        if(chemical.get() != null){
            return chemical.get();
        }else{
            throw new IllegalArgumentException(id + " is not chemicals");
        }
    }

    private static ChemicalStack<?> getStack(Chemical<?> chemical){
        return chemical.getStack(256);
    }
}
