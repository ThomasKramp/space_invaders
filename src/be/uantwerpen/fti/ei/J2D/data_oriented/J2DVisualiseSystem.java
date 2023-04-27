package be.uantwerpen.fti.ei.J2D.data_oriented;

import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

import java.awt.*;
import java.util.List;

public class J2DVisualiseSystem implements IVisualiseSystem {
    GraphicsContext grCtx;

    public J2DVisualiseSystem(GraphicsContext grCtx) {
        this.grCtx = grCtx;
    }

    @Override
    public void visualise(List<AVisualComp> components) {
        Graphics2D g2d = grCtx.getG2d();
        int scaleDiff = 0;
        for (AVisualComp component: components) {
            // System.out.println("X: " + component.getX() + ", Y: " + component.getY());
            J2DVisualComp j2dComp = (J2DVisualComp) component;

            // Set Colors
            if (j2dComp.isHit())            g2d.setColor(new Color(255 - j2dComp.getR(),255 - j2dComp.getG(),255 - j2dComp.getB())); // invert colors if hit
            else if (j2dComp.isBigHit())    g2d.setColor(new Color(224,224,224)); // invert colors if hit
            else                            g2d.setColor(new Color(j2dComp.getR(), j2dComp.getG(), j2dComp.getB()));

            // Place component
            scaleDiff = (grCtx.getScale() - j2dComp.getScale()) / 2; // Compensate for scaling differences
            g2d.fillRect(j2dComp.getX() * grCtx.getScale() + scaleDiff, j2dComp.getY() * grCtx.getScale() + scaleDiff,
                      j2dComp.getSize() * j2dComp.getScale(), j2dComp.getScale());
        }
        grCtx.render();
    }

    @Override
    public void end() { grCtx.close(); }
}
