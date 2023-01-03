package flybuff.mythicmobs;

import io.lumine.mythic.api.mobs.GenericCaster;
import io.lumine.mythic.api.skills.Skill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillTrigger;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.adapters.BukkitEntity;
import io.lumine.mythic.core.skills.SkillMetadataImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.fastmcmirror.flybuff.api.BuffListenerHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AttackSkillHandler implements BuffListenerHandler {
    @Override
    public ItemStack handle(Event event, String buff_name, ItemStack item, String param) {
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) event;
            if (entityDamageByEntityEvent.getDamager() instanceof Player) {
                Player player = (Player) entityDamageByEntityEvent.getDamager();
                Optional<Skill> optionalSkill = MythicBukkit.inst().getSkillManager().getSkill(param);
                if (!optionalSkill.isPresent()) {
                    throw new IllegalArgumentException("无法从MythicMobs中获取技能: " + param + " 请检查是否填写错误");
                }
                Skill skill = optionalSkill.get();
                SkillMetadata metadata = new SkillMetadataImpl(SkillTrigger.get("ATTACK"), new GenericCaster(new BukkitEntity(entityDamageByEntityEvent.getEntity())), new BukkitEntity(player));
                skill.execute(metadata);
            }
        }
        return item;
    }

    @Override
    public String getIdentifier() {
        return "mythicmobs";
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
    public List<String> getDependencies() {
        return Arrays.asList("MythicMobs");
    }

    @Override
    public String getName() {
        return "FlyBuffMythicMobs5";
    }
}
