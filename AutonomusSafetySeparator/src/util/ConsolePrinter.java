package util;

import model.Dataset;
import model.LabeledPoint;
import model.SeparatorLine;

import java.util.List;

public class ConsolePrinter {

    private ConsolePrinter() {
    }

    public static void printDataset(Dataset dataset) {
        System.out.println("=== VERI KUMESI ===");
        for (LabeledPoint point : dataset.getPoints()) {
            System.out.println(point);
        }
    }

    public static void printLine(SeparatorLine line) {
        System.out.println("Ayristirici Dogru Denklemi: " + line);
        System.out.printf("Minimum Margin: %.6f%n", line.getMargin());
        System.out.printf("Guvenlik Koridoru Genisligi (yaklasik): %.6f%n", 2 * line.getMargin());
    }

    public static void printSupportVectors(List<LabeledPoint> supportVectors) {
        System.out.println("\n=== DESTEK VEKTORLERI / EN YAKIN NOKTALAR ===");
        if (supportVectors.isEmpty()) {
            System.out.println("Destek vektoru bulunamadi.");
            return;
        }

        for (LabeledPoint point : supportVectors) {
            System.out.println(point);
        }
    }
}