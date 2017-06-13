package org.eyeofthewatcher.dungeonmaster.entities;

import lombok.Data;

import java.time.Duration;

/**
 * Created by nmw on 09-06-2017.
 */
@Data
public class EntitySpawner extends JSONAble {
    private Integer entityId;
    private Integer amountOfEntities;
    private Duration frequency;
}
