package org.eyeofthewatcher.dungeonmaster;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.eyeofthewatcher.dungeonmaster.entities.JSONAble;
import org.eyeofthewatcher.dungeonmaster.entities.Weapon;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by nmw on 12-06-2017.
 */
public class JSONRepository<T extends JSONAble> implements Repository<T> {


    private ObjectMapper mapper = new ObjectMapper();
    private File file;

    private List<T> items;

    public JSONRepository(Class<T> clazz) {
        file = new File(clazz.getClass().toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            items = mapper.readValue(file, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(T object) {
        if (get(object.getId()) != null) {
            items.set(items.indexOf(get(object.getId())), object);
        } else {
            items.add(object);
        }

        try {
            mapper.writeValue(file, items);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List getAll() {
        return items;
    }

    @Override
    public T get(Integer id) {
        T foundItem = IterableUtils.find(items, new Predicate<T>() {
            @Override
            public boolean evaluate(T jsonAble) {
                return jsonAble.getId() == id;
            }
        });
        return foundItem;
    }
}
