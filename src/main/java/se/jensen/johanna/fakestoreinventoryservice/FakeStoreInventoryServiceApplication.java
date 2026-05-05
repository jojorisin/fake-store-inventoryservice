package se.jensen.johanna.fakestoreinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FakeStoreInventoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(FakeStoreInventoryServiceApplication.class, args);
  }

}
