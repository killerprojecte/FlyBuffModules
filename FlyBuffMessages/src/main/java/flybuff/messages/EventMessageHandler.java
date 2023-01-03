package flybuff.messages;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.fastmcmirror.flybuff.api.BuffListenerHandler;
import org.fastmcmirror.flybuff.utils.Color;

public class EventMessageHandler implements BuffListenerHandler {
    @Override
    public ItemStack handle(Event event, String buff_name, ItemStack item, String param) {
        if (event instanceof PlayerEvent){
            PlayerEvent playerEvent = (PlayerEvent) event;
            playerEvent.getPlayer().sendMessage(Color.color(param));
        } else if (event instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) event;
            if (entityDamageByEntityEvent.getDamager() instanceof Player){
                entityDamageByEntityEvent.getDamager().sendMessage(Color.color(param));
            }
        } else if (event instanceof EntityEvent){
            EntityEvent entityEvent = (EntityEvent) event;
            if (entityEvent.getEntity() instanceof Player){
                entityEvent.getEntity().sendMessage(Color.color(param));
            }
        }
        return item;
    }

    @Override
    public String getIdentifier() {
        return "msg";
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
        return "FlyBuffMessages";
    }
}
