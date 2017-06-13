package org.eyeofthewatcher.dungeonmaster;

import org.eyeofthewatcher.dungeonmaster.entities.Prop;
import org.eyeofthewatcher.dungeonmaster.entities.Tile;
import org.eyeofthewatcher.dungeonmaster.entities.Weapon;

/**
 * Created by nmw on 12-06-2017.
 */
public interface DungeonMaster {

    public JSONRepository<Weapon> getWeaponRepository();
    public JSONRepository<Tile> getTileRepository();
    public JSONRepository<Prop> getPropRepository();

    public Integer rollDice(Short sides);
}
