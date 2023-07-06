package com.tohome.bundlebundle.order.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private Integer id;
    private Integer memberId;
    private Integer groupId;

    public OrderVO(Integer memberId, Integer groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
    }
}
