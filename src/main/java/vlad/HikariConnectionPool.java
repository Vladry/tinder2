package vlad;

import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPool {

        private final HikariDataSource hikariSource;


        public HikariConnectionPool() {
            hikariSource = new HikariDataSource();
            hikariSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
            hikariSource.setJdbcUrl("jdbc:postgresql://ec2-52-20-166-21.compute-1.amazonaws.com:5432/d6lc60vtmh2mi7");
            hikariSource.setUsername("bstbokqjlzdjsh");
            hikariSource.setPassword("d5b8cccdea56d6f7d8fc4bff397feafaed66103c456562e2d3347618796b2bd9");
            hikariSource.setMinimumIdle(1);
            hikariSource.setMaximumPoolSize(10);
        }

public HikariDataSource getDataSource(){
    return this.hikariSource;
}

    }
