/*
 * Copyright (c) 2020, Rían Errity. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 3 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 3 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Rían Errity <rian@paradaux.io> or visit https://paradaux.io
 * if you need additional information or have any questions.
 * See LICENSE.md for more details.
 */

package io.paradaux.hiberniadiscord.eventlisteners;

import io.paradaux.hiberniadiscord.HiberniaDiscord;
import io.paradaux.hiberniadiscord.controllers.TaskController;
import io.paradaux.hiberniadiscord.webhookutils.ChatWebhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerLoadEvent;

public class ServerLoadEventListener extends WebhookListener {

    @EventHandler(priority = EventPriority.LOW)
    public void listener(ServerLoadEvent event) {

        // Stop if disabled
        if (!configuration.isServerStartupEnabled()) {
            return;
        }

        TaskController.newChain().async(() -> {
            // Parse Username Placeholders
            String userName = configuration.getServerStartupUsernameFormat();

            // Parse Message Placeholders
            String messageContent = configuration.getServerStartupMessageFormat();

            String avatarUrl = configuration.getServerStartupAvatarUrl();

            // Sanitise Message, remove @everyone, @here and replace empty messages with
            // a zero-width space.
            messageContent = this.sanistiseMessage(messageContent);

            // Send the webhook
            new ChatWebhook(userName, avatarUrl, messageContent).sendWebhook();
        }).execute();
    }
}
