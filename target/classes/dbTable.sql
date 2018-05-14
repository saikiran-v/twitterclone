
create table t_person(user_id varchar(25)  primary key not null,password varchar(50) 
not null,fullName varchar(30)not null,email varchar(50) not null,joined DateTime
not null ,active bit not null);


create table t_tweet(tweet_id  integer primary key identity not null, user_id varchar(25) not null , message varchar(140) not null,created DateTime not null, FOREIGN KEY (user_id) REFERENCES t_person(user_id) )
create table t_following(user_id  varchar(25) not null FOREIGN KEY (user_id) REFERENCES t_person(user_id) , following_id varchar(25) not null FOREIGN KEY (following_id) REFERENCES t_person(user_id) )




insert into t_person values(1,'1234','pavan1','saikiran@gmail',GETDATE(),1)
insert into t_person values(2,'1234','saikiran1','saikiran@gmail',GETDATE(),1)



select * from t_tweet

select * from t_following

//To get No of Followers
select count(following_id) from t_person p,t_following f where p.user_id=f.user_id and p.user_id=1
