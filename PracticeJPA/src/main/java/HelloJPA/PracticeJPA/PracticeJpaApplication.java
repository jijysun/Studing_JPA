package HelloJPA.PracticeJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeJpaApplication.class, args);
	}

}
