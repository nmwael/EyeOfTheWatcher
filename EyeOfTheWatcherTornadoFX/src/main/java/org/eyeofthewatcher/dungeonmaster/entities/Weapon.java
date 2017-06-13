package org.eyeofthewatcher.dungeonmaster.entities;

import lombok.Data;

import java.net.URL;

/**
 * Created by nmw on 09-06-2017.
 */
@Data
public class Weapon extends Item {

    private Short damage;

    private URL effect;

    private boolean ranged;
    private byte rangedSpeed;
    private byte maxrange;
    private URL projectile;

}
