package org.eyeofthewatcher.dungeonmaster;

import org.eyeofthewatcher.dungeonmaster.entities.Weapon;

import java.util.List;

/**
 * Created by nmw on 12-06-2017.
 */
public interface Repository<T> {

    public void save(T object);

    public List<T> getAll();

    public T get(Integer id);



}
