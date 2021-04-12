package com.company.tiles;

import com.company.gfx.Assets;

public class BradTile extends Tile {

    public BradTile(int id) {
        super(Assets.brad, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
