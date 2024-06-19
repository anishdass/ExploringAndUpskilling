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
			runRepository.create(new Run(1, "Monday morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(2, "Monday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(3, "Tuesday Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(4, "Tuesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(5, "Wednesday Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(6, "Wednesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(7, "Thursday Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(8, "Thursday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(9, "Friday Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
			runRepository.create(new Run(10, "Friday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, Location.OUTDOOR));
		};
	}
}