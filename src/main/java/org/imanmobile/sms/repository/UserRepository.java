package org.imanmobile.sms.repository;

import org.imanmobile.sms.core.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
