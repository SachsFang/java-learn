package com.fang.springboot.common.interfact;

import java.io.Serializable;

public interface Entity extends Serializable {

    String DEFAULT_GENERATOR = "uuid";

    String DEFAULT_STRATEGY = "uuid";

}