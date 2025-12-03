package com.spr.reactivedemo.module;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("EVENT")
@Data
public class Event {
    private Long id;
    private String name;
    private String location;
    private Integer cost;
    private Integer duration;

}
