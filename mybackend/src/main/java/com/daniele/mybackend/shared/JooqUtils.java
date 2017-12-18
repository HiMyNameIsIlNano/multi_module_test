package com.daniele.mybackend.shared;

import org.jooq.*;
import org.jooq.generated.tables.records.UserProfileRecord;

import java.util.List;

public class JooqUtils {

    public static void buildConditions(SelectOnConditionStep<Record> select, List<Condition> conditions) {
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

    public static void buildConditions(SelectWhereStep<UserProfileRecord> select, List<Condition> conditions) {
        if (conditions.size() > 1) {
            Condition condition = conditions.get(0);
            SelectConditionStep<UserProfileRecord> where = select.where(condition);
            for (Condition other : conditions.subList(1, conditions.size())) {
                where.and(other);
            }
        } else if (conditions.size() == 1) {
            select.where(conditions.get(0));
        }
    }
}
