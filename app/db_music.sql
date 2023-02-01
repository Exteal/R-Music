create table tag(id_tag serial primary key, tag varchar(50));

create table music(id_music serial primary key, artist varchar(50), name varchar(50));

create table playlist(id_playlist serial primary key, name varchar(60));




create table playlist_contains(id_playlist int, id_music int,
 constraint fk_id_playlist foreign key(id_playlist) references playlist(id_playlist),
 constraint fk_id_music foreign key(id_music) references music(id_music));

create table music_tags(id_tag int , constraint fk_id_tag foreign key (id_tag) references tag(id_tag),
id_music int, constraint fk_id_music foreign key(id_music) references music(id_music));


insert into music(name, artist) values('superstar', 'moe shop');
insert into music(name, artist) values ('don''t you want me now', 'psyqui');
insert into music(name, artist) values ('don''t you want me now', 'rush garcia');

insert into tag(tag) values('electro');
insert into tag(tag) values('pop');
insert into tag(tag) values('house');
insert into tag(tag) values('jazz');
insert into tag(tag) values('funk');

insert into playlist(name) values ('the_best');
insert into playlist(name) values ('radio_edm');
insert into playlist(name) values ('top5');

