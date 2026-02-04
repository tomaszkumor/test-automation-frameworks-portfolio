package models.api.pojo;

import lombok.Data;
import utils.ideserialize.IDeserialize;

@Data
public class POJOApiResponse implements IDeserialize<POJOApiResponse>{
    private int code;
    private String type;
    private String message;

    @Override
    public POJOApiResponse deserialize(String path, Class<POJOApiResponse> classOf) {
        return IDeserialize.super.deserialize(path, classOf);
    }
}
