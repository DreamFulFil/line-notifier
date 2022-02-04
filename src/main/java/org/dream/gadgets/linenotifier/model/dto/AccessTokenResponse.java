package org.dream.gadgets.linenotifier.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccessTokenResponse extends GenericResponse {
    
    @JsonProperty(value = "access_token")
    private String accessToken;

}
