package org.imanmobile.sms.repository;

import org.imanmobile.sms.core.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
	Group findById(String id);
}
