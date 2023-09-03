package cdk.windows;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseSimple;

public class ListenerWindow implements Listener {

    @EventHandler
    public void onFormResponse(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID();
        if (event.wasClosed()) {
            return;
        }
        if (id == 0x32e2d) {
            FormResponseSimple response = (FormResponseSimple) event.getResponse();
            int clickedButtonId = response.getClickedButtonId();
            switch (clickedButtonId) {
                case 0:
                    CreateWindow.sendRedeemMenu(player);
                    break;
                case 1:
                    CreateWindow.sendCreateMenu(player);
                    break;
                case 2:
                    CreateWindow.sendSetMenu(player);
                    break;
                case 3:
                    CreateWindow.sendDelMenu(player);
                    break;
                case 4:
                    player.getServer().dispatchCommand(player, "cdk get");
                    break;
                default:
                    break;
            }
        }
        if (id == 0x82c85) {
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            player.getServer().dispatchCommand(player,"cdk "+input);
        }
        if (id == 0x716ed) {
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            player.getServer().dispatchCommand(player,"cdk create "+input);
        }
        if (id == 0x3718e) {
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            String count = response.getInputResponse(1);
            player.getServer().dispatchCommand(player,"cdk set "+input+" "+count);
        }
        if (id == 0x18d22) {
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            player.getServer().dispatchCommand(player,"cdk del "+input);
        }
    }
}