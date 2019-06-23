package business_layer.services.impl;

import business_layer.dto.ProjectDto;
import business_layer.mappers.PortfolioMapper;
import business_layer.mappers.ProjectMapper;
import business_layer.services.IProjectService;
import data_layer.domain.Portfolio;
import data_layer.domain.Project;
import data_layer.repositories.IPortfolioRepository;
import data_layer.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IPortfolioRepository portfolioRepository;

    @Override
    public List<ProjectDto> getProjects(Integer id) {
        List<Project> projects = projectRepository.findByPortfolio(id);
        return ProjectMapper.toDtoList(projects);
    }

    @Override
    public ProjectDto getProject(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ProjectMapper.toDto(project);
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        Project project = projectRepository.findById(projectDto.getId()).orElseThrow(ResourceNotFoundException::new);
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setIssueDate(projectDto.getIssueDate());
        project.setClosingDate(projectDto.getClosingDate());
        project.setEstimatedPrice(projectDto.getEstimatedPrice());
        project.setActualPrice(projectDto.getActualPrice());
        project.setDifference(projectDto.getDifference());
        project.setComments(projectDto.getComments());
        project.setPortfolio(PortfolioMapper.toEntity(projectDto.getPortfolio()));
        projectRepository.flush();
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteProjectById(id);
        projectRepository.flush();
    }

    @Override
    public void addProject(ProjectDto projectDto) {
        Project project = ProjectMapper.toEntity(projectDto);
        projectRepository.save(project);
        projectRepository.flush();
    }
}
