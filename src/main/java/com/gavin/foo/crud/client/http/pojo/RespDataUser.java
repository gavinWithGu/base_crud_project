package com.gavin.foo.crud.client.http.pojo;

import lombok.Data;

@Data
public class RespDataUser extends BaseResponseData {
	private String userToken;
	private String uid;
	private String userId;
	private String refreshToken;
	private long createdAt;
	private long expiredAt;

	private ResponseDataJwt jwtAuthenticationDto;

	@Data
	class ResponseDataJwt {
		private String token;
	}
}
