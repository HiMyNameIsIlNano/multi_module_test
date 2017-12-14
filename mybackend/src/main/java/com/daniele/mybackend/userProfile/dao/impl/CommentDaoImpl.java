package com.daniele.mybackend.userProfile.dao.impl;

import com.daniele.mybackend.shared.dao.impl.BaseEntityDaoImpl;
import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mybackend.userProfile.model.CommentFilter;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mylogger.LogMethodExecution;
import org.jooq.*;
import org.jooq.generated.tables.UserComment;
import org.jooq.generated.tables.UserProfile;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDaoImpl extends BaseEntityDaoImpl<Comment> implements CommentDao {

	@Inject
	private DSLContext create;
	
    @Override
    @LogMethodExecution
    public List<Record> findByFilter(CommentFilter filter) {
        UserComment c = UserComment.USER_COMMENT.as("c");
        UserProfile p = UserProfile.USER_PROFILE.as("p");

        SelectOnConditionStep<Record> select = create
                .select()
                .from(c)
                .join(p).on(c.USER_ID_REF.eq(p.ID));

        List<Condition> conditions = new ArrayList<>();

        if (filter.getUserName() != null) {
            Condition nameCondition = p.NAME.eq(filter.getUserName());
            select.where(nameCondition);
            conditions.add(nameCondition);
        }

        if (filter.getValidFrom() != null) {
            Date dateFrom = Date.valueOf(filter.getValidFrom());
            Condition validFromCondition = c.VALID_FROM.greaterOrEqual(dateFrom);
            conditions.add(validFromCondition);
        }

        if (filter.getValidTo() != null) {
            Date dateTo = Date.valueOf(filter.getValidTo());
            Condition validToCondition = c.VALID_TO.lessOrEqual(dateTo);
            conditions.add(validToCondition);
        }

        if (filter.isActive()){
            Condition validToCondition = c.IS_ACTIVE.eq(filter.isActive());
            conditions.add(validToCondition);
        }

        CommentDaoImpl.buildConditions(select, conditions);
        return select.fetch();
    }

    private static void buildConditions(SelectOnConditionStep<Record> select, List<Condition> conditions) {
        if (conditions.size() > 1) {
            Condition condition = conditions.get(0);
            select.where(condition);
            for (Condition other : conditions.subList(1, conditions.size())) {
                select.and(other);
            }
        } else if (conditions.size() == 1) {
            select.where(conditions.get(0));
        }
    }
}
