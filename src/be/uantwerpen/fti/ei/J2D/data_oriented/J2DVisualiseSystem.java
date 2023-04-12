package be.uantwerpen.fti.ei.J2D.data_oriented;

import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

import java.util.List;

public class J2DVisualiseSystem implements IVisualiseSystem {
    GraphicsContext grCtx;

    public J2DVisualiseSystem(GraphicsContext grCtx) {
        this.grCtx = grCtx;
    }

    @Override
    public void visualise(List<AVisualComp> components) {
        for (AVisualComp component: components) {
            J2DVisualComp j2dComp = (J2DVisualComp) component;
            j2dComp.draw();
        }
        grCtx.render();
    }
}
