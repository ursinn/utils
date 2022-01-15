/*
 * MIT License
 *
 * Copyright (c) 2021-2022 Ursin Filli
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
 */

package dev.ursinn.utils.bukkit.reflections;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
public class ReflectionsBukkit {

    private ReflectionsBukkit() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @return NMS Version
     */
    public static String getNmsVersion() {
        String ver = Bukkit.getServer().getClass().getPackage().getName();
        return ver.substring(ver.lastIndexOf('.') + 1);
    }

    /**
     * @param className NMS Class Name
     * @return NMS Class
     * @throws ClassNotFoundException NMS Class not Found
     */
    public static Class<?> getNmsClass(String className) throws ClassNotFoundException {
        String name = "net.minecraft.server." + getNmsVersion() + "." + className;
        return Class.forName(name);
    }

    /**
     * @param className OBC Class Name
     * @return OBC Class
     * @throws ClassNotFoundException OBC Class not Found
     */
    public static Class<?> getObcClass(String className) throws ClassNotFoundException {
        String name = "org.bukkit.craftbukkit." + getNmsVersion() + "." + className;
        return Class.forName(name);
    }

    /**
     * Retrieve a field accessor for a specific field type and name.
     *
     * @param <T>       Class
     * @param target    the target type
     * @param name      the name of the field, or NULL to ignore
     * @param fieldType a compatible field type
     * @return the field accessor
     */
    public static <T> FieldAccessor<T> getField(Class<?> target, String name,
                                                Class<T> fieldType) {
        return getField(target, name, fieldType, 0);
    }

    /**
     * Retrieve a field accessor for a specific field type and name.
     *
     * @param <T>       Class
     * @param target    the target type
     * @param fieldType a compatible field type
     * @param index     the number of compatible fields to skip
     * @return the field accessor
     */
    public static <T> FieldAccessor<T> getField(Class<?> target, Class<T> fieldType,
                                                int index) {
        return getField(target, null, fieldType, index);
    }

    private static <T> FieldAccessor<T> getField(Class<?> target, @Nullable String name,
                                                 Class<T> fieldType, int index) {
        for (final Field field : target.getDeclaredFields()) {
            if ((name == null || field.getName().equals(name)) &&
                    fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
                field.setAccessible(true);

                return new FieldAccessor<T>() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public T get(Object target) {
                        try {
                            return (T) field.get(target);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Cannot access reflection.", e);
                        }
                    }

                    @Override
                    public void set(Object target, Object value) {
                        try {
                            field.set(target, value);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Cannot access reflection.", e);
                        }
                    }

                    @Override
                    public boolean hasField(Object target) {
                        return field.getDeclaringClass().isAssignableFrom(target.getClass());
                    }
                };
            }
        }

        // Search in parent classes
        if (target.getSuperclass() != null) {
            return getField(target.getSuperclass(), name, fieldType, index);
        }
        throw new IllegalArgumentException("Cannot find field with type " + fieldType);
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
    public static Object getConnection(Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
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
    public static void sendPacket(Player player, Object packet) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Method sendPacket = getNmsClass("PlayerConnection").getMethod("sendPacket", getNmsClass("Packet"));
        sendPacket.invoke(getConnection(player), packet);
    }
}
