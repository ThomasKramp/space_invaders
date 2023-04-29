package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

import java.awt.*;
import java.util.List;

public class J2DPVisualiseSystem implements IVisualiseSystem {
    J2DPGraphicsContext grCtx;

    public J2DPVisualiseSystem(J2DPGraphicsContext grCtx) {
        this.grCtx = grCtx;
    }

    @Override
    public void visualise(List<AVisualComp> components) {
        Graphics2D g2d = grCtx.getG2d();
        int scaleDiff = 0;
        for (AVisualComp component: components) {
            // System.out.println("X: " + component.getX() + ", Y: " + component.getY());
            J2DPVisualComp j2dComp = (J2DPVisualComp) component;

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
