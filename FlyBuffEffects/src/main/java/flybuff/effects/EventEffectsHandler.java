package flybuff.effects;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.fastmcmirror.flybuff.api.BuffListenerHandler;

import java.util.Arrays;

public class EventEffectsHandler implements BuffListenerHandler {
    @Override
    public ItemStack handle(Event event, String buff_name, ItemStack item, String param) {
        Entity entity = null;
        if (event instanceof EntityDamageByEntityEvent){
            entity = ((EntityDamageByEntityEvent) event).getDamager();
        } else if (event instanceof EntityEvent){
            entity = ((EntityEvent) event).getEntity();
        } else if (event instanceof PlayerEvent){
            entity = ((PlayerEvent) event).getPlayer();
        } else {
            return item;
        }
        String[] args = param.split(" ");
        if (args.length!=3){
            throw new IllegalArgumentException("[FlyBuffEffects] 错误的格式调用格式 请检查官方介绍");
        }
        if (Arrays.stream(PotionEffectType.values()).noneMatch(type -> type.toString().equals(args[0].toUpperCase()))){
            throw new IllegalArgumentException("[FlyBuffEffects] 未知的药水类型: " + args[0] + " 原因: 输入错误或当前版本尚不支持");
        }
        String time;
        if (entity instanceof Player){
            time = PlaceholderAPI.setPlaceholders((Player) entity, args[2]);
        } else {
            time = args[2];
        }
        String level;
        if (entity instanceof Player){
            level = PlaceholderAPI.setPlaceholders((Player) entity, args[1]);
        } else {
            level = args[1];
        }
        if (!(entity instanceof LivingEntity)) return item;
        PotionEffect effect = new PotionEffect(PotionEffectType.getByName(args[0]),
                Integer.parseInt(MathEngine.format(time)),
                Integer.parseInt(MathEngine.format(level)));
        for (PotionEffect active : ((LivingEntity) entity).getActivePotionEffects()){
            if (effect.getType().equals(active.getType())){
                if (active.getAmplifier() > effect.getAmplifier()) return item;
            }
        }
        ((LivingEntity) entity).addPotionEffect(effect);
        return item;
    }

    @Override
    public String getIdentifier() {
        return "effect";
    }

    @Override
    public String getAuthor() {
        return "FlyProject";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getName() {
        return "FlyBuffEffects";
    }
}
