package org.imanmobile.sms.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories
public class MongoDbConfiguration {
    @Bean(name="mongo")
    Mongo mongoClient(){
        MongoClient client=null;
        try {
            client = new MongoClient("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Bean
    MongoDbFactory mongoDbFactory(){
        return new SimpleMongoDbFactory(mongoClient(), "imanmobile");
    }

    @Bean
    MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }
    
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
    	return new BCryptPasswordEncoder();
    }
    
    @Bean 
    Datastore morphia(){
    	Morphia morphia = new Morphia();
    	morphia.mapPackage("com.imanmobile.sms.core.domain");
    	
    	Datastore ds = morphia.createDatastore(mongoClient(), "imanmobile");
    	ds.ensureIndexes();
//        ds.ensureIndex(User.class, "username", "username", true, true);
        ds.ensureIndex(User.class, "email", "email", true, true);
        ds.ensureIndex(User.class, "cellnumber", "cellnumber", true, true);
        ds.ensureIndex(Group.class, "name_userid", "name, user_id", true, true);


        return  ds;
    }
}
