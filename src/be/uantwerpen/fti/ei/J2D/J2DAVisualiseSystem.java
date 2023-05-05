package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

import java.util.List;

abstract public class J2DAVisualiseSystem implements IVisualiseSystem {
    protected J2DGraphicsContext grCtx;

    public J2DAVisualiseSystem(J2DGraphicsContext grCtx) { this.grCtx = grCtx; }

    protected abstract void placeComponents(AVisualComp component);

    @Override
    public void visualise(List<AVisualComp> components, int score, int lives) {
        // Place entities
        for (AVisualComp component: components) placeComponents(component);

        // Place hotbar
        grCtx.setText("Score: " + score, grCtx.getScale(), grCtx.getFrame().getHeight() - grCtx.getScale() * 4);
        grCtx.setText("Health: " + lives, grCtx.getScale() * 5, grCtx.getFrame().getHeight() - grCtx.getScale() * 4);

        // Render
        grCtx.render();
    }

    @Override
    public void visualise(String title, String... lines) {
        // Place title
        grCtx.setTitle(title);

        // Place text
        int y = grCtx.getFrame().getHeight() / 2;
        if (lines != null) for (String line : lines) {
            grCtx.setText(line, 0, y);
            y += grCtx.getScale();
        }

        // Render
        grCtx.render();
    }

    @Override
    public void end() { grCtx.close(); }
}
