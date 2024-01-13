package eg.gov.moj.hr.service;



import com.example.hrSystem.Service.ProjectService;
import com.example.hrSystem.entity.Project;

import com.example.hrSystem.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProjectServiceTest
{
    private ProjectRepository projectRepository = mock(ProjectRepository.class);
    private ProjectService projectService = new ProjectService(projectRepository);
    private Project getProject(){
        Project project = new Project();
        project.setId(new Random().nextInt());
        project.setArabicName("project");
        return project;
    }
    @Test
    public void testGetById() {
        //Prep
        Project project = getProject();

        //Test
        Mockito.when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));

        //Assert
        assertThat(projectService.getById(project.getId())).isEqualTo(Optional.of(project));
    }

    @Test
    public void testGetAll(){
        //Prep
        List<Project> projectList = new ArrayList<>();
        projectList.add(getProject());
        projectList.add(getProject());
        Page<Project> projectPage = new PageImpl(projectList);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //Test
        Mockito.when(projectRepository.findAll(pageRequest)).thenReturn(projectPage);
        Page<Project> all = projectService.getAll(0, 10);

        //Assert
        assertThat(all.getContent().equals(projectList));

    }

    @Test
    public void testSave(){
        //Prep
        Project project = getProject();

        //Test
        Mockito.when(projectService.save(project)).thenReturn(project);

        //Assert
        assertThat(projectService.save(project)).isEqualTo(project);

    }

    @Test
    public void testUpdate(){
        //Prep
        Project project = getProject();

        //Test
        Mockito.when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        project.setArabicName("update-asd");
        Mockito.when(projectRepository.save(project)).thenReturn(project);
        projectService.update(project);

        //Assert
        verify(projectRepository).save(project);
    }

    @Test
    public void testDelete()
    {
        //Prep
        Project project = getProject();

        //Test
        Mockito.when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));

        //Assert
        projectService.delete(project);
        verify(projectRepository).delete(project);
    }


}
