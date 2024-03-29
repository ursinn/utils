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

package dev.ursinn.utils.bukkit.builder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.*;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
public class ItemBuilderBukkit {

    private final ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack item ({@link ItemStack})
     */
    public ItemBuilderBukkit(ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack);
    }

    /**
     * Constructor.
     *
     * @param type item material
     */
    public ItemBuilderBukkit(Material type) {
        this.itemStack = new ItemStack(Objects.requireNonNull(type));
    }

    /**
     * Constructor.
     *
     * @param type   item material
     * @param amount item amount
     */
    public ItemBuilderBukkit(Material type, int amount) {
        this.itemStack = new ItemStack(Objects.requireNonNull(type), amount);
    }

    /**
     * Constructor.
     *
     * @param type   item material
     * @param amount item amount
     * @param damage durability / damage
     */
    public ItemBuilderBukkit(Material type, int amount, short damage) {
        this.itemStack = new ItemStack(Objects.requireNonNull(type), amount, damage);
    }

    /**
     * @return item name
     */
    public String getName() {
        return getItemMeta().getDisplayName();
    }

    /**
     * @param name item name
     * @return ItemBuilder
     */
    public ItemBuilderBukkit setName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(name);
        setItemMeta(meta);
        return this;
    }

    /**
     * @param lore item lore
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(lore);
        setItemMeta(meta);
        return this;
    }

    /**
     * @param lore item lore
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addLore(String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> original = itemMeta.getLore();
        if (original == null) {
            original = new ArrayList<>();
        }
        Collections.addAll(original, lore);
        itemMeta.setLore(original);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * @return item lore
     */
    public List<String> getLore() {
        return getItemMeta().getLore();
    }

    /**
     * @param itemFlags item Flags
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addItemFlags(ItemFlag... itemFlags) {
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(itemFlags);
        setItemMeta(meta);
        return this;
    }

    /**
     * @return item Flags
     */
    public Set<ItemFlag> getItemFlags() {
        return getItemMeta().getItemFlags();
    }

    /**
     * @return item material
     */
    public Material getMaterial() {
        return itemStack.getType();
    }

    /**
     * @param type item material
     * @return ItemBuilder
     */
    public ItemBuilderBukkit setMaterial(Material type) {
        itemStack.setType(type);
        return this;
    }

    /**
     * @return item amount
     */
    public int getAmount() {
        return itemStack.getAmount();
    }

    /**
     * @param amount item amount
     * @return ItemBuilder
     */
    public ItemBuilderBukkit setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * @return durability / damage
     */
    public short getDurability() {
        return itemStack.getDurability();
    }

    /**
     * @param damage durability / damage
     * @return ItemBuilder
     */
    public ItemBuilderBukkit setDurability(short damage) {
        itemStack.setDurability(damage);
        return this;
    }

    /**
     * @return item material data
     */
    public MaterialData getData() {
        return itemStack.getData();
    }

    /**
     * @param data item material data
     * @return ItemBuilder
     */
    public ItemBuilderBukkit setData(MaterialData data) {
        itemStack.setData(data);
        return this;
    }

    /**
     * @param enchantment enchantment ({@link Enchantment})
     * @param level       enchantment level
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addEnchantment(Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return this;
    }

    /**
     * @param enchantments enchantments
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    /**
     * @param enchantment enchantment ({@link Enchantment})
     * @param level       enchantment level
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addUnsafeEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * @param enchantments enchantments
     * @return ItemBuilder
     */
    public ItemBuilderBukkit addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addUnsafeEnchantments(enchantments);
        return this;
    }

    /**
     * @return enchantments
     */
    public Map<Enchantment, Integer> getEnchantments() {
        return itemStack.getEnchantments();
    }

    /**
     * @return item meta
     */
    public ItemMeta getItemMeta() {
        return itemStack.getItemMeta();
    }

    /**
     * @param meta item meta
     * @return ItemBuilder
     */
    public ItemBuilderBukkit setItemMeta(ItemMeta meta) {
        itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * @return item ({@link ItemStack})
     */
    public ItemStack build() {
        return itemStack;
    }
}

