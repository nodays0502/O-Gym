package com.B305.ogym.domain.users.ptTeacher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSns is a Querydsl query type for Sns
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSns extends EntityPathBase<Sns> {

    private static final long serialVersionUID = -1079406673L;

    public static final QSns sns = new QSns("sns");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath platform = createString("platform");

    public QSns(String variable) {
        super(Sns.class, forVariable(variable));
    }

    public QSns(Path<? extends Sns> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSns(PathMetadata metadata) {
        super(Sns.class, metadata);
    }

}

