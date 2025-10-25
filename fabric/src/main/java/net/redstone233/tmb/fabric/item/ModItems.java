package net.redstone233.tmb.fabric.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.redstone233.tmb.annotation.Fabric;
import net.redstone233.tmb.core.TestMod;
import net.redstone233.tmb.fabric.item.custom.BlazingFlameSwordItem;
import net.redstone233.tmb.fabric.item.custom.IceFreezeSwordItem;

@Fabric
public class ModItems {

    public static final Item BLAZING_FLAME_SWORD = register(
            "blazing_flame_sword",
            new BlazingFlameSwordItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .maxDamage(3000000)
            ));

    public static final Item ICE_FREEZE_SWORD = register(
            "ice_freeze_sword",
            new IceFreezeSwordItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .maxDamage(3000000)
            ));



    public static void registerItems() {
        TestMod.LOGGER.info("Registering Mod Items for " + TestMod.MOD_ID);
    }

    public static Item register(String id, Item item) {
        return Items.register(Identifier.of(TestMod.MOD_ID,id), item);
    }


}
