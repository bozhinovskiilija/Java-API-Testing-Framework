package models.booking.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {

    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }
}
