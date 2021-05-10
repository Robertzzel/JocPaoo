package com.company.tiles;

import com.company.gfx.Assets;

public class SolidGrassTile extends Tile{

    public SolidGrassTile(int id) {
        super(Assets.grass, id);
    }

    @Override
    public boolean isSolid() { return true; }
}
