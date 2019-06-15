package web_layer.controllers;

import business_layer.dto.ProjectDto;
import business_layer.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class ProjectRestController {

    @Autowired
    private final IProjectService projectService;

    public ProjectRestController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PutMapping("/addProject")
    public ResponseEntity<?> putProject(@RequestBody Integer id, @RequestBody ProjectDto projectDto) {
        projectService.addProject(id, projectDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/deleteProject")
    public ResponseEntity<?> deleteProject(@RequestBody ProjectDto projectDto) {
        projectService.deleteProject(projectDto);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/updateProject")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDto projectDto) {
        projectService.updateProject(projectDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{projectName}")
    public ProjectDto getProject(@PathVariable Integer id) {
        return projectService.getProject(id);
    }

    @GetMapping("/projects")
    public ResponseEntity<?> getProjects(Integer id) {
        List<ProjectDto> projects = projectService.getProjects(id);
        return ResponseEntity.ok(projects);
    }
}
