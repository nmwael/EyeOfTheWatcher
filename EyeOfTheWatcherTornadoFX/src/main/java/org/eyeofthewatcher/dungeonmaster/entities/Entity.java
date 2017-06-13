package org.eyeofthewatcher.dungeonmaster.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Entity extends JSONAble {

    private byte strength;
    private byte constitution;
    private byte agility;
    private byte dexterity;
    private byte intellect;
    private byte aura;

    private ArrayList<Item> items;
}