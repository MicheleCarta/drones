package org.drones.business;

import org.drones.dto.TubeDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StorageManager {
    private static Collection<TubeDTO> tubes = Collections.synchronizedCollection(new ArrayList<>());

    public static Collection<TubeDTO> getTubes() {
        return tubes;
    }
}
