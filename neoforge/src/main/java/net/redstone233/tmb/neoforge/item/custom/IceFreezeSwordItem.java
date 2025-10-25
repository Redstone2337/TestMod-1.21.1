package net.redstone233.tmb.neoforge.item.custom;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class IceFreezeSwordItem extends SwordItem {
    public IceFreezeSwordItem(Tier tier, Properties properties) {
        super(tier, properties
                .attributes(SwordItem.createAttributes(
                        Tiers.NETHERITE,
                        500,
                        -4.7f
                ))
        );
    }
}
