package com.company.states;

import com.company.Assets;
import com.company.Game;
import com.company.entities.Player;
import com.company.tiles.Tile;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game,100,100);
        world = new World("res/worlds/world1.txt");
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
