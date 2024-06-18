package primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import run.Location;
import run.Run;
import run.RunRepository;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = "run")
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Application Started");
	}

	@Bean
	CommandLineRunner commandLineRunner(RunRepository runRepository){
		return args -> {
			log.info("Running command line runner");
			Run run =  new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR);
			runRepository.create(run);
			run =  new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, Location.OUTDOOR);
			runRepository.create(run);
		};
	}
}