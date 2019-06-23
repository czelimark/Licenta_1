package business_layer.services;

import business_layer.dto.ProjectDto;

import java.util.List;

public interface IProjectService {

    ProjectDto getProject(Integer id);

    List<ProjectDto> getProjects(Integer id);

    void updateProject(ProjectDto projectDto);

    void deleteProject(Integer id);

    void addProject(ProjectDto projectDto);
}
