package com.bancociudad.utils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
// Importaciones para manejo de fechas
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UnirArchivos {
    public static void main(String[] args) {

        // ✅ Ventana para seleccionar archivo principal
        String file1 = selectFile("Seleccione el archivo principal (archivo1.txt)");
        if (file1 == null) {
            System.out.println("No se seleccionó el archivo principal.");
            return;
        }

        String file2 = selectFile("Seleccione el archivo adicional (archivo2.txt)");
        if (file2 == null) {
            System.out.println("No se seleccionó el archivo adicional.");
            return;
        }


        // ✅ Solicitar tipo de producto
        String productTypeInput = requestProductType();
        String formattedDate = getDateByProductType(productTypeInput);


        // ⚙️ Posiciones
        int datePosition = 0;
        int productTypePosition = 8;
        int companyCodePosition = 11;
        int firstFillerPosition = 48;
        int operationCountPosition = 66;
        int totalAmountPosition = 71;
        int amountPosition = 87;

        try {
            Path mainFilePath = Paths.get(file1);
            Path additionalFilePath = Paths.get(file2);

            List<String> mainFileLines = Files.readAllLines(mainFilePath);
            if (mainFileLines.isEmpty()) {
                System.err.println("El archivo principal está vacío.");
                return;
            }

            String headerLine = mainFileLines.get(0);

            // ✅ Extraer filler
            if (headerLine.length() < firstFillerPosition + 5) {
                System.err.println("El header no tiene suficiente longitud para extraer firstFiller.");
                return;
            }

            String fillerStr = headerLine.substring(firstFillerPosition, firstFillerPosition + 5);
            int fillerValue;
            try {
                fillerValue = Integer.parseInt(fillerStr);
            } catch (NumberFormatException e) {
                System.err.println("firstFiller inválido en el header: " + fillerStr);
                return;
            }

            boolean isLargeAccount = fillerValue > 0;
            System.out.println("Número de cuenta menor a 50 millones");
            System.out.println("firstFiller = " + fillerValue + "  isLargeAccount = " + isLargeAccount);

            processFile(mainFileLines, additionalFilePath, mainFilePath,
                    operationCountPosition, totalAmountPosition, amountPosition,
                    datePosition, productTypePosition,productTypeInput,companyCodePosition, isLargeAccount, formattedDate);

        } catch (IOException e) {
            System.err.println("Error al procesar los archivos: " + e.getMessage());
        }
    }

    private static void processFile(List<String> mainFileLines, Path additionalFilePath, Path mainFilePath,
                                    int operationCountPosition, int totalAmountPosition, int amountPosition,
                                    int datePosition, int productTypePosition, String productTypeInput, int companyCodePosition,
                                    boolean isLargeAccount,String formattedDate) throws IOException {

        List<String> additionalFileLines = Files.readAllLines(additionalFilePath);
        mainFileLines.addAll(additionalFileLines);

        String headerLine = mainFileLines.get(0);

        // ✅ Actualizar tipo de producto
        headerLine = headerLine.substring(0, productTypePosition)
                + productTypeInput
                + headerLine.substring(productTypePosition + 3);


        // ✅ Actualizar fecha
        int dateLength = 8;
        headerLine = headerLine.substring(0, datePosition) + formattedDate + headerLine.substring(datePosition + dateLength);

        // ✅ Actualizar cantidad operaciones
        int newOperationCount = mainFileLines.size() - 1;
        headerLine = headerLine.substring(0, operationCountPosition)
                + String.format("%05d", newOperationCount)
                + headerLine.substring(operationCountPosition + 5);

        // ✅ Actualizar total importe
        long totalAmountCents = 0;
        for (int i = 1; i < mainFileLines.size(); i++) {
            String line = mainFileLines.get(i);
            if (line.length() >= amountPosition + 12) {
                String amountStr = line.substring(amountPosition, amountPosition + 12);
                try {
                    totalAmountCents += Long.parseLong(amountStr);
                } catch (NumberFormatException e) {
                    System.err.println("Importe inválido en línea " + (i + 1) + ": " + amountStr);
                }
            } else {
                System.err.println("Línea demasiado corta para importe en línea " + (i + 1));
            }
        }

        headerLine = headerLine.substring(0, totalAmountPosition)
                + String.format("%012d", totalAmountCents)
                + headerLine.substring(totalAmountPosition + 12);

        mainFileLines.set(0, headerLine);

        // ✅ Renombrar archivo
        String productType = productTypeInput;
        String companyCode = headerLine.substring(companyCodePosition, companyCodePosition + 6);
        String newFileName = productType + "_" + companyCode + "_" + formattedDate + ".txt";

        // ✅ Esta línea genera el archivo donde se ejecuta el .jar
        Path renamedPath = Paths.get(System.getProperty("user.dir")).resolve(newFileName);
        Files.write(renamedPath, mainFileLines); // Sobrescribe si ya existe

        if (isLargeAccount) {
            System.out.println("Aplicando lógica especial para firstFiller > 0...");
        }

        System.out.println("Archivo actualizado y renombrado a: " + renamedPath.getFileName());
    }


    private static String getDateByProductType(String tipoProducto) {
        if (tipoProducto.equals("001")) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }

        LocalDate enteredDate = null;
        boolean valid = false;

        while (!valid) {
            String inputDate = JOptionPane.showInputDialog(null, "Ingrese la fecha en formato dd/mm/aaaa:");
            if (inputDate == null || inputDate.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fecha inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                continue; // vuelve a pedir la fecha
            }

            try {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                enteredDate = LocalDate.parse(inputDate.trim(), inputFormatter);
                LocalDate today = LocalDate.now();

                if (!enteredDate.isAfter(today)) {
                    JOptionPane.showMessageDialog(null,
                            "El tipo de producto ingresado, sólo admite fechas futuras.",
                            "Fecha inválida",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    valid = true;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null,
                        "Formato incorrecto. Debe ser dd/mm/aaaa.",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return enteredDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }


    private static String requestProductType() {
        String productTypeInput = null;
        boolean validProductType = false;

        while (!validProductType) {
            productTypeInput = JOptionPane.showInputDialog(null, "Ingrese tipo de producto (001 a 005):");

            if (productTypeInput == null || !productTypeInput.matches("\\d{3}")) {
                JOptionPane.showMessageDialog(null, "Tipo de producto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (productTypeInput) {
                case "001":
                case "002":
                case "003":
                case "004":
                case "005":
                    validProductType = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo de producto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return productTypeInput;
    }


    private static String selectFile(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return fileChooser.getSelectedFile().getAbsolutePath();
    }


}
