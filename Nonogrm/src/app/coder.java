package app;

class coder {
    public static void main(String[] args) {
        // System.out.println("#!/bin/bash");
        for (int i = 1; i <= 180; i++) {
            String help = String.format("Nonogram-%03d-regular-table.xml", i);
            System.out.println(help);
            // System.out.printf("minisat instances/big/Nonogram-%03d-regular-table.xml output/minisatOutput/Nonogram-%03d.out\n", i, i);
        }
    }
}