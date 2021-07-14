package dev.ursinn.utils.minecraft.checker;

public enum UpdatePlatform {

    SPIGOT("spigot", "https://api.spigotmc.org/legacy/update.php?resource=");

    private final String name;
    private final String url;

    UpdatePlatform(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl(String id) {
        if (name.equals("spigot")) {
            return url + id;
        }
        return url;
    }
}
