package com.kalpesh.map.direction.finder;

import com.kalpesh.map.direction.model.Direction;

/**
 * Created by user on 10/6/2014.
 */
public interface IDirectionListener {
    public void onDirectionFound(Direction direction);

    public void onError(int result);
}
