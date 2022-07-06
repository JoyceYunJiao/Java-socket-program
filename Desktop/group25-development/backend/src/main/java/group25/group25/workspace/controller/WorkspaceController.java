package group25.group25.workspace.controller;

import group25.group25.usermanagement.model.User;
import group25.group25.workspace.model.UserAccessWorkspace;
import group25.group25.workspace.model.Workspace;
import group25.group25.workspace.repository.WorkspaceRepository;
import group25.group25.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    WorkspaceRepository workspaceRepository;

    @GetMapping(path = "/getAllWorkspaces", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Workspace> getAllWorkspaces() {
        return workspaceService.findAll();
    }

    @GetMapping(path = "/getAssignedUsers", consumes = "application/json", produces = "application/json")
    public Set<User> getAssignedUsers(@RequestBody Workspace workspace) {
        Optional<Workspace> w = workspaceRepository.findById(workspace.getId());
        if (w.isPresent()) {
            Workspace ws = w.get();
            return ws.getAssignedUsers();
        }

        // Workspace not found, return null
        System.out.println("Could not retrieve assigned users from workspace with id " + workspace.getId() + ", workspace not found");
        return null;
    }

    @PostMapping(path = "/addWorkspace", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:3000")
    public String addWorkspace(@RequestBody Workspace workspace) {
        workspaceService.saveWorkspace(workspace);
        return "Saved workspace data successfully with id " + workspace.getId();
    }

    @PutMapping(path = "/assignWorkspaceUser", consumes = "application/json", produces = "application/json")
    public String assignWorkspaceUser(@RequestBody UserAccessWorkspace access) {
        if (workspaceService.assignWorkspaceUser(access)) {
            // User/workspace pair was added to database
            return "Successfully assigned user with id "
                    + access.getUserId()
                    + " to workspace with id "
                    + access.getWorkspaceId();
        }
        else {
            // User/workspace pair already exists
            return "Could not assigned user with id "
                    + access.getUserId()
                    + " to workspace with id "
                    + access.getWorkspaceId()
                    + "; pair already exists";
        }
    }
}
