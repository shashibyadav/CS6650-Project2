package com.project.two.server;

public class ProjectEnums {
	public enum ConnectionType {
		UDP,
		TCP,
		RPC,
		ALL
	};
	public enum MethodType {
		PUT,
		GET,
		DELETE,
		STOP,
		RUN
	}
	public enum RequestKeys {
		type,
		data,
		authId
	}
	public enum ServiceKeys {
		StoreService
	}
	public enum DataKeys {
		Priority
	}
}