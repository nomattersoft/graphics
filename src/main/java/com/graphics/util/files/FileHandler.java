package com.graphics.util.files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphics.model.Polygon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static List<Polygon> readFile(String fileName) {
		try {
			Path filePath = Paths.get(fileName);
			return MAPPER.readValue(Files.readAllLines(filePath).stream()
					.map(Object::toString)
					.collect(Collectors.joining()), new TypeReference<List<Polygon>>() {});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
