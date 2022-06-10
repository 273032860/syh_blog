package com.syh.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LinkVo {
    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;


}
