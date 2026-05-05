package se.jensen.johanna.fakestoreinventoryservice.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.jensen.johanna.fakestoreinventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

  @Query("SELECT i.productId as productId, i.inStock as stock FROM Inventory i WHERE i.productId IN :productIds")
  List<StockProjection> getStockByProductIds(@Param("productIds") Set<UUID> productIds);

  interface StockProjection {

    UUID getProductId();

    Integer getStock();
  }

  List<Inventory> findByProductIdIn(Set<UUID> productIds);


}
