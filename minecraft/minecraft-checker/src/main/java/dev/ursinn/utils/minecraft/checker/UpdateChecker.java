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

package dev.ursinn.utils.minecraft.checker;

import lombok.Cleanup;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
public class UpdateChecker {

    private final String id;
    private final String pluginName;
    private final String pluginVersion;
    private final UpdatePlatform updatePlatform;

    /**
     * Result of UpdateCheck
     */
    @Getter
    private boolean updateAvailable;
    private String updateNotifyText;

    /**
     * Constructor.
     *
     * @param id      Plugin Id
     * @param name    Plugin Name
     * @param version Plugin Version
     */
    public UpdateChecker(String id, String name, String version, UpdatePlatform platform) {
        this.id = Objects.requireNonNull(id);
        this.pluginName = Objects.requireNonNull(name);
        this.pluginVersion = Objects.requireNonNull(version);
        this.updatePlatform = Objects.requireNonNull(platform);
        this.updateAvailable = false;
        this.updateNotifyText = "An update for %PLUGIN_NAME% is available";
    }

    /**
     * Checks for Update on Spigot
     */
    public void checkUpdate() {
        new Thread(() -> {
            try {
                URLConnection connection = new URL(updatePlatform.getUrl(id)).openConnection();
                checkVersion(connection);
            } catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }).start();
    }

    private void checkVersion(URLConnection connection) throws IOException {
        @Cleanup InputStreamReader reader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
        @Cleanup BufferedReader br = new BufferedReader(reader);
        String newVersion = br.readLine();
        if (!newVersion.equals(pluginVersion)) {
            updateAvailable = true;
            System.out.println(getFormattedUpdateNotifyText());
        }
    }

    /**
     * Placeholders:
     * %PLUGIN_NAME% - Plugin Name
     *
     * @param updateNotifyText UpdateNotifyText
     */
    public void setUpdateNotifyText(String updateNotifyText) {
        this.updateNotifyText = updateNotifyText;
    }

    /**
     * @return Formatted UpdateNotifyText
     */
    public String getFormattedUpdateNotifyText() {
        return updateNotifyText.replace("%PLUGIN_NAME%", pluginName);
    }
}
