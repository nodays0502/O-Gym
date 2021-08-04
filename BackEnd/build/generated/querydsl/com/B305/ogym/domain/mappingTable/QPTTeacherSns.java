package com.B305.ogym.domain.mappingTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPTTeacherSns is a Querydsl query type for PTTeacherSns
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPTTeacherSns extends EntityPathBase<PTTeacherSns> {

    private static final long serialVersionUID = 272865685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPTTeacherSns pTTeacherSns = new QPTTeacherSns("pTTeacherSns");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.B305.ogym.domain.users.ptTeacher.QPTTeacher ptTeacher;

    public final com.B305.ogym.domain.users.ptTeacher.QSns sns;

    public final StringPath url = createString("url");

    public QPTTeacherSns(String variable) {
        this(PTTeacherSns.class, forVariable(variable), INITS);
    }

    public QPTTeacherSns(Path<? extends PTTeacherSns> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPTTeacherSns(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPTTeacherSns(PathMetadata metadata, PathInits inits) {
        this(PTTeacherSns.class, metadata, inits);
    }

    public QPTTeacherSns(Class<? extends PTTeacherSns> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ptTeacher = inits.isInitialized("ptTeacher") ? new com.B305.ogym.domain.users.ptTeacher.QPTTeacher(forProperty("ptTeacher"), inits.get("ptTeacher")) : null;
        this.sns = inits.isInitialized("sns") ? new com.B305.ogym.domain.users.ptTeacher.QSns(forProperty("sns")) : null;
    }

}

