package vlad;

import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPool {

        private final HikariDataSource hikariSource;


        public HikariConnectionPool() {
            hikariSource = new HikariDataSource();
            hikariSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
            hikariSource.setJdbcUrl("jdbc:postgresql://ec2-52-86-177-34.compute-1.amazonaws.com:5432/d7g10jrgsjruk4");
            hikariSource.setUsername("mtmaprkfztrfne");
            hikariSource.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
            hikariSource.setMinimumIdle(1);
            hikariSource.setMaximumPoolSize(10);
        }

public HikariDataSource getDataSource(){
    return this.hikariSource;
}

    }
