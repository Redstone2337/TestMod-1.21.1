package net.redstone233.tmb.neoforge.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.redstone233.tmb.neoforge.keys.ModKeys;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlazingFlameSwordItem extends SwordItem {
    public BlazingFlameSwordItem(Tier tier, Properties properties) {
        super(tier, properties
                .attributes(SwordItem.createAttributes(
                        Tiers.NETHERITE,
                        500,
                        -4.7f
                ))
        );
    }

    @Override
    public void postHurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        if (target instanceof LivingEntity livingEntity && attacker instanceof Player player) {
            livingEntity.isOnFire();
            livingEntity.fireImmune();
            livingEntity.setRemainingFireTicks(2400);
            livingEntity.igniteForSeconds(120.0f);
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1200,4,false,false,false));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1200,4,false,false,false));
//            livingEntity.setOnFire(true);
//            livingEntity.setOnFireForTicks(2400);
//            livingEntity.setOnFireFor(120.0f);
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,1200,4,false,false,false));
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,1200,4,false,false,false));
        }
        super.postHurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("§c§l火焰之剑").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
        tooltipComponents.add(Component.translatable("tooltip.ability_sword.display1").withStyle(ChatFormatting.WHITE)
                .append(Component.translatable("key.use_ability.item",Component.translatable(ModKeys.USE_ABILITY_KEY.getTranslatedKeyMessage().getString())
                                .withStyle(ChatFormatting.GOLD))
                        .append(Component.translatable("tooltip.ability_sword.display2").withStyle(ChatFormatting.WHITE))
                ));
        tooltipComponents.add(Component.literal("§7§l火焰之剑，拥有火焰之威，").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("专属定制武器").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));
        tooltipComponents.add(Component.literal("§7§l能够点燃敌人，并给予使用者速度和防火效果。\n\n").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("[稀有度]").append(Component.literal("传说").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (player instanceof Player playerEntity && level.isClientSide) {
            playerEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1200,4,false,false,false));
            playerEntity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 300,4,false,true,true));
            return InteractionResultHolder.success(playerEntity.getMainHandItem());
        } else {
            player.displayClientMessage(Component.literal("似乎没有正常运行。").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return InteractionResultHolder.fail(player.getMainHandItem());
        }
    }
}
