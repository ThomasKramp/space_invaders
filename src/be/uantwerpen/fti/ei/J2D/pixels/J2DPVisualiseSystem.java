package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.J2D.J2DGraphicsContext;
import be.uantwerpen.fti.ei.J2D.J2DAVisualiseSystem;
import be.uantwerpen.fti.ei.components.AVisualComp;

import java.awt.*;

public class J2DPVisualiseSystem extends J2DAVisualiseSystem {
    J2DGraphicsContext grCtx;

    public J2DPVisualiseSystem(J2DGraphicsContext grCtx) { super(grCtx); }

    @Override
    protected void placeComponents(AVisualComp component) {
        Graphics2D g2d = grCtx.getG2d();
        J2DPVisualComp j2dComp = (J2DPVisualComp) component;

        // Set Colors
        if (j2dComp.isHit())            g2d.setColor(new Color(255 - j2dComp.getR(),255 - j2dComp.getG(),255 - j2dComp.getB())); // invert colors if hit
        else if (j2dComp.isBigHit())    g2d.setColor(new Color(224,224,224)); // invert colors if hit
        else                            g2d.setColor(new Color(j2dComp.getR(), j2dComp.getG(), j2dComp.getB()));

        // Place component
        int scaleDiff = (grCtx.getScale() - j2dComp.getScale()) / 2; // Compensate for scaling differences
        g2d.fillRect(j2dComp.getX() * grCtx.getScale() + scaleDiff, j2dComp.getY() * grCtx.getScale() + scaleDiff,
                j2dComp.getSize() * j2dComp.getScale(), j2dComp.getScale());
    }
}
