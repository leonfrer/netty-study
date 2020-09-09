package com.github.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufDemo {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		DataInfo.Student leon = DataInfo.Student.newBuilder()
				.setName("leon")
				.setAge(18)
				.setAddress("xxx str.")
				.build();

		byte[] bytes = leon.toByteArray();

		DataInfo.Student student = DataInfo.Student.parseFrom(bytes);
		System.out.println(student.getAddress());
		System.out.println(student.getAge());
		System.out.println(student.getName());
	}
}
