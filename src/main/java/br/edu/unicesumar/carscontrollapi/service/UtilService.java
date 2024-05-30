package br.edu.unicesumar.carscontrollapi.service;

import java.util.Random;

public class UtilService {
    UtilService() {
        throw new IllegalStateException("Utility class");
    }
    public static String generateRandomCode() {
        int length = 6;
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // Gera um número aleatório entre 0 e 1 para decidir se será um número ou uma letra
            boolean isNumber = random.nextBoolean();

            if (isNumber) {
                // Gera um número aleatório entre 0 e 9 e o converte para caractere
                code.append((char) ('0' + random.nextInt(10)));
            } else {
                // Gera um número aleatório entre 0 e 25 e o converte para caractere maiúsculo
                code.append((char) ('A' + random.nextInt(26)));
            }
        }

        return code.toString();
    }
}
