package flybuff.messages;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.fastmcmirror.flybuff.api.BuffTickHandler;
import org.fastmcmirror.flybuff.api.ItemSlot;
import org.fastmcmirror.flybuff.utils.Color;

public class TickMessageHandler implements BuffTickHandler {
    @Override
    public ItemStack handle(Player player, ItemStack item, String buff_name, ItemSlot slot, String param) {
        player.sendMessage(Color.color(param));
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
