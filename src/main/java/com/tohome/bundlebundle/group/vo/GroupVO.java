package com.tohome.bundlebundle.group.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupVO {
    private Integer id;
    private final Integer groupOwnerId;
    private final String token;
}