package com.tohome.bundlebundle.fcm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FcmVO {
	private int token;
	private int groupId;
	private int memberId;
}
