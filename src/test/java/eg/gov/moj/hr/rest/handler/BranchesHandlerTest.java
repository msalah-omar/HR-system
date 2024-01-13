package eg.gov.moj.hr.rest.handler;

import com.example.hrSystem.Dto.BranchesDto;

import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Dto.commen.PaginationDto;
import com.example.hrSystem.Service.BranchesService;

import com.example.hrSystem.entity.Branches;

import com.example.hrSystem.handler.BranchesHandler;

import com.example.hrSystem.mapper.BranchesMapper;

import com.example.hrSystem.mapper.PaginationMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BranchesHandlerTest
{

    private BranchesService branchesService = mock(BranchesService.class);
    private BranchesMapper mapper = mock(BranchesMapper.class);
    private PaginationMapper paginationMapper = mock(PaginationMapper.class);
    private BranchesHandler branchesHandler = new BranchesHandler(branchesService, mapper, paginationMapper);


    @Test
    public void testGetAll()
    {
        List<Branches> branchesList = mock(List.class);
        List<BranchesDto> branchesDtoList = mock(List.class);
        Page<Branches> branchesPage = mock(Page.class);
        PaginationDto paginationDto = mock(PaginationDto.class);
        Branches branches = mock(Branches.class);

        Mockito.when(branchesPage.getContent()).thenReturn(branchesList);
        Mockito.when(paginationMapper.toPaginationDto(branchesPage)).thenReturn(paginationDto);
        Mockito.when(branchesService.getAll(0, 10)).thenReturn(branchesPage);
        Mockito.when(mapper.toDto(branchesList)).thenReturn(branchesDtoList);

        PaginatedResultDto<BranchesDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(branchesDtoList);
        paginatedResult.setPagination(paginationDto);

        ResponseEntity<?> response = branchesHandler.getAll(0, 10);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().body(paginatedResult);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetById()
    {
        Branches branches = mock(Branches.class);
        BranchesDto branchesDto = mock(BranchesDto.class);

        Mockito.when(branchesService.getById(branches.getId())).thenReturn(Optional.of(branches));
        Mockito.when(mapper.toDto(branches)).thenReturn(branchesDto);

        ResponseEntity<?> response = branchesHandler.getById(branches.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(branchesDto);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testSave()
    {
        Branches branches = mock(Branches.class);
        BranchesDto branchesDto = mock(BranchesDto.class);

        Mockito.when(mapper.toEntity(branchesDto)).thenReturn(branches);
        Mockito.when(branchesService.save(branches)).thenReturn(branches);
        Mockito.when(mapper.toDto(branches)).thenReturn(branchesDto);

        ResponseEntity<?> response = branchesHandler.save(branchesDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.created(URI.create("/branches/" + branches.getId())).body(branchesDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdate()
    {
        Branches branches = mock(Branches.class);
        BranchesDto branchesDto = mock(BranchesDto.class);

        Mockito.when(branchesService.getById(branches.getId())).thenReturn(Optional.of(branches));
        Mockito.when(mapper.updateEntityFromDto(branchesDto, branches)).thenReturn(branches);

        ResponseEntity<?> response = branchesHandler.update(branches.getId(), branchesDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void testDelete()
    {
        Branches branches = mock(Branches.class);

        Mockito.when(branchesService.getById(branches.getId())).thenReturn(Optional.of(branches));

      ResponseEntity<?> response = branchesHandler.delete(branches.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.noContent().build();

       assertEquals(expectedResponse,response);
    }
}
