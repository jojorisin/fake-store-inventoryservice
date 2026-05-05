package se.jensen.johanna.fakestoreinventoryservice.dto;

import java.util.Set;

public record AvailabilityResponse(
    Set<CartItemRequest> updatedCart,
    Boolean allAvailable
) {

}
