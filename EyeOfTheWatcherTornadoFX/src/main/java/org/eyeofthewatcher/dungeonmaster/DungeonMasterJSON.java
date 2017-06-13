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
    private JSONRepository<Weapon> weaponJSONRepository;
    private JSONRepository<Tile> tileJSONRepository;
    private JSONRepository<Prop> propJSONRepository;

    public DungeonMasterJSON() {
        random = new Random();
        weaponJSONRepository = new JSONRepository<>(Weapon.class);
        tileJSONRepository = new JSONRepository<>(Tile.class);
        propJSONRepository = new JSONRepository<>(Prop.class);
    }

    @Override
    public JSONRepository<Weapon> getWeaponRepository() {

        return weaponJSONRepository;
    }

    @Override
    public JSONRepository<Tile> getTileRepository() {

        return tileJSONRepository;
    }

    @Override
    public JSONRepository<Prop> getPropRepository() {

        return propJSONRepository;
    }

    @Override
    public Integer rollDice(Short sides) {
        return random.nextInt(sides);
    }
}
