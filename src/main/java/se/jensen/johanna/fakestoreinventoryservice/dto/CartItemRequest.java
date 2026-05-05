package se.jensen.johanna.fakestoreinventoryservice.dto;

import java.util.UUID;

public record CartItemRequest(
    UUID productId,
    Integer quantity
) {

}
