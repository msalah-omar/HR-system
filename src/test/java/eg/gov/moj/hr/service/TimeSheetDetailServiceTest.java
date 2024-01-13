package eg.gov.moj.hr.service;


import com.example.hrSystem.Service.TimeSheetDetailService;

import com.example.hrSystem.entity.Employees;
import com.example.hrSystem.entity.TimeSheetDetail;

import com.example.hrSystem.repository.TimeSheetDetailRepository;
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

public class TimeSheetDetailServiceTest
{
    private TimeSheetDetailRepository timeSheetDetailRepository = mock(TimeSheetDetailRepository.class);
    private TimeSheetDetailService timeSheetDetailService = new TimeSheetDetailService(timeSheetDetailRepository);

    private TimeSheetDetail getTimeSheetDetail(){
        TimeSheetDetail timeSheetDetail = new TimeSheetDetail();
        timeSheetDetail.setId(new Random().nextInt());
        timeSheetDetail.setEmployees(new Employees());
        return timeSheetDetail;
    }

    @Test
    public void testGetAll(){
        //Prep
        List<TimeSheetDetail> timeSheetDetailList = new ArrayList<>();
        timeSheetDetailList.add(getTimeSheetDetail());
        timeSheetDetailList.add(getTimeSheetDetail());
        Page<TimeSheetDetail> timeSheetDetailPage = new PageImpl(timeSheetDetailList);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //Test
        Mockito.when(timeSheetDetailRepository.findAll(pageRequest)).thenReturn(timeSheetDetailPage);
        Page<TimeSheetDetail> all = timeSheetDetailService.getAll(0, 10);

        //Assert
        assertThat(all.getContent().equals(timeSheetDetailList));
    }

    @Test
    public void testGetById() {
        //Prep
        TimeSheetDetail timeSheetDetail = getTimeSheetDetail();

        //Test
        Mockito.when(timeSheetDetailRepository.findById(timeSheetDetail.getId())).thenReturn(Optional.of(timeSheetDetail));

        //Assert
        assertThat(timeSheetDetailService.getById(timeSheetDetail.getId())).isEqualTo(Optional.of(timeSheetDetail));
    }

    @Test
    public void testSave(){
        //Prep
        TimeSheetDetail timeSheetDetail = getTimeSheetDetail();


        //Test
        Mockito.when(timeSheetDetailRepository.save(timeSheetDetail)).thenReturn(timeSheetDetail);

        //Assert
        assertThat(timeSheetDetailService.save(timeSheetDetail)).isEqualTo(timeSheetDetail);

    }

    @Test
    public void testUpdate(){
        //Prep
        TimeSheetDetail timeSheetDetail = getTimeSheetDetail();

        //Test
        Mockito.when(timeSheetDetailRepository.findById(1)).thenReturn(Optional.of(timeSheetDetail));
        Mockito.when(timeSheetDetailRepository.save(timeSheetDetail)).thenReturn(timeSheetDetail);
        timeSheetDetailService.update(timeSheetDetail);

        //Assert
        verify(timeSheetDetailRepository).save(timeSheetDetail);
    }

    @Test
    public void testDelete(){
        //Prep
        TimeSheetDetail timeSheetDetail = getTimeSheetDetail();

        //Test
        Mockito.when(timeSheetDetailRepository.findById(timeSheetDetail.getId())).thenReturn(Optional.of(timeSheetDetail));

        //Assert
        timeSheetDetailService.delete(timeSheetDetail);
        verify(timeSheetDetailRepository).delete(timeSheetDetail);
    }
}
