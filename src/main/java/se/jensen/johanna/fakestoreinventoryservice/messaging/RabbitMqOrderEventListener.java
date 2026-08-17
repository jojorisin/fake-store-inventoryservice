package se.jensen.johanna.fakestoreinventoryservice.messaging;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.jensen.johanna.fakestoreinventoryservice.repository.ReservationRepository;
import se.jensen.johanna.fakestoreinventoryservice.service.ReservationService;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqOrderEventListener {

  private final ReservationRepository reservationRepository;
  private final ReservationService reservationService;

  @RabbitListener(queues = "${app.queue.commit-reservation-queue}")
  public void handleOrderPaid(UUID orderId) {
    log.info("Received order paid event {}", orderId);
    if (!reservationRepository.existsByOrderId(orderId)) {
      log.warn("Reservation for order {} not found. Abort stock commit",
          orderId);
      return;
    }
    reservationService.confirmReservation(orderId);
  }

}
