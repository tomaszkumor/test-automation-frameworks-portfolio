package dataProviders.dataProvidersModels;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DesktopModel {
    private String fontSize;
    private String fileName;
    private String documentScale;
}
