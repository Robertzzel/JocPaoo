package com.company.states;

import com.company.Assets;
import com.company.Game;
import com.company.entities.Player;
import com.company.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Game game){
        super(game);
        player = new Player(game,100,100);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        Tile.tiles[0].render(g,0,0);
        player.render(g);
    }
}
