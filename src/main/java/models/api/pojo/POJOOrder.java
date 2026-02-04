package models.api.pojo;

import lombok.Data;
import utils.ideserialize.IDeserialize;

@Data
public class POJOOrder implements IDeserialize<POJOOrder> {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    @Override
    public POJOOrder deserialize(String path, Class<POJOOrder> classOf) {
        return IDeserialize.super.deserialize(path, classOf);
    }

    public POJOOrder replaceShipDate(POJOOrder actualResponse) {
        setShipDate(actualResponse.getShipDate());

        return this;
    }
}
