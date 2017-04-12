import kr.studygram.utils.database.Database;
import org.bson.Document;
import org.junit.Test;

/**
 * Created by cynos07 on 2017-04-11.
 */
public class DatabaseTest{
    @Test
    public void call() {
        Database.INSTANCE.insert("account", new Document("name", "user").append("id", "test").append("password", "test"));
    }
}