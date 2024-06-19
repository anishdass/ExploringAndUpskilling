package run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcclient) {
        this.jdbcClient = jdbcclient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM RUN").query(Run.class).list();
    }

    public Optional<Run> findById(int id) {
        return jdbcClient.sql("SELECT id, title, started_on, completed_on, miles, location FROM RUN WHERE id = ?")
                .param(id)
                .query(Run.class)
                .optional();
    }

    public List<Run> findByLocation(String location){
        return jdbcClient.sql("SELECT id, title, started_on, completed_on, miles, location FROM RUN WHERE location=?")
                .param(location)
                .query(Run.class)
                .list();
    }

    public void create(Run run) {
        jdbcClient.sql("DELETE FROM RUN where id=?")
                .param(run.id())
                .update();

        var updated = jdbcClient.sql("INSERT INTO RUN(id, title, started_on, completed_on, miles, location) values (?, ?, ?, ?, ?, ?)")
                .param(run.id())
                .param(run.title())
                .param(run.startedOn())
                .param(run.completedOn())
                .param(run.miles())
                .param(run.location().toString())
                .update();

        Assert.state(updated==1, "Failed to create run "+run.title());
    }

    void update(Run run, int id) {
        var updated = jdbcClient.sql("UPDATE RUN SET title=?, started_on=?, completed_on=?, miles=?, location=? WHERE id=?")
                .param(run.title())
                .param(run.startedOn())
                .param(run.completedOn())
                .param(run.miles())
                .param(run.location().toString())
                .param(run.id())
                .update();

        Assert.state(updated==1, "Failed to update run "+run.title());
    }

    void delete(int id){
        var updated = jdbcClient.sql("DELETE FROM RUN WHERE id=?")
                .param(id)
                .update();

        Assert.state(updated==1, "Failed to delete run "+id);
    }

    public int count(){
        return jdbcClient.sql("SELECT * from RUN")
                .query()
                .listOfRows()
                .size();
    }

    public void saveAll(List<Run> runs){
        runs.forEach(this::create);
    }

}