package flybuff.mythicmobs;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitEntity;
import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
import io.lumine.xikage.mythicmobs.skills.Skill;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
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
                Optional<Skill> optionalSkill = MythicMobs.inst().getSkillManager().getSkill(param);
                if (!optionalSkill.isPresent()) {
                    throw new IllegalArgumentException("无法从MythicMobs中获取技能: " + param + " 请检查是否填写错误");
                }
                Skill skill = optionalSkill.get();
                SkillMetadata metadata = new SkillMetadata(SkillTrigger.ATTACK, new GenericCaster(new BukkitEntity(entityDamageByEntityEvent.getEntity())), new BukkitEntity(player));
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
        return "FlyBuffMythicMobs4";
    }
}
