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

package dev.ursinn.utils.spigot.builder;

import dev.ursinn.utils.bukkit.builder.ItemBuilderBukkit;
import org.apiguardian.api.API;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
@API(status = API.Status.MAINTAINED, since = "1.0")
public class ItemBuilderSpigot extends ItemBuilderBukkit {

    /**
     * Constructor.
     *
     * @param itemStack item ({@link ItemStack})
     */
    public ItemBuilderSpigot(ItemStack itemStack) {
        super(Objects.requireNonNull(itemStack));
    }

    /**
     * Constructor.
     *
     * @param type item material
     */
    public ItemBuilderSpigot(Material type) {
        super(Objects.requireNonNull(type));
    }

    /**
     * Constructor.
     *
     * @param type   item material
     * @param amount item amount
     */
    public ItemBuilderSpigot(Material type, int amount) {
        super(Objects.requireNonNull(type), amount);
    }

    /**
     * Constructor.
     *
     * @param type   item material
     * @param amount item amount
     * @param damage durability / damage
     */
    public ItemBuilderSpigot(Material type, int amount, short damage) {
        super(Objects.requireNonNull(type), amount, damage);
    }

    /**
     * @return Unbreakable status
     */
    public boolean isUnbreakable() {
        return getItemMeta().spigot().isUnbreakable();
    }

    /**
     * @param unbreakable Unbreakable status
     * @return ItemBuilder
     */
    public ItemBuilderSpigot setUnbreakable(boolean unbreakable) {
        ItemMeta meta = getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        setItemMeta(meta);
        return this;
    }
}

