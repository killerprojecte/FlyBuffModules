package flybuff.messages;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.fastmcmirror.flybuff.api.BuffCallback;
import org.fastmcmirror.flybuff.api.BuffInstallHandler;
import org.fastmcmirror.flybuff.utils.Color;

public class InstallMessageHandler implements BuffInstallHandler {
    @Override
    public BuffCallback checkCondition(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        return new BuffCallback();
    }

    @Override
    public ItemStack handle(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        player.sendMessage(Color.color(param));
        return target;
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
