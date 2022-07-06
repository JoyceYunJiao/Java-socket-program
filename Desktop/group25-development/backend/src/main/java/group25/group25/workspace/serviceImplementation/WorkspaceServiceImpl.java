package group25.group25.workspace.serviceImplementation;

import group25.group25.workspace.model.UserAccessWorkspace;
import group25.group25.workspace.model.Workspace;
import group25.group25.workspace.repository.UserAccessWorkspaceRepository;
import group25.group25.workspace.repository.WorkspaceRepository;
import group25.group25.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private UserAccessWorkspaceRepository accessRepository;

    @Override
    public Workspace saveWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    @Override
    public boolean assignWorkspaceUser(UserAccessWorkspace access) {
        if (accessRepository.existsByUserIdAndWorkspaceId(access.getUserId(), access.getWorkspaceId())==1)
            // This user is already assigned to this workspace
            return false;

        // Save new access to table
        accessRepository.save(access);
        return true;
    }

    @Override
    public List<Workspace> findAll() {
        return workspaceRepository.findAll();
    }
}
