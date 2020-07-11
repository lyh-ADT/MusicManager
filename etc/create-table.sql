create table song(
    sid int primary key auto_increment,
    url varchar(500) not null unique,
    sname varchar(255) not null

    -- ass picture

);

create table user(
    uid int primary key auto_increment,
    pwd varchar(255) not null,
    cookies varchar(255)

     -- add level
);

create table music_list(
    mlid int primary key auto_increment,
    uid int not null,
    mlname varchar(255) not null,
    constraint FK_music_list_uid foreign key (uid) references user(uid)
    -- add picture
    -- add descrption
);

create table music_list_detail(
    sid int not null,
    mlid int not null,
    constraint FK_music_list_detail_sid foreign key (sid) references song(sid),
    constraint FK_music_list_detail_mlid foreign key (smlidid) references music_list(mlid)
);