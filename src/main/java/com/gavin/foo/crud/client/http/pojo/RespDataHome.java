package com.gavin.foo.crud.client.http.pojo;

import java.util.List;

import lombok.Data;

@Data
public class RespDataHome extends BaseResponseData {
	private String userId;
	private String id;
	private String name;
	private List<Room> rooms;
	private List<Object> favorites;
	
	@Data
	class Room {
		private String id;
		private String homeId;
		private String name;
		private String icon;
		private List<Object> groups;
		private List<Object> devices;
	}

}
