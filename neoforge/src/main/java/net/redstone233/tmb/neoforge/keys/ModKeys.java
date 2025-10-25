package net.redstone233.tmb.neoforge.keys;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.redstone233.tmb.annotation.NeoForged;
import net.redstone233.tmb.core.TestMod;
import org.lwjgl.glfw.GLFW;

@NeoForged
@EventBusSubscriber(modid = TestMod.MOD_ID, value = Dist.CLIENT)
public class ModKeys {
    private static final String USE_ABILITY_NAME = "key.tmb.use_ability";
    private static final String KEY_CATEGORY = "category.tmb";


    public static KeyMapping USE_ABILITY_KEY = new KeyMapping(
            USE_ABILITY_NAME,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            KEY_CATEGORY
    );

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(USE_ABILITY_KEY);
    }


    public static boolean isUseAbilityKeyPressed() {
        return USE_ABILITY_KEY.isDown();
    }

    // 可选：添加检查按键是否刚刚被按下（而不是持续按下）的方法


    public static boolean wasUseAbilityKeyPressed() {
        return USE_ABILITY_KEY.consumeClick();
    }

    public static void init() {
        TestMod.LOGGER.info("命令注册成功！");
    }
}
