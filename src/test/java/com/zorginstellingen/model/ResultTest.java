package com.zorginstellingen.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @Test
    void getSetId() {
        Result result = new Result();
        result.setId(1L);
        Long id = result.getId();
        assertEquals(1L, id, "Expected id = 1L");
    }

    @Test
    void getResultDescription() {
        Result result = new Result();
        result.setId(1L);
        Long id = result.getId();
        result.setResultDescription("Test result");
        String checkResult = result.getResultDescription();
        assertEquals("Test Result", checkResult, "Expected result = Test result");

    }

    @Test
    void getCreatedDate() {
        Result result = new Result();
        Date date = new Date();
        result.setCreatedDate(date);
        Date checkDate = result.getCreatedDate();
        assertEquals(date, checkDate, "expected created date is same as prev");
    }

}