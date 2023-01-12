package com.sfanshen.upgrade;

import com.sfanshen.main.Const;
import com.sfanshen.main.Global;

import java.util.ArrayList;

import java.awt.*;

public class UpgradesFrame{
  
  String upgradeFrame;
  int x, y, width, height, numPerRow;
  public ArrayList<Upgrade> upgrades;

  public UpgradesFrame(String upgradeFrame, int x, int y, int width, int height, ArrayList<Upgrade> upgrades){
    this.upgradeFrame = upgradeFrame;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.upgrades = upgrades;
    this.numPerRow = (int)(width / (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));
    Global.upgradeFrames.add(this);
  }
  
//  void sort(String sortOrder){
//    
//  }
//  
public void draw(Graphics g){
    for (int i = 0; i < this.upgrades.size(); i ++){
      Upgrade upgrade = this.upgrades.get(i);
      int x = calculateCoords(i)[0];
      int y = calculateCoords(i)[1];
      if (upgrade.isUnlocked){
        upgrade.draw(g, x, y);
      }
      else{
        drawBlank(g, x, y, width, height);
      }
    }
  }
  
  
  
  public static void drawBlank(Graphics g, int x, int y, int width, int height){
    g.setColor(Const.BLACK);
    g.drawRect(x, y, width, height);
  }

  public int[] calculateCoords(int i){
    int x, y, row, column, sideOffset;
    
    row = i / this.numPerRow;
    column = i % this.numPerRow;
    
    if (this.upgrades.size() - this.numPerRow <= 5){
      int amtOnRow = this.upgrades.size() - i - 1;
      sideOffset = (int)(this.width - amtOnRow * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET) - Const.UPGRADE_OFFSET) / 2; 
    }
    else{
      sideOffset = (int)(this.width - this.numPerRow * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET) - Const.UPGRADE_OFFSET) / 2;
    }
    
    x = (int)(this.x + sideOffset + column * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET)); 
    y = (int)(this.y + row * (Const.UPGRADE_WIDTH + Const.UPGRADE_OFFSET));

    return new int[]{x, y};
  }
}