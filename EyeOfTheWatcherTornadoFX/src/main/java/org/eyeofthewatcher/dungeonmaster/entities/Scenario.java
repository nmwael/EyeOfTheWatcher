package org.eyeofthewatcher.dungeonmaster.entities;

import lombok.Data;

/**
 * Created by nmw on 09-06-2017.
 */
@Data
public class Scenario extends JSONAble {
    private Integer sizeY;
    private Integer sizeX;
    private Tile[][] tiles;
    private Prop[][] props;
    private EntitySpawner[][] entitySpawners;

    public Scenario(Integer sizeX, Integer sizeY) {
        setSizeX(sizeX);
        setSizeY(sizeY);
        initialize();
    }

    private void initialize() {
        tiles = new Tile[sizeX][sizeY];
        props = new Prop[sizeX][sizeY];
        entitySpawners = new EntitySpawner[sizeX][sizeY];

    }

}
