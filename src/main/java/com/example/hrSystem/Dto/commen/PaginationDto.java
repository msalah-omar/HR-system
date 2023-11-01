package com.example.hrSystem.Dto.commen;

import lombok.Data;

@Data
public class PaginationDto {
    private int totalPages;
    private long itemCount;
    private int currentPage;
    private int size;

    public PaginationDto()
    {
    }

    public PaginationDto(int totalPages, long itemCount, int currentPage, int size)
    {
        this.totalPages = totalPages;
        this.itemCount = itemCount;
        this.currentPage = currentPage;
        this.size = size;
    }
}
