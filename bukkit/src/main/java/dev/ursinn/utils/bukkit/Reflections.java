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

package dev.ursinn.utils.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
public class Reflections {

    private Reflections() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @return NMS Version
     */
    public static @Nonnull
    String getNmsVersion() {
        String ver = Bukkit.getServer().getClass().getPackage().getName();
        return ver.substring(ver.lastIndexOf('.') + 1);
    }

    /**
     * @param className NMS Class Name
     * @return NMS Class
     * @throws ClassNotFoundException NMS Class not Found
     */
    public static @Nonnull
    Class<?> getNmsClass(@Nonnull String className) throws ClassNotFoundException {
        String name = "net.minecraft.server." + getNmsVersion() + "." + className;
        return Class.forName(name);
    }

    /**
     * @param className OBC Class Name
     * @return OBC Class
     * @throws ClassNotFoundException OBC Class not Found
     */
    public static @Nonnull
    Class<?> getObcClass(@Nonnull String className) throws ClassNotFoundException {
        String name = "org.bukkit.craftbukkit." + getNmsVersion() + "." + className;
        return Class.forName(name);
    }

    /**
     * Get PlayerConnection OBC Object
     *
     * @param player Player
     * @return PlayerConnection OBC Object
     * @throws NoSuchMethodException     Exception
     * @throws InvocationTargetException Exception
     * @throws IllegalAccessException    Exception
     * @throws NoSuchFieldException      Exception
     */
    public static @Nonnull
    Object getConnection(@Nonnull Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Method getHandle = player.getClass().getMethod("getHandle");
        Object nmsPlayer = getHandle.invoke(player);
        Field conField = nmsPlayer.getClass().getField("playerConnection");
        return conField.get(nmsPlayer);
    }

    /**
     * Send Packet Object to Player
     *
     * @param player Player
     * @param packet Packet Object
     * @throws ClassNotFoundException    Exception
     * @throws NoSuchMethodException     Exception
     * @throws IllegalAccessException    Exception
     * @throws NoSuchFieldException      Exception
     * @throws InvocationTargetException Exception
     */
    public static void sendPacket(@Nonnull Player player, @Nonnull Object packet) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Method sendPacket = getNmsClass("PlayerConnection").getMethod("sendPacket", getNmsClass("Packet"));
        sendPacket.invoke(getConnection(player), packet);
    }
}
