/*
 * Copyright (C) 2016 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.dungeonsxl.player;

import io.github.dre2n.dungeonsxl.DungeonsXL;
import io.github.dre2n.dungeonsxl.global.DPortal;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a player in the non-DXL worlds of the server.
 *
 * @author Daniel Saukel
 */
public class DGlobalPlayer {

    static DungeonsXL plugin = DungeonsXL.getInstance();

    protected Player player;

    private boolean breakMode;
    private boolean chatSpyMode;
    private DPortal creatingPortal;

    private ItemStack[] respawnInventory;
    private ItemStack[] respawnArmor;

    public DGlobalPlayer(Player player) {
        this.player = player;

        plugin.getDPlayers().addPlayer(this);
    }

    public DGlobalPlayer(DGlobalPlayer dPlayer) {
        player = dPlayer.getPlayer();
        breakMode = dPlayer.isInBreakMode();
        chatSpyMode = dPlayer.isInChatSpyMode();
        creatingPortal = dPlayer.getPortal();
        respawnInventory = dPlayer.getRespawnInventory();
        respawnArmor = dPlayer.getRespawnArmor();

        plugin.getDPlayers().addPlayer(this);
    }

    /**
     * @return the Bukkit player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return if the player is in break mode
     */
    public boolean isInBreakMode() {
        return breakMode;
    }

    /**
     * @param breakMode
     * sets if the player is in break mode
     */
    public void setInBreakMode(boolean breakMode) {
        this.breakMode = breakMode;
    }

    /**
     * @return if the player spies the DXL chat channels
     */
    public boolean isInChatSpyMode() {
        return chatSpyMode;
    }

    /**
     * @param chatSpyMode
     * sets if the player is in chat spy mode
     */
    public void setInChatSpyMode(boolean chatSpyMode) {
        this.chatSpyMode = chatSpyMode;
    }

    /**
     * @return if the player is creating a DPortal
     */
    public boolean isCreatingPortal() {
        return creatingPortal != null;
    }

    /**
     * @return the portal the player is creating
     */
    public DPortal getPortal() {
        return creatingPortal;
    }

    /**
     * @param dPortal
     * the portal to create
     */
    public void setCreatingPortal(DPortal dPortal) {
        creatingPortal = dPortal;
    }

    /**
     * @return the respawnInventory
     */
    public ItemStack[] getRespawnInventory() {
        return respawnInventory;
    }

    /**
     * @param respawnInventory
     * the respawnInventory to set
     */
    public void setRespawnInventory(ItemStack[] respawnInventory) {
        this.respawnInventory = respawnInventory;
    }

    /**
     * Give the saved respawn inventory to the player
     */
    public void applyRespawnInventory() {
        if (respawnInventory == null || respawnArmor == null) {
            return;
        }

        player.getInventory().setContents(respawnInventory);
        player.getInventory().setArmorContents(respawnArmor);
        respawnInventory = null;
        respawnArmor = null;
    }

    /**
     * @return the respawnArmor
     */
    public ItemStack[] getRespawnArmor() {
        return respawnArmor;
    }

    /**
     * @param respawnArmor
     * the respawnArmor to set
     */
    public void setRespawnArmor(ItemStack[] respawnArmor) {
        this.respawnArmor = respawnArmor;
    }

    /**
     * @param permission
     * the permission to check
     * @return if the player has the permission
     */
    public boolean hasPermission(DPermissions permission) {
        return DPermissions.hasPermission(player, permission);
    }

    /**
     * @param permission
     * the permission to check
     * @return if the player has the permission
     */
    public boolean hasPermission(String permission) {
        return DPermissions.hasPermission(player, permission);
    }

}