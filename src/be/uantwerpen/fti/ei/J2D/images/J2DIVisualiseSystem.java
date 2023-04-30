package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class J2DIVisualiseSystem implements IVisualiseSystem {
    GraphicsContext grCtx;

    public J2DIVisualiseSystem(GraphicsContext grCtx) {
        this.grCtx = grCtx;
    }

    @Override
    public void visualise(List<AVisualComp> components) {
        Graphics2D g2d = grCtx.getG2d();
        int scaleDiff;
        for (AVisualComp component: components) {
            // System.out.println("X: " + component.getX() + ", Y: " + component.getY());
            J2DIVisualComp j2dComp = (J2DIVisualComp) component;

            // Place component
            scaleDiff = (grCtx.getScale() - j2dComp.getScale()) / 2; // Compensate for scaling differences
            if ((j2dComp.isHit() || j2dComp.isBigHit()) && j2dComp.getSpriteHit() != null) {
                g2d.drawImage(j2dComp.getSpriteHit().getSubimage(0, 0, j2dComp.getSize() * j2dComp.getScale(), j2dComp.getScale()),
                        j2dComp.getX() * grCtx.getScale() + scaleDiff, j2dComp.getY() * grCtx.getScale() + scaleDiff, null);
            } else {
                g2d.drawImage(j2dComp.getSprite().getSubimage(0, 0, j2dComp.getSize() * j2dComp.getScale(), j2dComp.getScale()),
                        j2dComp.getX() * grCtx.getScale() + scaleDiff, j2dComp.getY() * grCtx.getScale() + scaleDiff, null);
            }
        }
        grCtx.render();
    }

    @Override
    public void end() { grCtx.close(); }
}
