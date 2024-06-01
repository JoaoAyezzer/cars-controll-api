package br.edu.unicesumar.carscontrollapi.dto;

public record AddressCreate(
        String street,
        String district,
        String city,
        String state,
        String zip,
        String country
) {
}
