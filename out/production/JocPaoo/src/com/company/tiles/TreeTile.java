package com.company.tiles;

import com.company.gfx.Assets;

public class TreeTile extends Tile {

    public TreeTile(int id) {
        super(Assets.tree, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
