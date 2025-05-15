package HelloJPA.PracticeJPA;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class PracticeJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeJpaApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			StoreQueryService bean = ctx.getBean(StoreQueryService.class);

			String name = "요아정";
			Float score = 4.0f;
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			bean.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);
		};
	}*/

}
