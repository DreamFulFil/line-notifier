package org.dream.gadgets.linenotifier.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccessTokenResponse extends GenericResponse {
    
    private String accessToken;

}
