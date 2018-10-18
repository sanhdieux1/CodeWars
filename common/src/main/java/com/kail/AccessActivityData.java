package com.kail;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccessActivityData {

    private String userCode;

    private String brandName;

    private String functionName;

    private String functionParams;

    private String description;

    private long accessTime;

    private String initGroupName;

    public String getTruncatedFunctionName() {
        return functionName;
    }

}
