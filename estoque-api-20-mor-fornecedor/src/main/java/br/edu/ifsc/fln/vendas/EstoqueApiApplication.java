package br.edu.ifsc.fln.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EstoqueApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApiApplication.class, args);
	}
	
	/**
	 * Este método foi colocado para corrigir o problema com FlyWay:
	 * "Detected applied migration not resolved locally: 001. 
	 *  If you removed this migration intentionally, 
	 *  run repair to mark the migration as deleted."
	 * 
	 * https://stackoverflow.com/questions/37462550/flyway-repair-with-spring-boot
	 * @return
	 */
    @Bean
    public static FlywayMigrationStrategy cleanMigrateStrategy() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }

}
