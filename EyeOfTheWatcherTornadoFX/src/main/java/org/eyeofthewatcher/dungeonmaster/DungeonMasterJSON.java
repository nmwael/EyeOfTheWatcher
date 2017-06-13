package org.eyeofthewatcher.dungeonmaster;

import org.eyeofthewatcher.dungeonmaster.entities.Prop;
import org.eyeofthewatcher.dungeonmaster.entities.Tile;
import org.eyeofthewatcher.dungeonmaster.entities.Weapon;

import java.util.Random;

/**
 * Created by nmw on 12-06-2017.
 */
public class DungeonMasterJSON implements DungeonMaster {

    private Random random;

    public DungeonMasterJSON() {
        random = new Random();
    }

    @Override
    public JSONRepository<Weapon> getWeaponRepository() {
        return new JSONRepository<Weapon>(Weapon.class);
    }

    @Override
    public JSONRepository<Tile> getTileRepository() {
        return new JSONRepository<Tile>(Tile.class);
    }

    @Override
    public JSONRepository<Prop> getPropRepository() {
        return new JSONRepository<Prop>(Prop.class);
    }

    @Override
    public Integer rollDice(Short sides) {
        return random.nextInt(sides);
    }
}
