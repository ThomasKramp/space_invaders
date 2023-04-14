package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.AVisualComp;

import java.util.List;

public interface IVisualiseSystem {
    void visualise(List<AVisualComp> components);
    void end();
}
