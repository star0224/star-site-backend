package com.star.site.dto;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ArticleStatisticDTO {
    private AtomicInteger articleTotal = new AtomicInteger(0);

    private AtomicInteger articleDay = new AtomicInteger(0);

    private AtomicInteger articleWeek = new AtomicInteger(0);

    private AtomicInteger articleMonth = new AtomicInteger(0);

    public void articleTotalGAI() {
        articleTotal.getAndIncrement();
    }

    public void articleDayGAI() {
        articleDay.getAndIncrement();
    }

    public void articleWeekGAI() {
        articleWeek.getAndIncrement();
    }

    public void articleMonthGAI() {
        articleMonth.getAndIncrement();
    }
}
