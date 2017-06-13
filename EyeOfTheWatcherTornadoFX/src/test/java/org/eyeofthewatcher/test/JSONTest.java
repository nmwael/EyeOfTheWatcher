package org.eyeofthewatcher.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.eyeofthewatcher.dungeonmaster.entities.JSONAble;
import org.eyeofthewatcher.dungeonmaster.entities.Prop;
import org.eyeofthewatcher.dungeonmaster.entities.Scenario;
import org.eyeofthewatcher.dungeonmaster.entities.Tile;
import org.eyeofthewatcher.dungeonmaster.sprite.SpriteLoader;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;


/**
 * Created by nmw on 07-06-2017.
 */
public class JSONTest {

    @Test
    public void spriteTest() throws IOException {

        URL resource = SpriteLoader.class.getResource("DungeonCrawl_ProjectUtumnoTileset.png");
        BufferedImage bigImg = ImageIO.read(resource);
// The above line throws an checked IOException which must be caught.

        final int width = 32;
        final int height = 32;
        final int rows = bigImg.getHeight()/height;
        final int cols = bigImg.getWidth()/width;
        BufferedImage[] sprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                sprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
        System.out.println(""+sprites.length);

    }

    @Test
    public void testJson() throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        Staff obj = new Staff();

        JSONAble.builder().description("dfgdf").id(1).build();

        Scenario scenario=new Scenario(256,256);

        scenario.setName("Fortress of Doom");
        scenario.setDescription("Long ago, before the dwarves moved\n" +
                "into Ironhall, the Paladins of Light\n" +
                "stood watch on the Pine Hills.\n" +
                "The Order is long gone and the fortress,\n" +
                "commonly known as the Fortress of\n" +
                "Doom, sits at the base of the Scantridge\n" +
                "and is widely regarded as being a cursed\n" +
                "place.\n" +
                "Almost everyone in the Pine Hills has a\n" +
                "story to tell about the Fortress of Doom.\n" +
                "Much is speculation and conjecture, but\n" +
                "many stories have a kernel of truth to\n" +
                "them.\n" +
                "Time and time again, bands of robbers\n" +
                "and fierce monsters have taken up residence\n" +
                "in its halls, luring adventurous\n" +
                "warriors in search of fame and fortune.\n" +
                "Few have returned to tell their tale");
        Tile tile = new Tile();
        tile.setDescription("Rock Floor");
        tile.setImage(new URI("/org/eyeofthewatcher"));
        scenario.getTiles()[0][0]=tile;
        Prop prop=new Prop();
        prop.setInteractive(true);
        prop.setDescription("A simple chest");
        prop.setName("Chest");
        scenario.getProps()[100][200]=prop;

//Object to JSON in file
        mapper.writeValue(new File("c:\\file.json"), obj);

        mapper.writeValue(new File("c:\\scenario.json"), scenario);

//Object to JSON in String
        String jsonInString = mapper.writeValueAsString(obj);


        jsonInString = "{'name' : 'mkyong'}";

//JSON from file to Object
        //      obj = mapper.readValue(new File("c:\\file.json"), Staff.class);

//JSON from URL to Object
//        obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Staff.class);

//JSON from String to Object
//        obj = mapper.readValue(jsonInString, Staff.class);

        String json = "[{\"name\":\"mkyong\"}, {\"name\":\"laplap\"}]";
        List<Staff> list = mapper.readValue(json, new TypeReference<List<Staff>>() {
        });
        Collection<Staff> mkyoung = CollectionUtils.select(list, new Predicate<Staff>() {
            @Override
            public boolean evaluate(Staff staff) {
                return staff.getName().equalsIgnoreCase("mkyong");
            }
        });

        mkyoung.forEach(staff -> System.out.println("name" + staff.getName()));

    }
}
