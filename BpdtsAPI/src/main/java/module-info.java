module bpdtsclient {
	exports com.bpdts.client;
	exports com.bpdts.client.model;
	exports com.bpdts.api;
	opens com.bpdts.client.model;
	opens com.bpdts;
	requires java.net.http;
	requires com.fasterxml.jackson.databind;
	requires spring.web;
	requires spring.context;
	requires spring.boot.autoconfigure;
	requires spring.boot;
	requires spring.core;
	requires spring.beans;
}