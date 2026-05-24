package tata.Machine.Repositories;

import org.hibernate.dialect.function.NumberSeriesGenerateSeriesFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
public interface users extends JpaRepository<users, String> {
    }
