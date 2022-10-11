package bg.softuni.mobilelele;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MobileleleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileleleApplication.class, args);
	}

}
