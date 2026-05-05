package se.jensen.johanna.fakestoreinventoryservice.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.jensen.johanna.fakestoreinventoryservice.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

  @EntityGraph(attributePaths = "reservedItems")
  Optional<Reservation> findByReservationId(UUID reservationId);

  @Query("SELECT ri.productId as productId, SUM(ri.quantity) as count " +
      "FROM Reservation r JOIN r.reservedItems ri " +
      "WHERE ri.productId IN :productIds " +
      "AND r.expiresAt > :now " +
      "GROUP BY ri.productId")
  List<ReservationCountProjection> countActiveReservationsByProductIds(
      @Param("productIds") Set<UUID> productIds,
      @Param("now") Instant now
  );

  interface ReservationCountProjection {

    UUID getProductId();

    Integer getCount();
  }

  @EntityGraph(attributePaths = "reservedItems")
  List<Reservation> findByExpiresAtBefore(Instant expiresAt);

}
