package cdk.windows;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

public class CreateWindow {

    static int MENU = 0x32e2d;
    static int REDEEM = 0x82c85;
    static int CREATE = 0x716ed;
    static int SET = 0x3718e;
    static int DEL = 0x18d22;

    public static void sendMainMenu(Player player) {
        FormWindowSimple simple = new FormWindowSimple("CDK", "");
        simple.addButton(new ElementButton("兑换", new ElementButtonImageData("path","textures/ui/invite_base")));
        if (player.isOp()){
            simple.addButton(new ElementButton("创建", new ElementButtonImageData("path","textures/ui/icon_book_writable")));
            simple.addButton(new ElementButton("添加", new ElementButtonImageData("path","textures/ui/dark_plus")));
            simple.addButton(new ElementButton("删除", new ElementButtonImageData("path","textures/ui/cancel")));
            simple.addButton(new ElementButton("获取", new ElementButtonImageData("path","textures/ui/icon_map")));
        }
        player.showFormWindow(simple, MENU);
    }

    public static void sendRedeemMenu(Player player) {
        FormWindowCustom custom = new FormWindowCustom("CDK--兑换");
        custom.addElement(new ElementInput("","请输入CDK"));
        player.showFormWindow(custom, REDEEM);
    }

    public static void sendCreateMenu(Player player) {
        FormWindowCustom custom = new FormWindowCustom("CDK--创建");
        custom.addElement(new ElementInput("奖励代号","请输入奖励代号"));
        player.showFormWindow(custom, CREATE);
    }

    public static void sendSetMenu(Player player) {
        FormWindowCustom custom = new FormWindowCustom("CDK--添加");
        custom.addElement(new ElementInput("奖励代号","请输入奖励代号"));
        custom.addElement(new ElementInput("数量","请输入数量(可不填)"));
        player.showFormWindow(custom, SET);
    }

    public static void sendDelMenu(Player player) {
        FormWindowCustom custom = new FormWindowCustom("CDK--删除");
        custom.addElement(new ElementInput("删除奖励代号","请输入要删除的奖励代号"));
        player.showFormWindow(custom, DEL);
    }
}
