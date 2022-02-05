package org.dream.gadgets.linenotifier.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiStatusResponse extends GenericResponse {
    
    private String targetType;
    private String target;
    
}
