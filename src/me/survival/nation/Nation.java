package me.survival.nation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jakob on 31.05.2016.
 */

public enum Nation {



    N1("", Arrays.asList("")),
    N2("", Arrays.asList(""));

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
