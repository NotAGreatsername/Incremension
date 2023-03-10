package com.sfanshen.ui;

import com.sfanshen.graphics.Picture;
import com.sfanshen.graphics.UpgradeTab;
import com.sfanshen.main.Const;
import com.sfanshen.main.Global;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import com.sfanshen.upgrade.Upgrade;

import java.awt.*;

public class UpgradeButton extends GameButton {
    Picture icon;
    Upgrade parentUpgrade;

    public UpgradeButton(Picture icon, Upgrade parentUpgrade) {
        super(0, 0, Const.UPGRADE_SIZE, Const.UPGRADE_SIZE);
        this.icon = icon;
        this.parentUpgrade = parentUpgrade;
    }

    public void click() {
        this.parentUpgrade.buy();
    }

    public void draw(Graphics2D g) {
        boolean isMaxed;
        double boughtPercentage;
        Picture upgradeTypeIcon;

        if (this.parentUpgrade instanceof BoostUpgrade) {
            isMaxed = ((BoostUpgrade) this.parentUpgrade).level >= ((BoostUpgrade) this.parentUpgrade).maxLevel;
            boughtPercentage = ((BoostUpgrade) this.parentUpgrade).level / (double) ((BoostUpgrade) this.parentUpgrade).maxLevel;
            upgradeTypeIcon = Global.boostUpgradeIcon;
        }
        else if (this.parentUpgrade instanceof FeatureUpgrade) {
            boughtPercentage = ((FeatureUpgrade) this.parentUpgrade).isBought ? 1 : 0;
            isMaxed = ((FeatureUpgrade) this.parentUpgrade).isBought;
            upgradeTypeIcon = Global.featureUpgradeIcon;
        }
        else {
            System.out.println("Error: Undefined upgrade type in UpgradeButton.draw");
            isMaxed = false;
            boughtPercentage = 0.0;
            upgradeTypeIcon = Global.boostUpgradeIcon;
        }

        drawBorder(g, isMaxed);
        drawFill(g, isMaxed, boughtPercentage);
        drawIcon(g, upgradeTypeIcon);
    }

    public void drawBorder(Graphics2D g, boolean isMaxed) {
        if (isMaxed) {
            g.setColor(Const.MARIO_GREEN);
        } else if (this.isMouseHovering && this.parentUpgrade.isPurchasable()) {
            g.setColor(Const.SUN_YELLOW);
        } else if (this.isMouseHovering) {
            g.setColor(Const.DEPRESSEDER_GOOGLE_HIGHLIGHT);
        } else if (this.parentUpgrade.isPurchasable()) {
            g.setColor(Const.DARKER_SUN_YELLOW);
        } else {
            g.setColor(Const.GRAY);
        }

        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke((float) Const.UPGRADE_BORDER_WIDTH));
        Rectangle border = new Rectangle(this.x, this.y, Const.UPGRADE_SIZE, Const.UPGRADE_SIZE);
        g.draw(border);
        g.setStroke(oldStroke);
    }

    public void drawFill(Graphics2D g, boolean isMaxed, double boughtPercentage) {
        if (isMaxed) {
            g.setColor(Const.LIGHT_MARIO_GREEN);
        } else if (this.isMouseHovering) {
            g.setColor(Const.GOOGLE_HIGHLIGHT);
        } else {
            g.setColor(Const.MORE_SUN_YELLOW);
        }

        int width = (int) (Const.UPGRADE_SIZE - 2 * Const.UPGRADE_BORDER_WIDTH);
        int height = (int) ((Const.UPGRADE_SIZE - 2 * Const.UPGRADE_BORDER_WIDTH) * boughtPercentage);
        int x = (int) (this.x + Const.UPGRADE_BORDER_WIDTH);
        int y = (int) (this.y + Const.UPGRADE_SIZE - Const.UPGRADE_BORDER_WIDTH - height);

        if (height > 0) {
            Rectangle fill = new Rectangle(x, y, width, height);
            g.fill(fill);
            g.draw(fill);
        }
    }

    public void drawIcon(Graphics2D g, Picture upgradeTypeIcon){
        icon.move(this.x + this.width / 2, this.y + this.height / 2, true);
        icon.draw(g);

        upgradeTypeIcon.move(this.x + this.width / 2, this.y + this.height / 2 + icon.height / 2, true);
        upgradeTypeIcon.draw(g);
    }
}
