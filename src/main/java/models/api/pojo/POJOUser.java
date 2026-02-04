package models.api.pojo;

import lombok.Data;
import utils.ideserialize.IDeserialize;

@Data
public class POJOUser implements IDeserialize<POJOUser> {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String userStatus;

    @Override
    public POJOUser deserialize(String path, Class<POJOUser> classOf) {
        return IDeserialize.super.deserialize(path, classOf);
    }
}
