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

package dev.ursinn.utils.bungee;

import com.google.common.reflect.ClassPath;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.apiguardian.api.API;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
@API(status = API.Status.MAINTAINED, since = "1.0")
public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Register Listeners from Package
     *
     * @param packageName Package Name
     * @param plugin      Plugin Instance
     */
    public static void registerListener(String packageName, Plugin plugin) {
        PluginManager pluginManager = Objects.requireNonNull(plugin).getProxy().getPluginManager();
        try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getTopLevelClasses(Objects.requireNonNull(packageName))) {
                Class<Listener> clazz = (Class<Listener>) Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(clazz)) {
                    pluginManager.registerListener(plugin, clazz.getDeclaredConstructor().newInstance());
                }
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException | InvocationTargetException | InstantiationException exception) {
            plugin.getLogger().warning(String.valueOf(exception));
        }
    }

    /**
     * Build String from String array
     *
     * @param args  String array
     * @param start Start position in array
     * @return String
     */
    public static String buildString(String[] args, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i == args.length - 1) {
                sb.append(args[i]);
            } else {
                sb.append(args[i]).append(" ");
            }
        }
        return sb.toString();
    }
}
