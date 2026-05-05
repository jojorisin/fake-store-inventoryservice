package se.jensen.johanna.fakestoreinventoryservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationItem {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID reservationItemId;


  private UUID productId;

  private Integer quantity;

  public static ReservationItem create(UUID productId, Integer quantity) {
    return ReservationItem.builder()
        .productId(productId)
        .quantity(quantity)
        .build();
  }

}
