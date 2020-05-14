package com.calltouch.spring5mvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by morgan on 12.05.2020
 */

public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
