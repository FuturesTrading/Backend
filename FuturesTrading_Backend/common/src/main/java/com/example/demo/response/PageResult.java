package com.example.demo.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    List<T> content;

    int number;

    int totalPages;
}
