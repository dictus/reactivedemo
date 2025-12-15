package com.spr.reactivedemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GroupingService {


    public static void main(String[] args) {


        Supplier<GroupingService> supplier = GroupingService::new;
        GroupingService service = supplier.get();
        service.gp();
        System.out.println(" res : ");
    }
    class T1 {

    }

    public void gp() {

        List<String> as = new ArrayList<>();
        as.add("test");
        as.add("test2");

        Map<String, List<String>> byDept = as.stream()
                .collect(Collectors.groupingBy(String::toLowerCase));

        byDept.forEach((s, strings) -> {
            System.out.println(s);
            System.out.println(strings);
        });


    }


}
