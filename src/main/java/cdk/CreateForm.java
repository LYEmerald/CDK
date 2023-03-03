package cdk;

import cdk.commands.CdkCommand;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

public class CreateForm implements Listener {

    public static int DefaultMenu = 0x20230228;
    public static int AdminMenu = 0x20230229;
    public static int RedeemMenu = 0x20230301;
    public static int CreateCdk = 0x20230302;
    public static int SetCdk = 0x20230303;

    public static void DefaultMenu(Player player){
        FormWindowSimple simple = new FormWindowSimple("CDK","");
        simple.addButton(new ElementButton("兑换",new ElementButtonImageData("path","textures/ui/invite_base")));
        player.showFormWindow(simple,DefaultMenu);
    }

    public static void AdminMenu(Player player){
        FormWindowSimple simple = new FormWindowSimple("CDK","");
        simple.addButton(new ElementButton("兑换",new ElementButtonImageData("path","textures/ui/invite_base")));
        simple.addButton(new ElementButton("创建",new ElementButtonImageData("path","textures/ui/invite_base")));
        simple.addButton(new ElementButton("设置",new ElementButtonImageData("path","textures/ui/invite_base")));


        player.showFormWindow(simple,AdminMenu);
    }

    public static void RedeemMenu(Player player){
        FormWindowCustom custom = new FormWindowCustom("CDK--兑换");
        custom.addElement(new ElementInput("","请输入CDK"));
        player.showFormWindow(custom,RedeemMenu);
    }

    public static void CreateCdk(Player player){
        FormWindowCustom custom = new FormWindowCustom("CDK--创建");
        custom.addElement(new ElementInput("","请输入奖励代号"));
        player.showFormWindow(custom,CreateCdk);
    }

    public static void SetCdk(Player player){
        FormWindowCustom custom = new FormWindowCustom("CDK--添加");
        custom.addElement(new ElementInput("","请输入奖励代号"));
        custom.addElement(new ElementInput("","请输入生成CDK的数量"));
        player.showFormWindow(custom,SetCdk);
    }

    @EventHandler
    public void onFormResponse(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID(); //这将返回一个form的唯一标识`id`
        if(event.wasClosed()){
            return;
        }
        if(id == 0x20230228){
            FormResponseSimple response = (FormResponseSimple) event.getResponse();
            int clickedButtonId = response.getClickedButtonId();
            switch (clickedButtonId) {
                case 0:
                    CreateForm.RedeemMenu(player);
                    break;
                default:
                    break;
            }
        }
        if(id == 0x20230229){
            FormResponseSimple response = (FormResponseSimple) event.getResponse();
            int clickedButtonId = response.getClickedButtonId();
            switch (clickedButtonId) {
                case 0:
                    CreateForm.RedeemMenu(player);
                    break;
                case 1:
                    CreateForm.CreateCdk(player);
                    break;
                case 2:
                    CreateForm.SetCdk(player);
                    break;
                default:
                    break;
            }
        }
        if(id == 0x20230301){
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            if(input == null){
                CreateForm.RedeemMenu(player);
                //如果输入内容为null，返回兑换界面
            } else {
                event.getPlayer().getServer().dispatchCommand(event.getPlayer(),"cdk "+input);
            }
        }
        if(id == 0x20230302){
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            if(input == null){
                CreateForm.CreateCdk(player);
                //如果输入内容为null，返回创建界面
            } else {
                event.getPlayer().getServer().dispatchCommand(event.getPlayer(),"cdk create "+input);
            }
        }
        if(id == 0x20230303){
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            String input = response.getInputResponse(0);
            String input2 = response.getInputResponse(1);
            if(input == null){
                CreateForm.SetCdk(player);
                //如果输入内容为null，返回设置界面
            } else {
                event.getPlayer().getServer().dispatchCommand(event.getPlayer(),"cdk set "+input+" "+input2);
            }
        }
    }
}
