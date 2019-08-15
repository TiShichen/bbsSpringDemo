package demo.springboot.community.bbs.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ShichenLi
 * @Date 8/15/2019 10:21
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = (totalCount / size) + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if ((page - i) > 0) {
                pages.add(0, page - i);
            }
            if ((page + i) <= totalPage) {
                pages.add(page + i);
            }
        }

        // Whether or not show previous button
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // Whether or not show next button
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // Whether or not show FirstPage button
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // Whether or not show EndPage button
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
