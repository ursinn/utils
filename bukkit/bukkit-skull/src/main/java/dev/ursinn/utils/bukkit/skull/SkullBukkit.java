/*
 * MIT License
 *
 * Copyright (c) 2021 Ursin Filli
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.ursinn.utils.bukkit.skull;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import dev.ursinn.utils.bukkit.reflections.ReflectionsBukkit;
import org.apiguardian.api.API;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
@API(status = API.Status.MAINTAINED, since = "1.0")
public class SkullBukkit {

    private SkullBukkit() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Return a skull that has a custom texture specified by url.
     *
     * @param url skin url
     * @return item ({@link ItemStack})
     */
    public static ItemStack getCustomSkull(String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }

        String encodedData = Base64.getEncoder().encodeToString(
                String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes(Charset.defaultCharset()));
        propertyMap.put("textures", new Property("textures", encodedData));
        ItemStack head = new ItemStack(getSkullMaterial(), 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        ReflectionsBukkit.getField(headMeta.getClass(), "profile", GameProfile.class).set(headMeta, profile);
        head.setItemMeta(headMeta);
        return head;
    }

    /**
     * Return a skull of a player.
     *
     * @param name player name
     * @return item ({@link ItemStack})
     */
    public static ItemStack getPlayerSkull(String name) {
        ItemStack itemStack = new ItemStack(getSkullMaterial(), 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Return minecraft version 1.8 to 1.12
     *
     * @return minecraft 1.8 skull versions
     */
    public static List<String> get18Versions() {
        List<String> versions = new ArrayList<>();
        versions.add("v1_8_R1");
        versions.add("v1_8_R2");
        versions.add("v1_8_R3");
        versions.add("v1_9_R1");
        versions.add("v1_9_R2");
        versions.add("v1_10_R1");
        versions.add("v1_11_R1");
        versions.add("v1_12_R1");
        return versions;
    }

    /**
     * Return material SKULL_ITEM for 1.8 skulls and PLAYER_HEAD for other skull versions
     *
     * @return skull material
     */
    public static Material getSkullMaterial() {
        if (get18Versions().contains(ReflectionsBukkit.getNmsVersion())) {
            return Material.getMaterial("SKULL_ITEM");
        }

        return Material.getMaterial("PLAYER_HEAD");
    }

}
