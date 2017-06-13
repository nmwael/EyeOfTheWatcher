package org.eyeofthewatcher.dungeonmaster.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.URL;

/**
 * Created by nmw on 09-06-2017.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JSONAble {

    private Integer id;
    private String name;
    private String description;
    private URI image;
}
