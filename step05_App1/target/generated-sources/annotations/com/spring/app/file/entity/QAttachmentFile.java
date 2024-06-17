package com.spring.app.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttachmentFile is a Querydsl query type for AttachmentFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttachmentFile extends EntityPathBase<AttachmentFile> {

    private static final long serialVersionUID = -1593736935L;

    public static final QAttachmentFile attachmentFile = new QAttachmentFile("attachmentFile");

    public final StringPath attachmentFileName = createString("attachmentFileName");

    public final NumberPath<Long> attachmentFileNo = createNumber("attachmentFileNo", Long.class);

    public final NumberPath<Long> attachmentFileSize = createNumber("attachmentFileSize", Long.class);

    public final StringPath attachmentOriginalFileName = createString("attachmentOriginalFileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> noticeNo = createNumber("noticeNo", Long.class);

    public QAttachmentFile(String variable) {
        super(AttachmentFile.class, forVariable(variable));
    }

    public QAttachmentFile(Path<? extends AttachmentFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttachmentFile(PathMetadata metadata) {
        super(AttachmentFile.class, metadata);
    }

}

