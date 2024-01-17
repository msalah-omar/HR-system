package eg.gov.moj.hr.service;

import com.example.hrSystem.Service.BranchesService;

import com.example.hrSystem.entity.Branches;


import com.example.hrSystem.entity.Employees;
import com.example.hrSystem.repository.BranchesRepository;
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

public class BranchesServiceTest
{
    private BranchesRepository branchesRepository = mock(BranchesRepository.class);
    private BranchesService branchesService = new BranchesService(branchesRepository);

    private Branches getBranches(){
        Branches branches = new Branches();
        branches.setId(new Random().nextInt());
        branches.setArabicName("branches");
        return branches;
    }

    @Test
    public void testGetById() {
        //Prep
        Branches branches = getBranches();

        //Test
        Mockito.when(branchesRepository.findById(branches.getId())).thenReturn(Optional.of(branches));

        //Assert
        assertThat(branchesService.getById(branches.getId())).isEqualTo(Optional.of(branches));
    }

    @Test
    public void testSave(){
        //Prep
        Branches branches = getBranches();

        //Test
        Mockito.when(branchesRepository.save(branches)).thenReturn(branches);

        //Assert
        assertThat(branchesService.save(branches)).isEqualTo(branches);

    }

    @Test
    public void testGetAll(){
        //Prep
        List<Branches> branchesList = new ArrayList<>();
        branchesList.add(getBranches());
        branchesList.add(getBranches());
        Page<Branches> branchesPage = new PageImpl(branchesList);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //Test
        Mockito.when(branchesRepository.findAll(pageRequest)).thenReturn(branchesPage);
        Page<Branches> all = branchesService.getAll(0, 10);

        //Assert
        assertThat(all.getContent().equals(branchesList));

    }

    @Test
    public void testUpdate(){
        //Prep
        Branches branches = getBranches();

        //Test
        Mockito.when(branchesRepository.findById(1)).thenReturn(Optional.of(branches));
        branches.setArabicName("update-asd");
        Mockito.when(branchesRepository.save(branches)).thenReturn(branches);
        branchesService.update(branches);

        //Assert
        verify(branchesRepository).save(branches);
    }

    @Test
    public void testDelete(){
        //Prep
        Branches branches = getBranches();

        //Test
        Mockito.when(branchesRepository.findById(branches.getId())).thenReturn(Optional.of(branches));

        //Assert
        branchesService.delete(branches);
        verify(branchesRepository).delete(branches);
    }


}
