package me.survival.nation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jakob on 31.05.2016.
 */

public enum Nation {


    N1("Aincrad", Arrays.asList("")),
    N2("Trivia", Arrays.asList("Die Nation Trivia ","hat die fähichkeit","schneller an verschiedene ","Orte reisen"));

    private String name;
    private List<String> desc;

    Nation(String name, List<String> desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public List<String> getDesc() {
        return desc;
    }

}
