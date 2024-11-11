package com.app.chuushajou.libs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import java.util.Map;

@AllArgsConstructor

public class PageInfo {
    private int currentPage;
    private int totalPage;
    private int limit;


    public static <K, V> Map<K, V> of(Page<?> page,int currentPage,int limit) {

        int totalPage = page.getTotalPages();
        int nextPage = currentPage+1;
        int previousPage = currentPage-1;
        boolean hasNext = nextPage <= totalPage;
        boolean hasPrevious = previousPage > 0;

        return ResMap.of(
                "status", "success",
                "filter", ResMap.of(
                        "currentPage", currentPage,
                        "limit", limit),
                "data", page.getContent(),
                "pages", ResMap.of(
                        "totalRows", page.getSize(),
                        "totalPage", totalPage,
                        "next", nextPage,
                        "previous", previousPage,
                        "hasNext", hasNext,
                        "hasPrevious", hasPrevious

                )
        );
    }

}
