package com.mts.tz.task.util;

import java.util.UUID;

public class Util {

    public static boolean isGuid(String guid) {
        try {
            UUID.fromString(guid);
            return true;
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return false;
    }

}
