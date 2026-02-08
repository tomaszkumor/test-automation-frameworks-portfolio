package models.api.pojo;

import lombok.Data;
import utils.ideserialize.IDeserialize;

import java.util.List;

@Data
public class POJOPet implements IDeserialize<POJOPet> {
    private int id;
    private POJOCategory category;
    private String name;
    private List<String> photoUrls;
    private List<POJOTag> tags;
    private String status;

    @Override
    public POJOPet deserialize(String path, Class<POJOPet> classOf) {
        return IDeserialize.super.deserialize(path, classOf);
    }
}
