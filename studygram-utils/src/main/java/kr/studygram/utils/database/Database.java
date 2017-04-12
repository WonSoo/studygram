package kr.studygram.utils.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by production on 2017-04-06.
 */
public enum Database {
    INSTANCE;
    private String DB_URL = "mongodb://"+SecureConfig.INSTANCE.getString("database.id")+":"+SecureConfig.INSTANCE.getString("database.password")+"@studygram-shard-00-00-csfwe.mongodb.net:27017,studygram-shard-00-01-csfwe.mongodb.net:27017,studygram-shard-00-02-csfwe.mongodb.net:27017/"+SecureConfig.INSTANCE.getString("database.name")+"?ssl=true&replicaSet=studygram-shard-0&authSource=admin";

    private MongoClientURI uri;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;
    private Document doc;

    Database()
    {
        System.out.println(DB_URL);
        uri = new MongoClientURI(DB_URL);
        mongoClient= new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase("studygram");
    }

    public void insert(String collection, Document doc)
    {
        this.collection = mongoDatabase.getCollection(collection);
        this.collection.insertOne(doc);
    }

    public static Database getInstance()
    {
        return INSTANCE;
    }
}