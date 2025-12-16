package com.spr.reactivedemo.module;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("EVENT")
@Data
@Builder
public class Event {
    private Long id;
    private String name;
    private String location;
    private Integer cost;
    private Integer duration;

}
