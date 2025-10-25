package net.redstone233.tmb.fabric.keys;


import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.redstone233.tmb.annotation.Fabric;
import net.redstone233.tmb.core.TestMod;
import org.lwjgl.glfw.GLFW;

@Fabric
public class ModKeys {
    public static KeyBinding USE_ABILITY_KEY = new KeyBinding(
            "key.tmb.use_ability",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_CONTROL,
            "category.tmb"
    );

    public static void register() {
        KeyBindingHelper.registerKeyBinding(USE_ABILITY_KEY);

        TestMod.LOGGER.info("注册按键绑定成功");
    }



    public static boolean isUseAbilityKeyPressed() {
        return USE_ABILITY_KEY.isPressed();
    }
}
