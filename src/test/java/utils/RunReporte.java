package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class RunReporte {

	public static void main(String[] args) {
		// Ruta al JSON generado por Cucumber
		String rutaJson = "target/cucumber-reports/cucumber.json";

		// Extraer el tag del JSON
		String tag = extraerTagDelJson(rutaJson);
		if (tag == null || tag.isEmpty()) {
			tag = "DefaultTag"; // Si no hay tag, usar un valor por defecto
		}

		// Obtener el nombre único del reporte
		String nombreReporte = obtenerNombreReporte(tag);
		String rutaReportes = "reports/" + nombreReporte;

		// Formatear el archivo JSON
		formatearJson(rutaJson);

		// Generar el reporte de Cucumber
		generarReporteCucumber(rutaJson, rutaReportes);

		System.out.println("Reporte generado en la carpeta: " + rutaReportes);
	}

	private static String extraerTagDelJson(String rutaJson) {
		try {
			File archivoJson = new File(rutaJson);

			if (!archivoJson.exists() || archivoJson.length() == 0) {
				throw new IllegalStateException("El archivo cucumber.json no existe o está vacío.");
			}

			// Leer el archivo JSON
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(archivoJson);

			// Buscar el primer tag
			JsonNode tagsNode = rootNode.at("/0/elements/0/tags/0/name");
			if (tagsNode.isTextual()) {
				return tagsNode.asText().replace("@", ""); // Quitar el '@' del tag
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al extraer el tag del archivo JSON.");
		}
		return null;
	}

	private static String obtenerNombreReporte(String tag) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		return tag + "_" + timestamp;
	}

	private static void formatearJson(String rutaJson) {
		try {
			File archivoJson = new File(rutaJson);

			if (!archivoJson.exists()) {
				throw new IllegalStateException("El archivo cucumber.json no existe: " + rutaJson);
			}

			if (archivoJson.length() == 0) {
				throw new IllegalStateException("El archivo cucumber.json está vacío o no se generó correctamente.");
			}

			// Leer el JSON existente
			ObjectMapper mapper = new ObjectMapper();
			Object jsonData = mapper.readValue(archivoJson, Object.class);

			// Escribir el JSON con formato
			ObjectWriter writer = mapper.writer(SerializationFeature.INDENT_OUTPUT);
			writer.writeValue(archivoJson, jsonData);

			System.out.println("El archivo JSON ha sido formateado correctamente: " + rutaJson);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al formatear el archivo JSON: " + rutaJson);
		}
	}

	private static void generarReporteCucumber(String rutaJson, String rutaReportes) {
		try {
			File jsonFile = new File(rutaJson);

			if (!jsonFile.exists() || jsonFile.length() == 0) {
				throw new IllegalStateException("El archivo cucumber.json está vacío o no se generó correctamente.");
			}

			File reportOutputDirectory = new File(rutaReportes);
			if (!reportOutputDirectory.exists()) {
				reportOutputDirectory.mkdirs();
			}

			List<String> jsonFiles = Collections.singletonList(rutaJson);

			Configuration configuration = new Configuration(reportOutputDirectory, "Proyecto Adintar");
			configuration.addClassifications("Sistema Operativo", "Windows 10");
			configuration.addClassifications("Navegador", "Chrome");
			configuration.addClassifications("Ambiente", "Testing");

			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();

			System.out.println("Reporte de Cucumber generado correctamente en: " + rutaReportes);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al generar el reporte de Cucumber.");
		}
	}
}
