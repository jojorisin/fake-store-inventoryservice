package se.jensen.johanna.fakestoreinventoryservice.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.johanna.fakestoreinventoryservice.dto.AvailabilityRequest;
import se.jensen.johanna.fakestoreinventoryservice.dto.AvailabilityResponse;
import se.jensen.johanna.fakestoreinventoryservice.dto.ReservationRequest;
import se.jensen.johanna.fakestoreinventoryservice.dto.ReservationResponse;
import se.jensen.johanna.fakestoreinventoryservice.service.ReservationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping("/reserve-cart")
  public ResponseEntity<ReservationResponse> reserveCart(@AuthenticationPrincipal Jwt jwt,
      @RequestBody @Valid ReservationRequest request) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reservationService.reserveCart(jwt, request));
  }

  @PostMapping("/{reservationId}/reduce-stock")
  public ResponseEntity<Void> commitReservation(@PathVariable UUID reservationId) {
    reservationService.reduceStock(reservationId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/check-stock")
  public ResponseEntity<AvailabilityResponse> checkAvailability(
      @RequestBody @Valid AvailabilityRequest request) {
    return ResponseEntity.ok()
        .body(reservationService.getCartAvailability(request.cartItemRequests()));
  }

}
