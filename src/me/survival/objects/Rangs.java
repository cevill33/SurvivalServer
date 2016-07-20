package me.survival.objects;

public enum Rangs {

    OWNER("§4§lOWNER§f ", "AOwner", "vetox.tab.owner"),
    ADMIN("§c§lADMIN§f ", "BAdmin", "vetox.tab.admin"),
    MOD("§6§lMOD§f ", "CMod", "vetox.tab.mod"),
    BUILDERPLUS("§2§lBUILDER§f ", "DBuilder", "vetox.tab.builder"),
    DEV("§3§lDEV§f ", "EDev", "vetox.tab.dev"),
    SUPP("§3§lSUPP§f ", "FSupp", "vetox.tab.supp"),
    TITAN("§a§lTITAN§f ", "GTitan", "vetox.tab.titan"),
    YT("§5§lYT§f ", "HYt", "vetox.tab.yt"),
    ELITE("§6§lELITE§f ", "IElite", "vetox.tab.elite"),
    SPIELER("§f", "JSpieler", "vetox.tab.spieler");



    private String prefix;
    private String id;
    private String permission;

    private Rangs(String prefix, String id, String permission) {
        this.prefix = prefix;
        this.id = id;
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPermission() {
        return permission;
    }








    }
