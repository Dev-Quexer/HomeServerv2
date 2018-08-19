package me.quexer.homeserverv2.homebackend.enums;

public enum Group {

    ADMIN("§4", "§4Admin §8● §4", "§4Administrator", 13),
    SRDEV("§b", "§bSrDev §8● §b", "§bSrDeveloper", 12),
    SRMOD("§c", "§cSrMod §8● §c", "§cSrModerator", 11),
    SRBUILDER("§2", "§2SrBuilder §8● §2", "§2SrBuilder", 10),
    DEV("§b", "§bDev §8● §b", "§bDeveloper", 9),
    MOD("§c", "§cMod §8● §c", "§cModerator", 8),
    BUILDER("§2", "§2Builder §8● §2", "§2Builder", 7),
    CONTENT("§b", "§bContent §8● §b", "§bContent", 6),
    SUPPORTER("§9", "§9Sup §8● §9", "§9Supporter", 5),
    YOUTUBER("§5", "§5YT §8● §5", "§5YouTuber", 4),
    PREMIUMPLUS("§e", "§ePremium+ §8● §e", "§ePremiumPlus", 3),
    PREMIUM("§6", "§6Premium §8● §6", "§6Premium", 2),
    SPIELER("§a", "§aSpieler §8● §7", "§aSpieler", 1);

    private String krz;
    private String prefix;
    private String full;
    private int groupId;

    Group(String krz, String prefix, String full, int groupId) {
        this.krz = krz;
        this.prefix = prefix;
        this.full = full;
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getKrz() {
        return this.krz;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getFull() {
        return full;
    }

}
