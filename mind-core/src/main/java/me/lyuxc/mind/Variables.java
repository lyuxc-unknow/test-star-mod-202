package me.lyuxc.mind;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Variables {
    //模组名
    public static final String MOD_ID = "test_star_core";
    //最大玩家血量
    public static double MAX_HEALTH = 80;
    //开发者设置 - 名字
    public static final String DEVELOPER_NAME = "lyuxc_";
    //开发者设置 - UUID
    public static final String DEVELOPER_UUID = "ce691642-c635-44a0-9d25-8856ccfc9ab1";
    //开发者设置 - 标签
    public static boolean DEVELOPER = false;
    //列表 - 禁用命令
    public static String[] DISABLE_COMMAND = {
            "gamemode", "give", "attribute", "advancement", "difficulty", "effect", "fill", "gamerule",
            "item", "loot", "place", "setblock", "summon", "teleport", "tp", "weather", "xp"
    };
    //最大模组数
    public static final int MAX_MOD_COUNT = 1024;
    //随机 - 种子：时间刻
    public static Random random = new Random(System.currentTimeMillis());
    //获取MC运行路径
    public static String workDir = System.getProperty("user.dir");
    //获取到的数据 - 预留
    public static String data = "";
    //禁用方块 - id
    public static String[] IDs = new String[]{""};
    //标题
    public static String title = "";
    //日志
    public static final Logger LOGGER = LogManager.getLogger("Mind2");
}
