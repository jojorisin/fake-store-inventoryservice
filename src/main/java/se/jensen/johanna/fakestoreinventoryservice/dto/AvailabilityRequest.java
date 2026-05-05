package se.jensen.johanna.fakestoreinventoryservice.dto;

import java.util.Set;

public record AvailabilityRequest(
    Set<CartItemRequest> cartItemRequests
) {

}
