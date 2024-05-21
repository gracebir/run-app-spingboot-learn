package dev.grace.runnerzs.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.util.Assert;

@Repository
public class RunRepository {
    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id){
        return  jdbcClient.sql("select id, title, started_on, completed_on, miles, location from run where id=?")
                .param("id", id)
                .query(Run.class)
                .optional();

    }

    public void create(Run run){
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, miles, location) VALUES(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location()))
                .update();
        Assert.state(updated==1, "Failed to create run"+run.title());
    }


    public void update(Run run, Integer id){
        var updated = jdbcClient.sql("update run set tite=?, started_on=?, completed_on=?, miles=?, location=? where id=?")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location()))
                .update();
        Assert.state(updated==1, "Failed to update run"+run.title());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from run where id=?")
                .params("id", id)
                .update();
        Assert.state(updated==1, "Failed to delete run"+id);
    }

}
