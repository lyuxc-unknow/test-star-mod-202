package me.lyuxc.tp;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DataComponent {
    private static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(TestStarTP.MOD_ID);

    public static final Supplier<DataComponentType<Double>> X = REGISTRAR.registerComponentType("player_x",
            builder -> builder.persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE).cacheEncoding()
    );

    public static final Supplier<DataComponentType<Double>> Y = REGISTRAR.registerComponentType("player_y",
            builder -> builder.persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE).cacheEncoding()
    );

    public static final Supplier<DataComponentType<Double>> Z = REGISTRAR.registerComponentType("player_z",
            builder -> builder.persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE).cacheEncoding()
    );

    public static void init(IEventBus modEventBus) {
        REGISTRAR.register(modEventBus);
    }
}
