package net.redstone233.tmb.neoforge.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.redstone233.tmb.neoforge.item.ModItems;
import net.redstone233.tmb.neoforge.keys.ModKeys;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        if (target instanceof LivingEntity livingEntity && attacker instanceof Player player) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1200,255,false,false,false));
            // 为玩家添加速度提升效果，持续1200刻（60秒），等级为4
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1200,4,false,false,false));
            // 为玩家添加火焰抗性效果，持续1200刻（60秒），等级为4
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1200,4,false,false,false));
            // 以下是注释掉的代码，功能与上面类似但使用了不同的API
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity interactionTarget, @NotNull InteractionHand usedHand) {
        if (player instanceof Player playerEntity && interactionTarget instanceof LivingEntity livingEntity) {
            // 检查是否按下了能力使用键
            if (ModKeys.isUseAbilityKeyPressed()) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1200,255,false,false,false));
            }
            playerEntity.addEffect(new MobEffectInstance(MobEffects.JUMP,3600,4));
            playerEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST,1800,6));
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        /*
        tooltip.add(Text.literal("§b§l冰霜之剑").formatted(Formatting.YELLOW, Formatting.BOLD));
        tooltip.add(Text.translatable("tooltip.ability_sword.display1").formatted(Formatting.WHITE)
                .append(Text.translatable("key.use_ability.item",Text.keybind(ModKeys.USE_ABILITY_KEY.getBoundKeyLocalizedText().getString())
                                .formatted(Formatting.GOLD))
                        .append(Text.translatable("tooltip.ability_sword.display2").formatted(Formatting.WHITE))
                ));
        tooltip.add(Text.literal("§7§l冰霜之剑，拥有冰霜之威，").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("专属定制武器").formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD));
        tooltip.add(Text.literal("§7§l能够冻结敌人，并给予使用者速度和防火效果。\n\n").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("[稀有度]").append(Text.literal("传说").formatted(Formatting.GOLD,Formatting.BOLD)))
         */
        tooltipComponents.add(Component.literal("§b§l冰霜之剑").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
        tooltipComponents.add(Component.translatable("tooltip.ability_sword.display1").withStyle(ChatFormatting.WHITE)
                .append(Component.keybind(ModKeys.USE_ABILITY_KEY.getDisplayName().getString())
                        .withStyle(ChatFormatting.GOLD))
                .append(Component.translatable("tooltip.ability_sword.display2").withStyle(ChatFormatting.WHITE))
                );
        tooltipComponents.add(Component.literal("§7§l冰霜之剑，拥有冰霜之威，").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("专属定制武器").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));
        tooltipComponents.add(Component.literal("§7§l能够冻结敌人，并给予使用者速度和防火效果。\n\n").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("[稀有度]").append(Component.literal("传说").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        // 检查玩家是否为客户端玩家
        if (player instanceof Player playerEntity && level.isClientSide) {
            // 添加抗火效果，持续1200 ticks（60秒），等级4
            playerEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1200,4,false,false,false));
            // 添加饱和度效果，持续300 ticks（15秒），等级4，显示图标和粒子
            playerEntity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 300,4,false,true,true));
            // 返回成功交互结果，并返回主手物品
            return InteractionResultHolder.success(playerEntity.getMainHandItem());
        } else {
            // 如果不是客户端或玩家不正确，显示错误消息
            player.displayClientMessage(Component.literal("似乎没有正常运行。").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            // 返回失败交互结果，并返回当前物品
            return InteractionResultHolder.fail(player.getMainHandItem());
        }
    }

    @Override
    public void postHurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        // 使物品在主手受到1点伤害
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        // 使物品在副手受到1点伤害
        stack.hurtAndBreak(1, attacker, EquipmentSlot.OFFHAND);
        // 调用父类的postHurtEnemy方法，执行默认的伤害后处理逻辑
        super.postHurtEnemy(stack, target, attacker);
    }
}
