package web_layer.controllers;

import business_layer.dto.ProjectDto;
import business_layer.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> putProject(@RequestBody ProjectDto projectDto) {
        projectService.addProject(projectDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        projectService.deleteProject(Integer.valueOf(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/updateProject")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDto projectDto) {
        projectService.updateProject(projectDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ProjectDto getProject(@PathVariable Integer id) {
        return projectService.getProject(id);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<?> getProjects(@PathVariable String id) {
        List<ProjectDto> projects = projectService.getProjects(Integer.valueOf(id));
        return ResponseEntity.ok(projects);
    }
}
