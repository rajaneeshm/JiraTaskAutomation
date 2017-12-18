drop table if exists User_Limit;
create table User_Limit (
  userName VARCHAR(255) PRIMARY KEY,
  userLimit FLOAT );
  
 drop table if exists Task_Status_Rating;
create table Task_Status_Rating (
  taskStage VARCHAR(255) PRIMARY KEY,
  rating FLOAT );
 
  