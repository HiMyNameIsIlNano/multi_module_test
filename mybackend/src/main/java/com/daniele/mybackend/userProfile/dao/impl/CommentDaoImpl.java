package com.daniele.mybackend.userProfile.dao.impl;

import com.daniele.mybackend.shared.dao.impl.BaseEntityDaoImpl;
import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mybackend.userProfile.model.CommentFilter;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mylogger.LogMethodExecution;
import org.jooq.*;
import org.jooq.generated.tables.UserComment;
import org.jooq.generated.tables.UserProfile;
import org.jooq.generated.tables.records.UserCommentRecord;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.Date;
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
        boolean isFirst = true;

        SelectOnConditionStep<Record> select = create
                .select()
                .from(c)
                .join(p).on(c.USER_ID_REF.eq(p.ID));

        if (filter.getUserName() != null) {
            Condition nameCondition = p.NAME.eq(filter.getUserName());
            select.where(nameCondition);
            isFirst = false;
        }

        if (filter.getValidFrom() != null) {
            Date dateFrom = Date.valueOf(filter.getValidFrom());
            Condition validFromCondition = c.VALID_FROM.greaterOrEqual(dateFrom);
            if (isFirst) {
                select.where(validFromCondition);
                isFirst = false;
            } else {
                select.and(validFromCondition);
            }
        }

        if (filter.getValidTo() != null) {
            Date dateTo = Date.valueOf(filter.getValidTo());
            Condition validToCondition = c.VALID_FROM.eq(dateTo);
            if (isFirst) {
                select.where(validToCondition);
                isFirst = false;
            } else {
                select.and(validToCondition);
            }
        }

        if (filter.isActive()){
            Condition validToCondition = c.IS_ACTIVE.eq(filter.isActive());
            if (isFirst) {
                select.where(validToCondition);
            } else {
                select.and(validToCondition);
            }
        }

        return select.fetch();
    }
}
