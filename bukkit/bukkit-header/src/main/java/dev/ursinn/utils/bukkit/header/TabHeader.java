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

package dev.ursinn.utils.bukkit.header;

import lombok.Getter;
import org.apiguardian.api.API;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.0")
public class TabHeader {

    @Getter
    private final String header;

    @Getter
    private final String footer;

    /**
     * Constructor.
     *
     * @param header Header line
     * @param footer Footer line
     */
    public TabHeader(String header, String footer) {
        this.header = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(header));
        this.footer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(footer));
    }

    /**
     * Send TabHeader to Player
     *
     * @param player Player
     */
    public void send(Player player) {
        //
    }
}
