create table song(
    sid int primary key auto_increment,
    url varchar(500) not null unique,
    sname varchar(255) not null,
    imgUrl varchar(255),
    lyric text,
    singer varchar(255),
    plays int default 0, -- 播放量
    level int default 0
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

create table user(
    uid int primary key auto_increment,
    pwd varchar(255) not null,
    nickname varchar(100) not null,
    email varchar(100) not null,
    level varchar(30)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

create table music_list(
    mlid int primary key auto_increment,
    uid int not null,
    mlname varchar(255) not null,
    imgUrl varchar(255),
    description varchar(500),
    constraint FK_music_list_uid foreign key (uid) references user(uid)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

create table music_list_detail(
    mldid int primary key auto_increment,
    sid int not null,
    mlid int not null,
    constraint FK_music_list_detail_sid foreign key (sid) references song(sid),
    constraint FK_music_list_detail_mlid foreign key (mlid) references music_list(mlid)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

create table comment(
    cid int primary key auto_increment,
    content varchar(500),
    uid int not null,
    sub_cid int, -- 该列为空时，表示它是sid歌曲的根评论，否则它是对应cid的子评论
    sid int not null,
    cdate datetime,
    constraint FK_comment_sub_cid foreign key (sub_cid) references comment(cid),
    constraint FK_comment_sid foreign key (sid) references song(sid)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;