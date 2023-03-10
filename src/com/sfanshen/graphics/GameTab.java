package com.sfanshen.graphics;

import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sfanshen.ui.GameButton;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameTab {

    public String name;
    public int x;
    public int y;
    protected int width;
    int height;

    public ArrayList<GameButton> buttons;

    public GameTab(String name) {
        this.name = name;
        this.x = Const.FRAME_X;
        this.y = Const.FRAME_Y;
        this.width = Const.FRAME_WIDTH;
        this.height = Const.FRAME_HEIGHT;

        Global.gameTabs.put(this.name, this);
    }

    abstract void draw(Graphics2D g);
}
