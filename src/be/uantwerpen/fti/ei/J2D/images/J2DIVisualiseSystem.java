package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.J2DGraphicsContext;
import be.uantwerpen.fti.ei.J2D.J2DAVisualiseSystem;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class J2DIVisualiseSystem extends J2DAVisualiseSystem {

    public J2DIVisualiseSystem(J2DGraphicsContext grCtx) { super(grCtx); }

    @Override
    protected void placeComponents(AVisualComp component) {
        J2DIVisualComp j2dComp = (J2DIVisualComp) component;
        // Place component
        int scaleDiff = (grCtx.getScale() - j2dComp.getScale()) / 2; // Compensate for scaling differences
        if ((j2dComp.isHit() || j2dComp.isBigHit()) && j2dComp.getSpriteHit() != null) {
            grCtx.getG2d().drawImage(j2dComp.getSpriteHit().getSubimage(0, 0, j2dComp.getSize() * j2dComp.getScale(), j2dComp.getScale()),
                    j2dComp.getX() * grCtx.getScale() + scaleDiff, j2dComp.getY() * grCtx.getScale() + scaleDiff, null);
        } else {
            grCtx.getG2d().drawImage(j2dComp.getSprite().getSubimage(0, 0, j2dComp.getSize() * j2dComp.getScale(), j2dComp.getScale()),
                    j2dComp.getX() * grCtx.getScale() + scaleDiff, j2dComp.getY() * grCtx.getScale() + scaleDiff, null);
        }
    }
}
