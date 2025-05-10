package com.ice.music_metadata_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Music Metadata Service API",
            version = "1.0",
            description = "API for managing music metadata"))
@SpringBootApplication
public class MusicMetadataServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MusicMetadataServiceApplication.class, args);
  }
}
