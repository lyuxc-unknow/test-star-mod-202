package me.lyuxc.tp;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class Attachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TestStarTP.MOD_ID);
    public static final Supplier<AttachmentType<Boolean>> IS_FIRST_JOIN_GAME = ATTACHMENT_TYPES.register("is_first_join_game", () -> AttachmentType.builder(() -> false)
            .serialize(Codec.BOOL)
            .copyOnDeath()
            .build());

    public static void init(IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);
    }
}
