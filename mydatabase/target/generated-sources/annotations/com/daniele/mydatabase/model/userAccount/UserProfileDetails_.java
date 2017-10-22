package com.daniele.mydatabase.model.userAccount;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserProfileDetails.class)
public abstract class UserProfileDetails_ extends com.daniele.mydatabase.model.userAccount.SlicedEntity_ {

	public static volatile SingularAttribute<UserProfileDetails, String> password;
	public static volatile SingularAttribute<UserProfileDetails, String> surname;
	public static volatile SingularAttribute<UserProfileDetails, String> name;
	public static volatile SingularAttribute<UserProfileDetails, String> nickname;
	public static volatile SingularAttribute<UserProfileDetails, UserRole> userRole;
	public static volatile SingularAttribute<UserProfileDetails, String> email;

}

