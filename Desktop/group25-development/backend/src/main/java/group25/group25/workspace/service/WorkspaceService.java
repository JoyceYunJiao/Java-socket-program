package group25.group25.workspace.service;

import group25.group25.workspace.model.UserAccessWorkspace;
import group25.group25.workspace.model.Workspace;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkspaceService {
    public Workspace saveWorkspace(Workspace workspace);
    public boolean assignWorkspaceUser(UserAccessWorkspace access);
    public List<Workspace> findAll();
}
