package cz.pedry.moneytransfer;

import cz.pedry.moneytransfer.model.Account;
import cz.pedry.moneytransfer.model.Transfer;
import cz.pedry.moneytransfer.repository.AccountRepository;
import cz.pedry.moneytransfer.repository.AccountRepositoryImpl;
import cz.pedry.moneytransfer.repository.TransferRepository;
import cz.pedry.moneytransfer.repository.TransferRepositoryImpl;
import cz.pedry.moneytransfer.resource.AccountResource;
import cz.pedry.moneytransfer.resource.TransferResource;
import cz.pedry.moneytransfer.service.AccountService;
import cz.pedry.moneytransfer.service.AccountServiceImpl;
import cz.pedry.moneytransfer.service.TransferService;
import cz.pedry.moneytransfer.service.TransferServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {

        // clean database
        new App().run("db", "clean", args[0]);

        // run flyway migration
        new App().run("db", "migrate", args[0]);

        // start application
        new App().run("server", args[0]);

    }

    private final HibernateBundle<AppConfig> hibernate = new HibernateBundle<AppConfig>(Account.class, Transfer.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final FlywayBundle<AppConfig> flyway = new FlywayBundle<AppConfig>() {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
            return configuration.getDataSourceFactory();
        }
        @Override
        public FlywayFactory getFlywayFactory(AppConfig configuration) {
            return configuration.getFlywayFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(flyway);
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(AppConfig configuration, Environment environment) {
        AccountRepository accountRepository = new AccountRepositoryImpl(hibernate.getSessionFactory());
        AccountService accountService = new AccountServiceImpl(accountRepository);
        AccountResource accountResource = new AccountResource(accountService);

        TransferRepository transferRepository = new TransferRepositoryImpl(hibernate.getSessionFactory());
        TransferService transferService = new TransferServiceImpl(transferRepository);
        TransferResource transferResource = new TransferResource(transferService);

        environment.jersey().register(accountResource);
        environment.jersey().register(transferResource);
    }
}
