package group25.group25;

import group25.group25.workspace.model.UserAccessWorkspace;
import group25.group25.workspace.model.Workspace;
import group25.group25.workspace.repository.UserAccessWorkspaceRepository;
import group25.group25.workspace.repository.WorkspaceRepository;
import group25.group25.workspace.service.WorkspaceService;
import group25.group25.workspace.serviceImplementation.WorkspaceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WorkspaceServiceTest {
    @Mock
    WorkspaceRepository workspaceRepository;
    @Mock
    UserAccessWorkspaceRepository accessRepository;
    @InjectMocks
    WorkspaceServiceImpl workspaceService;

    @Test
    void testSaveWorkspace() {
        when(workspaceRepository.save(any(Workspace.class))).thenReturn(new Workspace());

        Workspace workspace = new Workspace("Test workspace", "Description");
        Workspace saved = workspaceService.saveWorkspace(workspace);

        Assertions.assertNotNull(saved);
    }

    @Test
    void testFindAll() {
        List<Workspace> workspaces = new ArrayList<>();

        when(workspaceRepository.findAll()).thenReturn(workspaces);

        List<Workspace> workspacesFromFindAll = workspaceService.findAll();

        Assertions.assertNotNull(workspaces);
        Assertions.assertNotNull(workspacesFromFindAll);
    }

    @Test
    void testAssignWorkspaceUser_newOrExisting() {
        when(accessRepository.existsByUserIdAndWorkspaceId(anyInt(), anyInt())).thenReturn(0); // Empty repository
        when(accessRepository.existsByUserIdAndWorkspaceId(0, 0)).thenReturn(1); // 0,0 exists

        Assertions.assertTrue(workspaceService.assignWorkspaceUser(new UserAccessWorkspace(0, 1)));
        Assertions.assertFalse(workspaceService.assignWorkspaceUser(new UserAccessWorkspace(0, 0)));
    }
}
